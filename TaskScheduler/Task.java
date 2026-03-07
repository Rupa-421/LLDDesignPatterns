import java.util.UUID;
import java.time.Instant;

public abstract class Task{
    protected String id;
    protected String work;
    protected int priority;
    protected long arrivalTime;
    protected TaskState state;
    public Task(String work,int priority){
        this.id = UUID.randomUUID().toString();
        this.work = work;
        this.priority = priority;
        this.arrivalTime = Instant.now().toEpochMilli();
        this.state = new NotPickedUpState();
    }
    public void process(){
        state.handle(this);
    }
    public abstract void execute();
    public void setState(TaskState state) {
        this.state = state;
    }
}