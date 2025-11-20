package com.example.parkinglot;

public class CarParkingSpot extends ParkingSlot{
    public CarParkingSpot(int spotNumber){
        super(spotNumber,"Car");
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle){
        return "Car".equalsIgnoreCase(vehicle.getVehicleType());
    }
}
