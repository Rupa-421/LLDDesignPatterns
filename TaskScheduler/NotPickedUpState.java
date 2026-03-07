public class NotPickedUpState implements TaskState{
    @Override
    public void handle(Task task){
        System.out.println("Task picked up as "+task.getId());
        task.setState(new InProgressState);
    }
    @Override
    public String getStateName(){
        return "NOT_PICKED_UP";
    }
}