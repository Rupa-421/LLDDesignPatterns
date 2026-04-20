class Elevator{
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<>(Collections.reverseOrder());
    private final ReentrantLock lock = new ReentrantLock();
    public Elevator(int id){
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
    }
    public void addRequest(Request request){
        lock.lock();
        try{
            int floor = request.getFloor();
            if(floor>currentFloor){
                upQueue.offer(floor);
            }else{
                downQueue.offer(floor);
            }
            if(direction == Direction.IDLE){
                direction = (floor>currentFloor)?Direction.UP:Direction.DOWN;
            }
        }
        finally{
            lock.unlock();
        }

    }
    public void step(){
        lock.lock();
        try{
            if(direction == Direction.UP){
                if(!upQueue.isEmpty()){
                    moveUp();
                }else{
                    direction = Direction.DOWN;
                }
            }
            else if(direction == Direction.DOWN){
                if(!downQueue.isEmpty()){
                    moveDown();
            else{
                        direction = Direction.UP;
                    }
                }
                else{
                    if(!upQueue.isEmpty()){
                        direction = Direction.UP;
                    }
                }else{
                    direction = Direction.DOWN;
                }
            }
        }
        finally{
            lock.unlock();
        }

    }
    private void moveUp(){
        state = ElevatorState.MOVING;
        currentFloor++;
        if(!upQueue.isEmpty() && upQueue.peek()==currentFloor){
            upQueue.poll();
            stop();
        }
    }
    private void moveDown(){
        state = ElevatorState.MOVING;
        currentFloor--;
        if(!downQueue.isEmpty() && downQueue.peek()==currentFloor){
            downQueue.poll();
            stop();
        }
    }
    private void stop(){
        state = ElevatorState.STOPPED;
        openDoor();
    }
    private void openDoor(){
        System.out.println("Elevator "+id+" opening door at floor"+currentFloor);
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public Direction getDirection(){
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }
}