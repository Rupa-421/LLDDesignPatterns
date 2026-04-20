class NearestElevatorStrategy implements SchedulingAlgorithm{
    @Override
    public Elevator selectElevator(ExternalRequest request,List<Elevator> elevators){
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator e: elevators){
            int distance = Math.abs(e.getCurrentFloor()-request.getFloor());
            if(distance<minDistance){
                minDistance = distance;
                best = e;
            }
        }
        return best;
    }
}