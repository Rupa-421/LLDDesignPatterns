import java.util.List;

public interface SchedulingStrategy {
    Task pickTask(List<Task> tasks);
}