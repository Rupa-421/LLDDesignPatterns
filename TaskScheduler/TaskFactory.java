public class TaskFactory{
    public static Task createTask(TaskType type,String work,int priority){
        switch (type){
            case CPU:
                return new CPUTask(work,priority);
            case IO:
                return new IOTask(work,priority);
            default:
                throw new IllegalArgumentException("Invalid task type");
        }
    }
}