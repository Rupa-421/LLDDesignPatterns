package com.example.lld;

public class Food {
    private int[][] foodPositions;
    private int currentFoodIndex;

    public Food(int[][] foodPositions){
        this.foodPositions = foodPositions;
        this.currentFoodIndex = 0;
    }
}
