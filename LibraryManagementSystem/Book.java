import java.util.*;
class Book{
    private String author;
   private String title;
    privte List<BookItem> copies = new ArrayList<>();

    public Book(String author,String title){
        this.title = title;
        this.author = author;
    }
    public void addCopy(BookItem item) {
        copies.add(item);
    }
}