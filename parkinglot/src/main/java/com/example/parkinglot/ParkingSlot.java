package com.example.parkinglot;

public abstract class ParkingSlot {
    private String spotType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int spotNumber;

    public ParkingSlot(int spotNumber, String spotType){
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }
    public boolean isOccupied(){
        return isOccupied;
    }
    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle){
        if(isOccupied){
            throw new IllegalStateException("Spot is already occupied");
        }
        if(!canParkVehicle(vehicle)){
            throw new IllegalArgumentException("This spot is not suitable for "+ Vehicle.getVehicleType());
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate(){
        if(!isOccupied){
            throw new IllegalStateException("spot is already vacant");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber(){
        return spotNumber;
    }
}
