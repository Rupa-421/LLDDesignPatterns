class ElevatorManager{
    private List<Elevator> elevators;
    private SchedulingAlgorithm algorithm;
    public ElevatorManager(List<Elevator> elevators,SchedulingAlgorithm algorithm){
        this.elevators = elevators;
        this.algorithm = algorithm;
    }
    public void handleExternalRequest(ExternalRequest request){
        Elevator elevator = algorithm.selectElevator(request,elevators);
        if(elevator!=null){
            elevator.addRequest(request);
        }
    }
}