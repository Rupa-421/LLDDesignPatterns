class InternalPanel{
    private Elevator elevator;
    public InternalPanel(Elevator elevator){
        this.elevator = elevator;
    }
    public void pressButton(int destinationFloor){
        InternalRequest request = new InternalRequest(destinationFloor);
        elevator.addRequest(request);
    }
}