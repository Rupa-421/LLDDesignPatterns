package Executors;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class KeyBasedExecutors{
    private final ExecutorService[] executors;
    private final int numExecutors;

    public KeyBasedExecutors(int numExecutors){
        this.numExecutors = numExecutors;
        this.executors = new ExecutorService[numExecutors];
        for(int i=0;i<numExecutors;i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key,Supplier<T> task){
        int index =getExecutorIndexForKey(key);
        ExecutorService executor = executors[index];
        return CompletableFuture.supplyAsync(task,executor);
    }

    public int getExecutorIndexForKey(Object key){
        return Math.abs(key.hashCode() % numExecutors);
    }

    public void shutdown(){
        for(ExecutorService executor: executors){
            executor.shutdown();
        }
    }
}