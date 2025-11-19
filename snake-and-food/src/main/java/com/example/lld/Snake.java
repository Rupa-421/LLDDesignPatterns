package com.example.lld;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class Snake {
    private Dequeu<Pair<Integer,Integer>> body;
    private Map<Pair<Integer,Integer>,Boolean> positionMap;

    public Snake(){
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();
        Pair<Integer,Integer> initialPos = new Pair<>(0,0);
        this.body.offerFirst(initialPos);
        this.positionMap.put(initialPos,true);

    }
}
