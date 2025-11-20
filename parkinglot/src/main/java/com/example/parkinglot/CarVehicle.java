package com.example.parkinglot;

public class CarVehicle extends Vehicle{
    public CarVehicle(String licensePlate, ParkingFeeStrategy feeStrategy){
        super(licensePlate,"Car",feeStrategy);
    }
}
