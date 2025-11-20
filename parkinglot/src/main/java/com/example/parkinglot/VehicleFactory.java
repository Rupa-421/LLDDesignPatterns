package com.example.parkinglot;

public class VehicleFactory {

    public static VehicleFactory(String vehicleType, String licensePlate,ParkingFeeStrategy feeStrategy){

        if(vehicleType.equalsIgnoreCase("Car")){
            return new CarVehicle(licensePlate,feeStrategy);
        }
        else if(vehicleType.equalsIgnoreCase("Bike")){
            return new BikeVehicle(licensePlate,feeStrategy);
        }
        else {
            return new OtherVehicle(licensePlate,feeStrategy);
        }

    }

}
