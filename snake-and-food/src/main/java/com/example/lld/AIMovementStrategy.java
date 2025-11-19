package com.example.lld;


public class AIMovementStrategy implements MovementStrategy{
    @Override
    public Pair<Integer,Integer> getNextPosition(Pair<Integer,Integer> currentHead,String direction){

        // AI logic to determine next best move based on food position and obstacles

        // For simplicity, this could just implement a basic pathfinding algorithm

        // or even random movement that avoids obstacles

        return currentHead;
    }
}
