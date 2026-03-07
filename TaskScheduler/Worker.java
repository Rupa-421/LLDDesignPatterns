public class Worker implements Runnable{
    private Scheduler scheduler;
    public Worker(){
        scheduler = Scheduler.getInstance();
    }
    @Override
    public void run(){
        while(true){
            Task task = scheduler.pickTask();
            if(task!=null){
                task.process();
                task.process();
            }
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}