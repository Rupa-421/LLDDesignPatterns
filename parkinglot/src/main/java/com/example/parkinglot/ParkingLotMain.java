package com.example.parkinglot;

import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLotMain {
    public static void main(String[] args){
        List<ParkingSlot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new CarParkingSpot(1));
        parkingSpots.add(new CarParkingSpot(2));
        parkingSpots.add(new BikeParkingSpot(3));
        parkingSpots.add(new BikeParkingSpot(4));

        ParkingLot parkingLot = new ParkingLot(parkingSpots);
        ParkingFeeStrategy basicHourlyRateStrategy = new BasicHourlyPaymentStrategy();
        ParkingFeeStrategy premiumRateStrategy = new PremiumRateStrategy();

        Vehicle car1 = VehicleFactory.createVehicle("Car","CAR123", basicHourlyRateStrategy);
        Vehicle bike1 = VehicleFactory.createVehicle("Bike","BIKE456",premiumRateStrategy);

        ParkingSlot carSpot = parkingLot.parkVehicle(car1);
        ParkingSlot bikeSpot = parkingLot.parkVehicle(bike1);

        Scanner sc =new Scanner(System.in);

        int paymentMethod = scanner.nextInt();

        if (carSpot != null){
            double carFee = car1.calculateFee(2,DurationType.HOURS);
            PaymentStrategy carPaymentStrategy = getPaymentStrategy(paymentMethod);
            carPaymentStrategy.processPayment(carFee);
            ParkingLot.vacateSpot(carSpot,car1);
        }
        if(bikeSpot!= null){
            double bikeFee=  bike1.calculateFee(3,DurationType.HOURS);
            PaymentStrategy bikePaymentStrategy = getPaymentStrategy(paymentMethod);
            bikePaymentStrategy.processPayment(bikeFee);
            parkingLot.vacateSpot(bikeSpot,bike1);
        }

    }

    private static PaymentStrategy getPaymentStrategy(int paymentMethod , double fee){
        return new CashPayment(fee);
    }
}
