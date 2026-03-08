class User{
    private String userId;
    private String name;
    private List<BorrowRecord> borrowedBooks = new ArrayList<>();
    public User(String userId,String name){
        this.userId = userId;
        this.name = name;
    }
    public void borrowBook(BookItem item){
        BorrowRecord record = new BorrowRecord(this,item);
        borrowedBooks.add(record);
    }
    public String getUserId(){
        return userId;
    }
}