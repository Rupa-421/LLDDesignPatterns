package com.example.parkinglot;

import java.util.ArrayList;

public class ParkingLotBuilder {
    private List<ParkingFloor> floors;

    public ParkingLotBuilder(){
        this.floors = new ArrayList<>();
    }
    public ParkingLotBuilder addFloor(ParkingFloor floor){
        this.floors.add(floor);
        return this;
    }
    public ParkingLotBuilder createFloor(int floorNumber,
                                         int numOfCarSpots,
                                         int numOfBikeSpots,
                                         int... otherSpotCounts){
        ParkingFloor floor = new ParkingFloor(floorNumber);

        for(int i=0;i<numOfCarSpots;i++){
            floor.addParkingSpot(new CarParkingSpot(i+1));
        }
        for(int i=0;i<numOfBikeSpots;i++){
            floor.addParkingSpot(new BikeParkingSpot(numOfCarSpots+i+1));
        }
        return this;
    }
    public ParkingLotBuilder build(){
        return new ParkingLot(this.floors);
    }
}
