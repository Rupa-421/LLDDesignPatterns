package com.example.lld;


import ch.qos.logback.core.joran.sanity.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class SnakeGame {
    private GameBoard board;
    private Deque<Pair<Integer,Integer>> snake;
    private Map<Pair<Integer,Integer>, Boolean> snakeMap;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;

    public SnakeGame(int width, int height, int[][] food){
        this.board = GameBoard.getInstance(width,height);
        this.food=food;
        this.foodIndex=0;

        //Initialization of snake
        this.snake = new LinkedList<>();
        this.snakeMap = new HashMap<>();
        Piar<Integer,Integer> initialPos = new Pair<>(0,0);
        this.snake.offerFirst(initialPos);
        this.snake.put(initialPos,true);
        this.setMovementStrategy(new HumanMovementStrategy());
    }
    public void setMovementStrategy(MovementStrategy strategy){
        this.movementStrategy = strategy;
    }

    public int move(String direction){
        Pair<Integer, Integer> currentHead = this.snake.peekFirst();

        //GET next position
        Pair<Integer,Integer> newHead = this.movementStrategy.getNextPosition(currentHead,direction);
        int newHeadRow = newHead.getKey();
        int newHeadCol = newHead.getValue();

        //Check boundary conditions
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= this.board.getHeight() || newHeadCol <0 ||
                newHeadCol >=this.board.getWidth();

        //Get current tail for collision check
        Pair<Integer,Integer> currentTail = this.snake.peekLast();

        //Check if snake bites itself
        boolean bitesItself = this.snakeMap.containsKey(newHead) && !(newHead.getKey() == currentTail.getKey() &&
                newHead.getValue() == currentTail.getValue());

        //Game over conditions
        if( crossesBoundary || bitesItself){
            return -1;
        }
        boolean ateFood = (this.foodIndex < this.food.length) &&
                (this.food[this.foodIndex][0] == newHeadRow) &&
                (this.food[this.foodIndex][1] == newHeadCol);

        if(ateFood){
            tihs.foodIndex++;
        }
        else{
            this.snake.pollLast();
            this.snakeMap.remove(currentTail);
        }
        this.snake.addFirst(newHead);
        this.snakeMap.put(newHead,true);
        int score = this.snake.size()-1;
        return score;

    }
    public static void main(String[] args){
        int width =20;
        int height =15;

        int[][] foodPositions={
                {5,5},
                {10,8},
                {3,12},
                {8,17},
                {12,3}
        };
        SnakeGame game =new SnakeGame(width,height,foodPositions);
        Scanner sc=new Scanner(System.in);
        boolean gameRunning = true;
        int score =0;
        while(gameRunning){
            displayGameState(game);
            String input = scanner.nextLine().toUpperCase();
            if(input.equals("Q")){
                System.out.println("Game eneed by player");
                gameRunning = false;
                continue;
            }
            String direction = convertInput(input);
            if(direction.isEmpty()){
                continue;
            }
            score = game.move(direction);
            if(score == -1){
                gameRunning = false;
            }
        }
        scanner.close();
        System.out.println("Thanks for playing");
    }
}
