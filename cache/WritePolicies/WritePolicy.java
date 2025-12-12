package WritePolicies;

import StorageMechanisms.*;

public interface WritePolicy<K,V>{
    void write(K key,V value,CacheStorage<K,V> cacheStorage,DBStorage<K,V> dbStorage) throws Exception;
}