package VendingMachineStates;

import CommonEnums.Coin;
import UtilityClasses.*;
import VendingMachineStates.ConcreteStates.DispenseState;
import VendingMachineStates.ConcreteStates.HasMoneyState;
import VendingMachineStates.ConcreteStates.IdleState;
import VendingMachineStates.ConcreteStates.SelectionState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineContext{
    private VendingMachineState currentState;
    private Inventory inventory;
    private int selectedItemCode;

    public VendingMachineContext(){
        inventory = new Inventory(10);
        coinList = new ArrayList<>();
        currentState = new IdleState();
        System.out.println("Initialized "+currentState.getStateName());
    }

    public VendingMachineState getCurrentState(){
        return currentState;
    }

    public void advanceState(){
        VendingMachineState nextState = currentState.next(this);
        System.out.println("Current state "+ currentState.getStateName());
    }

    public void clickOnInsertCoinButton(Coin coin){
        if(currentState instanceof IdleState || currentState instanceof HasMoneyState){
            System.out.println("Inserted" + coin.name()+" worth "+coin.value);
            coinList.add(coin);
            advanceState();
        }else{
            System.out.println("Cannot insert coin in "+ currentState.getStateName());
        }
    }

    public void selectProduct(int codeNumber){
        if(currentState instanceof SelectionState){
            try{
                Item item = inventory.getItem(codeNumber);
                int balance = getBalance();
                if (balance < item.getPrice()) { // Check for sufficient funds
                    System.out.println(
                            "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
                    return;
                }
                setSelectedItemCode(codeNumber);
                advanceState();
                dispenseItem(codeNumber);
                if(balance>=item.getPrice()){
                    int change = balance - item.getPrice();
                    System.out.println("Returning change: " + change);

                }
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }else(
                System.out.println("Products can only be selected in Selection state");
                )
    }

    public void resetBalance(){
        coinLit.clear();
    }

    public int getBalance(){
        int balance = 0;
        for(Coin coin:coinList){
            balance+=coin.value;
        }
        return balance;
    }

    public void resetSelection(){
        this.selectedItemCode =0;
    }

    public void setSelectedItemCode(int codeNumber){
        this.selectedItemCode = codeNumber;
    }

    public int getSelectedItemCode(){
        return selectedItemCode;
    }
    public void setCoinList(List<Coin> coinList){
        this.coinList = coinList;
    }

    public List<Coin > getCoinList(){
        return coinList;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }
    public void updateInventory(Item item,int coinNumber){
        if(currentState instanceof IdleState){
            try{
                inventory.addItem(item,codeNumber);
                System.out.println("Added "+item.getType()+" to slot"+codeNumber);
            }catch (Exception e){
                System.out.println("error updating inventory "+e.getMessage());
            }
        }else{
            System.out.println("inventory can only be updated in idle state");
        }
    }

    public void dispenseItem(int codeNumber){
        if(currentState instanceof DispenseState){
            try{
                Item item=inventory.getItem(codeNumber);
                System.out.println("Dispensing "+item.getType());
                inventory.removeItem(codeNumber);
                inventory.updateSoldOutItem(codeNumber);
                resetBalance();
                resetSelection();
                advanceState();
            }
        }
    }

    public void clickOnStartProductSelectionButton(int codeNumber){
        if(currentState instanceof HasMoneyState){
            advanceState();
            selectProduct(codeNumber);
        }else{
            System.out.println("Product selection button can only be clicked in hasmoney state");
        }
    }
}