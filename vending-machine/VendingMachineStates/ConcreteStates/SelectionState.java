package VendingMachineStates.ConcreteStates;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class SelectionState implements VendingMachineState{
    public SelectionState(){
        System.out.println("Vending machine is now in selection state");
    }

    @Override
    public String getStateName(){
        return "SelectionState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context){
        if(!context.getInventory().hasItems()){
            return new OutOfStockState();
        }
        if(context.getCoinList().isEmpty()){
            return new IdleState();
        }
        if(context.getSelectedItemCode()>0){
            return new DispenseState();
        }
        return this;
    }
}