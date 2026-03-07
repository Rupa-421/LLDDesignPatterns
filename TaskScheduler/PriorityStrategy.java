import java.util.List;

public class PriorityStrategy implements SchedulingStrategy{

    @Override
    public Task pickTask(List<Task> tasks){
        Task best = null;
        for(Task task:tasks){
            if(best == null || task.getPriority()>best.getPriority()){
                best = task;
            }
        }
        return best;
    }
}