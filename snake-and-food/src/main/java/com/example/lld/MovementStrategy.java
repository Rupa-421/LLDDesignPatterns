package com.example.lld;

public interface MovementStrategy {
    Pair<Integer,Integer> getNextPosition(Pair<Integer,Integer> currentHead, String direction);
}
