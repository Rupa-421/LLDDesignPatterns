package MainCache;

import EvictionAlgorithms.EvictionAlgorithm;
import Executos.KeyBasedExecutor;
import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;
import WritePolicies.WritePolicy;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
public class Cache<K,V>{

    private final CacheStorage<K,V> cacheStorage;
    private final DBStorage<K,V> dbStorage;
    private final WritePolicy<K,V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final KeyBasedExecutor keyBasedExecutor;

    public Cache(CacheStorge<K,V> cacheStorge, DBStorage<K,V> dbStorage, WritePolicy<K,V> writePolicy, EvictionAlgorithm<K> evictionAlgorithm,int numExecutors){
        this.cacheStorage = cacheStorge;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new KeyBasedExecutor(numExecutors);
    }

    public CompletableFuture<V> accessData(K key){
        return keyBasedExecutor.submitTask(key, ()->{
            try{
                if(!cacheStorage.containsKey(key)){
                    throw new Exception("key not found in cahce" + key);
                }
                evictionAlgorithm.keyAccessed(key);
                return cacheStorage.get(key);
            }catch (Exception e){
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key,V value){
        return keyBasedExecutor.submitTask(key,()->{
            try{
                if(cacheStorage.containsKey(key)){
                    writePolicy.write(key,value,cacheStorage,dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
                else{
                    if(cacheStorage.size()>=cacheStorage.getCapacity()){
                        K evictedKey = evictionAlgorithm.evictKey();
                        if(evictedKey!=null){
                            int currentIndex = KeyBasedExecutor.getExecutorIndexForKey(key);
                            int evictedIndex = KeyBasedExecutor.getExecutorIndexForKey(evictedKey);
                            if(currentIndex == evictedIndex){
                                cacheStorage.remove(evictedKey);
                            }else{
                                CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey,()->{
                                    try{
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    }
                                    catch(Exception ex){
                                        throw new CompletionException(ex);
                                    }
                                });
                                removalFuture.join();
                            }
                        }
                    }
                    writePolicy.write(key,value,cacheStorage,dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
            }
        });
    }

    public void shutdown(){
        keyBasedExecutor.shutdown();
    }
}