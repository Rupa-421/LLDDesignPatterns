package com.example.parkinglot;

public class OtherVehicle extends Vehicle{
    public OtherVehicle(String licensePlate, ParkingFeeStrategy feeStrategy){
        super(licensePlate,"other",feeStrategy);
    }
}
