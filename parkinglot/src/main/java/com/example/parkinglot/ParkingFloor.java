package com.example.parkinglot;

import java.util.ArrayList;

public class ParkingFloor {
    private List<ParkingSlot> spots;
    private int floorNumber;

    public ParkingFloor(int floorNumber){
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSlot spot){
        this.spots.add(spot);
    }
    public ParkingSlot findAvailableSpot(String vehicleType){
        for(ParkingSlot slot: spots){
            if(!slot.isOccupied()){
              if( slot.getSpotType().equalsIgnoreCase(vehicleType)){
                    return slot;
                }
            }
        }
    }
    public List<ParkingSpot> getParkingSpots(){
        return spots;
    }
    public int getFlootNumber(){
        return floorNumber;
    }
}
