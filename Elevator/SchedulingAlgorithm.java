interface SchedulingAlgorithm{
    Elevator selectElevator(ExternalRequest request,List<Elevator> elevators);
}