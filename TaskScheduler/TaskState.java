public interface TaskState {
    void handle(Task task);
    String getStateName();
}