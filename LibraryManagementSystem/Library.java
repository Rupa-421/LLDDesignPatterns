class Library{
    private Catalog catalog = new Catalog();
    private Map<String,Queue<BookItem>> availableCopies = new ConcurrentHashMap<>();
    private Map<String,Queue<Reservation>> reservations = new ConcurrentHashMap<>();
    public void addBook(Book book){
        catalog.addBook(book);
        Queue<BookItem> queue = new LinkedList<>();
        for(BookItem item:book.getCopies()){
            queue.add(item);
        }
        availableCopies.put(book.getIsbn(),queue);
    }
    public boolean borrowBook(User user,String isbn){
        Queue<BookItem> queue = availableCopies.get(isbn);
        if(queue == null || queue.isEmpty()){
            System.out.println("No copies available");
            return false;
        }
        BookItem item = queue.poll();
        if(item.borrow()){
            user.borrowBook(item);
            System.out.println("Book issued "+item.getBarcode());
            return true;
        }
        return false;
    }
    public void returnBook(BookItem item){
        item.returnBook();
        availableCopies.computeIfAbsent(item.getBarCode(),k->new LinkedList<>()).add(item);
    }

    public void reserveBook(User user,Book book){
        reservations.computeIfAbsent(book.getIsbn(),k->new LinkedList<>()).add(new Reservation(user,book));
        System.out.println("Book reserved for user"+user.getUserId());
    }
}