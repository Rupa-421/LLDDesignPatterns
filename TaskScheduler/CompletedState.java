public class CompletedState implements TaskState{
    @Override
    public void handle(Task task){
        System.out.println("Task already completed"+task.getId());
    }
    @Override
    public String getStateName(){
        return "COMPLETED";
    }
}