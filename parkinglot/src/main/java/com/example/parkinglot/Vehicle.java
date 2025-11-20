package com.example.parkinglot;

public abstract class Vehicle {
    private String licensePlate;
    private String vehicleType;
    private ParkingFeeStrategy feeStrategy;

    public Vehicle(String licensePlate,String vehicleType, ParkingFeeStrategy feeStrategy){
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.feeStrategy = feeStrategy;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public String getLicensePlate(){
        return licensePlate;
    }
}
