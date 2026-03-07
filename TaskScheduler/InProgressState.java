public class InProgressState implements TaskState{
    @Override
    public void handle(Task task){
        System.out.println("Executing task "+task.getId());
        task.execute();
        task.setState(new CompletedState());
    }
    @Override
    public String getStateName(){
        return "IN_PROGRESS";
    }
}