package com.example.parkinglot;

public class BikeParkingSpot extends ParkingSlot{
    public BikeParkingSpot(int spotNumber){
        super(spotNumber,"Bike");
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle){
        return "Bike".equalsIgnoreCase(vehicle.getVehicleType());
    }
}
