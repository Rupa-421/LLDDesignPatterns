import java.util.List;

public class FIFOStrategy implements SchedulingStrategy{

    @Override
    public Task pickTask(List<Task> tasks){
        Task earliest = null;
        for(Task task:Task){
            if(earlies == null || task.getArrivalTime()<earliest.getArrivalTime()){
                earliest = task;
            }
        }
        return earliest;
    }
}