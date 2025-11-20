package com.example.parkinglot;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duraction, DurationType durationType);
}
