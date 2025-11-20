package com.example.parkinglot;

public class BasicHourlyPaymentStrategy implements ParkingFeeStrategy{

    @Override
    public double calculateFee(String vehicleType, int duraction, DurationType durationType) {
        return 0;
    }
}
