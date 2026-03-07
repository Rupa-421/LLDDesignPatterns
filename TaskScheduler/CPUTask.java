public class CPUTask extends Task{
    public CPUTask(String work,int priority){
        super(work,priority);
    }
    @Override
    public void execute(){
        System.out.println("Running CPU task: "+work);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}