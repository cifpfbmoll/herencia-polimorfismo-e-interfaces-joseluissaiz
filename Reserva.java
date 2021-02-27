import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reserva implements Material {
    private Libro Book;
    private Usuario User;
    private String reserveDate;
    private String reserveTime;
    private String returnDate;

    /* Constructor methods */

    public Reserva() {
    }

    public Reserva(Libro Book, String reserveDate,String reserveTime, String returnDate, Usuario User) {
        this.Book = Book;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.User = User;
    }

    public Reserva(Reserva Reserve) {
        this.Book = Reserve.getBook();
        this.reserveDate = Reserve.getDate();
        this.reserveTime = Reserve.getTime();
        this.User = Reserve.getUser();
        this.returnDate = Reserve.getReturnDate();
    }


    /* Object methods */

    @Override
    public String toString() {
        return super.toString();
    }

    public void toString2() {
        System.out.println("Book name: " + this.getBook().getTitle());
        System.out.println("ISBN: " + this.getBook().getISBN());
        System.out.println("Reserve date: " + this.getDate());
        System.out.println("Reserve Time: " + this.getTime());
        System.out.println("Return date: " + this.getReturnDate());
        System.out.println("Days passed: " + getDateDifferenceInDays());
        System.out.println("_______________________________");
    }


    /* Instantiate functions */

    public static void makeReserve(Scanner reader) {
        Reserva newReserve = new Reserva();
        System.out.println("Make a Reserve");
        System.out.println("______________________");
        newReserve.setUser(reader);
        if (newReserve.getUser() != null) {
            newReserve.setBook(reader);
            if (newReserve.getBook() != null && newReserve.getUser() != null) {
                if (newReserve.getBook().isAvailable()) {
                    newReserve.setReserveDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalDateTime now = LocalDateTime.now();
                    newReserve.setReserveTime(dateTimeFormatter.format(now));
                    newReserve.setReturnDate(newReserve.getBook());
                    newReserve.getBook().setAvailableCopies(newReserve.getBook().getAvailableCopies() - 1);
                    newReserve.getUser().getAllReserves().add(newReserve);
                    System.out.println("=======================================");
                    System.out.println("Book >>>>> " + newReserve.getBook().getTitle() + " >>>>> reserved to >>>>> " +
                            newReserve.getUser().getFirstName());
                    System.out.println("=======================================");
                } else {
                    System.out.println("The book " + newReserve.getBook().getTitle() + " >>>>>> is not available");
                }
                System.out.println("______________________");
                reader.nextLine();
            }
        }
    }

    public static void returnBook(Scanner reader) {
        System.out.println("Return Book");
        System.out.println("______________________");
        Usuario User = authUser(reader);
        if (User != null){
            System.out.println("Enter the book ISBN");
            String ISBN = reader.nextLine();
            int i = 0;
            boolean founded = false;
            while (!founded && i < User.getAllReserves().size()){
                Libro bookIterator = User.getAllReserves().get(i).getBook();
                if (bookIterator.getISBN().equals(ISBN)){
                    founded = true;
                    System.out.println("Book <<<<< " + bookIterator.getTitle() + " <<<<< returned correctly");
                    bookIterator.setAvailableCopies(bookIterator.getAvailableCopies() + 1);
                    User.getAllReserves().remove(i);
                    reader.nextLine();
                }
                i++;
            }
            if (!founded){
                System.out.println("<<<<< We cannot found the book with ISBN " + ISBN + " in your reserves >>>>>");
                reader.nextLine();
            }
        }

    }


    /* Request info functions */

    private static Usuario authUser(Scanner reader) {
        Usuario User = null;
        System.out.println("VERIFY USER");
        System.out.println("Enter the user telephone number");
        System.out.println("______________________");
        String phoneNumber = reader.nextLine();
        System.out.println("Enter the user email address");
        System.out.println("______________________");
        String emailAddress = reader.nextLine();
        int userPositionIfExists = Usuario.searchUserPosition(phoneNumber, emailAddress);
        if (userPositionIfExists != -1) {
            User = (Usuario) Persona.getAllPersons().get(userPositionIfExists);
            System.out.println("User identified as " + User.getFirstName());
        } else {
            System.out.println("<<< Invalid phone number or email address >>>");
            reader.nextLine();
        }
        return User;
    }

    public static void showUserReserves(Usuario User) {
        for (int i = 0; i < User.getAllReserves().size(); i++) {
            User.getAllReserves().get(i).toString2();
        }
    }


    /*
    --DTO after this line--
    Setters for the instance variables
    */

    public void setUser(Scanner reader) {
        this.User = authUser(reader);
    }

    public void setReserveDate(String date) {
        this.reserveDate = date;
    }

    public void setReserveTime(String time){
        this.reserveTime = time;
    }

    public void setBook(Scanner reader) {
        System.out.println("Introduce the book ISBN");
        String ISBN = reader.nextLine();
        int bookIndex = Libro.searchBookByISBN(ISBN);
        if (bookIndex != -1) {
            this.Book = Libro.getAllBooks().get(bookIndex);
        } else {
            System.out.println("Book not founded by the ISBN provided");
            reader.nextLine();
        }
    }

    @Override
    public void setReturnDate(Object LibraryObj) {
        if (LibraryObj instanceof Libro){
            LocalDate futureDate = LocalDate.now().plusMonths(1);
            this.returnDate = futureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }


    //Getters for all the variables

    public Libro getBook() {
        return this.Book;
    }

    public String getDate() {
        return this.reserveDate;
    }

    public String getTime(){
        return this.reserveTime;
    }

    public Usuario getUser() {
        return this.User;
    }

    public int getDateDifferenceInDays() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date reserveDate = null;
        try {
            reserveDate = dateFormat.parse(this.getDate());
            Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
            long diffInMillis = Math.abs(currentDate.getTime() - reserveDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            return (int) diff;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getReturnDate(){
        return this.returnDate;
    }
}
