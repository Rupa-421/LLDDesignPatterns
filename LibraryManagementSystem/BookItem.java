public class BookItem{
    private String barcode;
    private Book book;
    private BookStatus status;
    private String rackLocation;

    private ReentrantLock lock = new ReentrantLock();
    public BookItem(String barcode,Book book,String rackLocation){
        this.book = book;
        this.barcode = barcode;
        this.rackLocation = BookStatus.AVAILABLE;
    }
    public boolean borrow(){
        lock.lock();
        try{
            if(status == BookStatus.AVAILABLE){
                status = BookStatus.ISSUED;
                return true;
            }
            return false;
        }finally{
            lock.unlock();
        }
    }
    public void returnBook(){
        lock.lock();
        try{
            status = BookStatus.AVAILABLE;
        }finally {
            lock.unlock();
        }
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getBarcode() {
        return barcode;
    }
}