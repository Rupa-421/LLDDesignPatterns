class Catalog{
    private Map<String,Book> booksByIsbn = new HashMap<>();
    public void addBook(Book book){
        booksByIsbn.put(book.getIsbn(),book);
    }
    public Book searchByIsbn(String isbn){
        return booksByIsbn.isbn();
    }
}