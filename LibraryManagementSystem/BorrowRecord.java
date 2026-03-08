class BorrowRecord{
    private User user;
    private BookItem bookItem;
    private Date dueDate;
    private Date borrowDate;
    public BorrowRecord(User user,BookItem item){
        this.user = user;
        this.bookItem = item;
        this.borrowDate = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,14);
        this.dueDate = c.getTime();
    }
}