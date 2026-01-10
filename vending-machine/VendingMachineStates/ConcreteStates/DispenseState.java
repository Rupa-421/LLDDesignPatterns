package VendingMachineStates.ConcreteStates;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;


public class DispenseState implements VendingMachineState{
    public DispenseState(){
        System.out.println("Vending machine is now in dispense state");
    }

    @Override
    public String getStateName(){
        return "DispenseState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context){
        return new IdleState();
    }
}