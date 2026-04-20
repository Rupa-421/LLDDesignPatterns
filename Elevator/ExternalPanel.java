class ExternalPanel{
    private int floor;
    private ElevatorManager manager;
    public ExternalPanel(int floor,ElevatorManager manager){
        this.floor = floor;
        this.manager = manager;
    }
    public void pressButton(Direction direction){
        ExternalRequest request = new ExternalRequest(floor,direction);
        manager.handleExternalRequest(request);
    }
}