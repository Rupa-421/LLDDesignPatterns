public class IOTask extends Task{
    public IOTask(String work,int priority){
        super(work,priority);
    }

    @Override
    public void execute(){
        System.out.println("Running IO task "+work);
        try{
            Thread.sleep(2000);
        }
        catch (InterruptException e){
            e.printStackTrace();
        }
    }
}