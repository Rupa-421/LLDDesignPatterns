package com.example.lld;


public class HumanMovementStrategy implements MovementStrategy{
    @Override
    public Pair<Integer,Integer> getNextPosition(Pair<Integer,Integer> currentHead,String direction){
        int row = currentHead.getKey();
        int col = currentHead.getValue();

        switch (direction){
            case "U" : return new Pair<>(row-1,col);
            case "D" : return new Pair<>(row+1,col);
            case "L" : return new Pair<>(row,col-1);
            case "R" : return new Pair<>(row,col+1);
            default: return currentHead;
        }
    }
}
