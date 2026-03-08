class Reservation{
    private User user;
    private Book book;
    private Date reservationDate;

    public Reservation(User user,Book book){
        this.user = user;
        this.book = book;
        this.reservationDate = new Date();
    }

    public User getUser() {
        return user;
    }
}