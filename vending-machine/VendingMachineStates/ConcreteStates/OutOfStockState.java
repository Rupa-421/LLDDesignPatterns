package VendingMachineStates.ConcreteStates;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class OutOfStockState implements VendingMachineState{
    pulic OutOfStockState(){
        System.out.println("Vending machine is now in Out of Stock state");
    }

    @Override
    public String getStateName(){
        return "OutOfStockState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context){
        if(context.getInventory().hasItems()){
            return new IdleState();
        }
        return this;
    }
}