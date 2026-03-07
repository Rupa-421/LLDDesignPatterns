import java.util.ArrayList;
import java.util.List;

public class Scheduler{
    private static Scheduler instance;
    private List<Task> tasks;
    private SchedulingStrategy strategy;
    private Scheduler(){
        task = new ArrayList<>();
        strategy = new PriorityStrategy();
    }
    public static synchronized Scheduler getInstance(){
        if(instance == null){
            instance = new Scheduler();
        }
        return instance;
    }

    public synchronized void addTask(Task task){
        tasks.add(task);
        System.out.println("Task added " + task.getId());
    }
    public synchronized Task pickTask(){
        if(tasks.isEmpty()){
            return null;
        }
        Task task = strategy.pickTask(tasks);
        tasks.remove(task);
        return task;
    }
    public void setStrategy(SchedulingStrategy strategy){
        this.strategy = strategy;
    }
}