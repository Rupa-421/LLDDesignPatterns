package UtilityClasses;
public class Inventory{
    ItemShelf[] inventory=null;

    public Inventory(int itemCount){
        inventory = new ItemShelf[itemCount];
        initialEmptyInventor();
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemShelf[] inventory){
        this.inventory = inventory;
    }

    public void initialEmptyInventor(){
        int startCode = 101;
        for(int i=0;i<inventory.length;i++){
            ItemShelf space = new ItemShelf(startCode);
            inventory[i]=space;
            startCode++;
        }
    }
    public void addItem(Item item,int codeNumber) throws Exception{
        for(ItemShelf itemShelf: inventory){
            if(itemShelf.getCode() == codeNumber){
                itemShelf.addItem(item);
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public Item getItem(int codeNumber) throws Exception{
        for(ItemShelf itemShelf : inventory){
            if(itemShelf.getCode() == codeNumber){
                if(itemShelf.checkIsSoldOut()){
                    throw new Exception("Item already sold out");
                }
                else{
                    Item item = itemShelf.getItems().get(0);
                    return item;
                }
            }
        }
        throw new Exception("Invalid code");
    }

    public void updateSoldOutItem(int codeNumber){
        for(ItemShelf itemShelf: inventory){
            if(itemShelf.getCode() == codeNumber){
                if(itemShelf.getItems().isEmpty())
                    itemShelf.setIsSoldOut(true);
            }
        }
    }

    public void removeItem(int codeNumber) throws Exception{
        for(ItemShelf itemShelf:inventory){
            if(itemShelf.getCode() == codeNumber){
                itemShelf.removeItem(itemShelf.getItems().get(0));
                return ;
            }
        }
        throw new Exception("Invalid Code");
    }

    public boolean hasItems(){
        for(ItemShelf itemShelf:inventory){
            if(!itemShelf.checkIsSoldOut()) return true;
        }
        return false;
    }

}