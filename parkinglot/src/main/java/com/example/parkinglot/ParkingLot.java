package com.example.parkinglot;

public class ParkingLot {
    private List<ParkingSlot> parkingSpots;

    public ParkingLot(List<ParkingSlot> parkingSpots){
        this.parkingSpots = parkingSpots;
    }

    public ParkingSlot findAvailableSpot(String vehicleType){
        for(ParkingSlot slot:parkingSpots){
            if(!slot.isOccupied() && slot.getSpotType().equals(vehicleType)){
                return slot;
            }
        }
        return null;
    }

    public ParkingSlot parkVehicle(Vehicle vehicle){
ParkingSlot spot = findAvailableSpot(vehicle.getVehicleType());
if(spot!=null){
    spot.parkVehicle(vehicle);
    System.out.println("Vehicle parked successfully in spot"+ spot.getSpotNumber());
    return spot;
}
System.out.println("No parking spots available for " + vehicle.getVehicleType());;
return null;
    }
    public void vacateSpot(ParkingSlot spot, Vehicle vehilce){
        if(spot!=null && spot.isOccupied() && spot.getVehicle().equals(vehilce)){
            spot.vacate();
            System.out.println(vehilce.getVehicleType()+" vacated the spot"+ vehilce.getSpotNumber());
        }
        else{
            System.out.println("Invalid operation.Either the spot is already vacated or the vehicle doesnt match");
        }
    }

    public ParkingSlot isSpotByNumber(int spotNumber){
        for(ParkingSlot spot: parkingSpots){
            if(spot.getSpotNumber() == spotNumber){
                return spot;
            }
        }
        return  null;
    }
    public List<ParkingSlot> getParkingSpots(){
        return parkingSpots;
    }
}
