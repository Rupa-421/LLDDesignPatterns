class ExternalRequest extends Request{
    private Direction direction;
    public ExternalRequest(int floor,Direction direction){
        this.floor =floor;
        this.direction = direction;
    }
    public Direction getDirection(){
        return direction;
    }
}