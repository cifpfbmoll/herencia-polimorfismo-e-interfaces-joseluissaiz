import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Libro {

    private String ISBN;
    private String ISBNRegex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+"
            + "[- ]?[0-9]+[- ]?[0-9X]$";
    Pattern pattern = Pattern.compile(ISBNRegex);
    private String title;
    private String author;
    private String publisher;
    private int availableCopies;
    private int totalCopies;
    private static List<Libro> allBooks = new ArrayList<Libro>();


    //Constructor methods

    public Libro() {
    }

    public Libro(final Libro Book) {
        this.ISBN = Book.getISBN();
        this.title = Book.getTitle();
        this.author = Book.getAuthor();
        this.publisher = Book.publisher;
        this.availableCopies = Book.getAvailableCopies();
        this.totalCopies = Book.getAllCopies();
    }

    public Libro(String ISBN, String title, String author, String publisher, int totalCopies, int availableCopies) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }


    //Object methods

    public void toString2() {
        System.out.println("Title: " + this.getTitle());
        System.out.println("ISBN: " + this.getISBN());
        System.out.println("Author: " + this.getAuthor());
        System.out.println("Publisher: " + this.getPublisher());
        System.out.println("Total copies: " + this.getAllCopies());
        System.out.println("Available copies: " + this.getAvailableCopies());
        System.out.println("______________________________");
    }


    //Book functions

    public static void addBook(Scanner reader) {
        Libro emptyBook = new Libro();
        System.out.println("Add a new book");
        System.out.println("_____________________________________");
        System.out.println("How many copies do you want to add");
        int copies = Integer.parseInt(reader.nextLine());
        System.out.println("Enter the book ISBN");
        String ISBN = reader.nextLine();
        int positionIfExists = searchBookByISBN(ISBN);
        if (positionIfExists == -1) {
            emptyBook.setISBN(reader, ISBN);
            emptyBook.setTtile(reader);
            emptyBook.setAuthor(reader);
            emptyBook.setPublisher(reader);
            emptyBook.addCopies(copies);
            getAllBooks().add(emptyBook);
            System.out.println("You have added " + copies + " copies of " + emptyBook.getTitle());
            System.out.println("ISBN: " + emptyBook.getISBN());
        } else {
            getAllBooks().get(positionIfExists).addCopies(copies);
            System.out.println("You have added " + copies + " copies of " +
                    getAllBooks().get(positionIfExists).getTitle());
            System.out.println("ISBN: " + getAllBooks().get(positionIfExists).getISBN());
        }

    }

    public static void addBookCopy(Scanner reader){
        System.out.println("Adding a new book copy");
        System.out.println("___________________________");
        System.out.println("Enter the ISBN of the book that you want to copy");
        String ISBNoriginal = reader.nextLine();
        int bookPositionIfExists = searchBookByISBN(ISBNoriginal);
        if (bookPositionIfExists != -1){
            Libro BookOriginal = getAllBooks().get(bookPositionIfExists);
            Libro BookVariant = new Libro(BookOriginal);
            System.out.println("Enter the ISBN variant of the new book");
            String ISBNvariant = reader.nextLine();
            if (!ISBNvariant.equals(ISBNoriginal)){
                BookVariant.setISBN(reader, ISBNvariant);
                System.out.println("Enter the number of copies that you want to add");
                String copyNumber = reader.nextLine();
                try {
                    int copyNumberInt = Integer.parseInt(copyNumber);
                    if (copyNumberInt > 0){
                        BookVariant.setTotalCopies(0);
                        BookVariant.setAvailableCopies(0);
                        BookVariant.addCopies(copyNumberInt);
                        getAllBooks().add(BookVariant);
                        System.out.println("New variant of " + BookOriginal.getTitle() + " >>>>> added correctly");
                    } else {
                        System.out.println("<<< The number of copies must be greater than 0 >>>");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("<<< You need to enter a number >>>");
                }
            } else {
                System.out.println("<<< You need to put a new ISBN for the book variant >>>");
            }
        } else {
            System.out.println("<<< We cannot found the book with ISBN " + ISBNoriginal + " >>>");
        }
    }

    private static void deleteModeOneCopy(Libro bookIterator) {
        if (bookIterator.isAvailable()) {
            bookIterator.addCopies(-1);
            System.out.println("You have deleted one copy of " + bookIterator.getTitle());
        } else {
            System.out.println("You cannot delete any copy because there are no available copies");
        }
    }

    private static void deleteModeAllCopies(int bookPosition) {
        System.out.println("You have deleted the book named " + getAllBooks().get(bookPosition).getTitle());
        Libro.getAllBooks().remove(bookPosition);
    }

    public static void deleteBook(Scanner reader, String mode) {
        System.out.println("Enter ISBN to delete this book");
        String ISBN = reader.nextLine();
        int bookPosition = Libro.searchBookByISBN(ISBN);
        if (bookPosition != -1) {
            Libro bookIterator = getAllBooks().get(bookPosition);
            if (mode.equals("oneCopy")) {
                deleteModeOneCopy(bookIterator);
            } else if (mode.equals("allCopies")) {
                deleteModeAllCopies(bookPosition);
            }
        } else {
            System.out.println("No books are found by the ISBN provided");
        }
    }

    public static int searchBookByISBN(String ISBN) {
        int position = -1;
        boolean found = false;
        int i = 0;
        while (!found && i < getAllBooks().size()) {
            if (getAllBooks().get(i).getISBN().equals(ISBN)) {
                position = i;
            }
            i++;
        }
        return position;
    }

    public static void searchBookByTitle(Scanner reader) {
        List<String[]> booksInfo = new ArrayList<String[]>();
        System.out.println("Search book by name");
        System.out.println("_____________________________________");
        System.out.println("Enter a book name: ");
        String searchTitle = reader.nextLine();
        for (int i = 0; i < Libro.getAllBooks().size(); i++) {
            if (Libro.getAllBooks().get(i).getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                String[] bookInfoString = new String[]{
                        Libro.getAllBooks().get(i).getTitle(),
                        Libro.getAllBooks().get(i).getISBN(),
                        Libro.getAllBooks().get(i).getPublisher(),
                        Libro.getAllBooks().get(i).getAuthor()
                };
                booksInfo.add(bookInfoString);
            }
        }
        if (!booksInfo.isEmpty()) {
            for (int i = 0; i < booksInfo.size(); i++) {
                System.out.println("Title: " + booksInfo.get(i)[0]);
                System.out.println("ISBN: " + booksInfo.get(i)[1]);
                System.out.println("Publisher: " + booksInfo.get(i)[2]);
                System.out.println("Author: " + booksInfo.get(i)[3]);
                System.out.println("_______________________________");
            }
            System.out.println("Total results: " + Integer.toString(booksInfo.size()));
        } else {
            System.out.println("No results available for the search: " + searchTitle);
        }
    }

    public boolean isAvailable() {
        if (this.getAvailableCopies() <= 0) {
            return false;
        } else {
            return true;
        }
    }


    //--DTO after this line--
    //Setters for the instance variables

    public void setISBN(Scanner reader, String ISBN) {
        boolean validISBN = true;
        Matcher matcher = pattern.matcher(ISBN);
        while (!matcher.matches() || !validISBN) {
            System.out.println("Enter the book ISBN: ");
            ISBN = reader.nextLine();
            matcher = pattern.matcher(ISBN);
            if (!matcher.matches()) {
                System.out.println("No valid ISBN format, please enter a format like n-nnn-nnnnn-n");
                reader.nextLine();
            }
        }
        this.ISBN = ISBN;
    }

    public void setTtile(Scanner reader) {

        String title = "";
        while (title == "" || title.length() < 4 || title.length() > 30) {
            System.out.println("Enter the book title: ");
            title = reader.nextLine();
            if (title == "" || title.length() < 4 || title.length() > 30) {
                System.out.println("Are you sure that this is a good title?");
            }
        }
        this.title = title;
    }

    public void setAuthor(Scanner reader) {
        String author = "";
        while (author == "" || author.length() < 4 || author.length() > 30) {
            System.out.println("Enter the book author: ");
            author = reader.nextLine();
            if (author == "" || author.length() < 4 || author.length() > 30) {
                System.out.println("Are you sure that this is a good author name?");
            }
        }
        this.author = author;
    }

    public void setPublisher(Scanner reader) {
        String publisher = "";
        while (publisher == "" || publisher.length() < 4 || publisher.length() > 30) {
            System.out.println("Enter the book publisher: ");
            publisher = reader.nextLine();
            if (publisher == "" || publisher.length() < 4 || publisher.length() > 30) {
                System.out.println("Are you sure that this is a good publisher name?");
            }
        }
        this.publisher = publisher;
    }

    public void setAvailableCopies(int copies) {
        this.availableCopies = copies;
    }

    public void addCopies(int copies) {
        this.totalCopies += copies;
        this.setAvailableCopies(getAvailableCopies() + copies);
    }

    public void setTotalCopies(int copies){
        this.totalCopies = copies;
    }


    //Getters for all the variables

    public String getISBN() {
        return this.ISBN;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public int getAllCopies() {
        return this.totalCopies;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public static List<Libro> getAllBooks() {
        return Libro.allBooks;

    }
}
