public class TaskScheduler{
    public static void main(String[] args){
        Scheduler scheduler = Scheduler.getInstance();
        Task t1 = TaskFactory.createTask(TaskType.CPU,"Process data",5);
        Task t2 = TaskFactory.createTask(TaskType.IO,"Read data",3);
        Task t3 = TaskFactory.createTask(TaskType.CPU,"computation",15);
        scheduler.addTask(t1);
        scheduler.addTask(t2);
        scheduler.addTask(t3);
        Thread worker1 = new Thread(new Worker());
        Thread worker2 = new Thread(new Worker());
        worker1.start();
        worker2.start();
    }
}