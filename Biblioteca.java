import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private String libraryName;
    private List<Libro> bookList = new ArrayList<Libro>();


    //Constructor methods

    public Biblioteca(){}

    public Biblioteca(String libraryName, List<Libro> bookList) {
        this.libraryName = libraryName;
        this.bookList = bookList;
    }

    public Biblioteca(final Biblioteca Library) {
        this.libraryName = Library.getlibraryName();
        this.bookList = Library.getBookList();
    }


    /* Object methods */

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }


    //Library functions

    public void showAllBooks() {
        System.out.println("Listing all books in " + this.getlibraryName());
        System.out.println("____________________________________");
        for(int i = 0; i < this.getBookList().size(); i++) {
            System.out.println("Title: " + this.getBookList().get(i).getTitle());
            System.out.println("ISBN: " + this.getBookList().get(i).getISBN());
            System.out.println("Author: " + this.getBookList().get(i).getAuthor());
            System.out.println("Publisher: " + this.getBookList().get(i).getPublisher());
            System.out.println("______________________________");
        }
        System.out.println("Total books :" + this.getBookList().size());
    }

    public void showAvailableBooks() {
        System.out.println("Disponible books");
        System.out.println("____________________________________");
        for (int i = 0; i < Libro.getAllBooks().size(); i++) {
            if (Libro.getAllBooks().get(i).isAvailable()){
                Libro.getAllBooks().get(i).toString2();
            }
        }
    }


    //--DTO after this line--
    //Setters for the instance variables

    public void setLibraryName(Scanner reader) {
        String libraryName = " ";
        while (libraryName.substring(0, 1) == " " || libraryName == "") {
            System.out.println("Set the Library name: ");
            libraryName = reader.nextLine();
            if (libraryName.substring(0, 1) == " " || libraryName == "") {
                System.out.println("Are you sure that this is a good library name?");
            }
        }
        String libraryNameFormatted = libraryName.substring(0, 1).toUpperCase() + libraryName.substring(1);
        this.libraryName = libraryNameFormatted;
    }

    public void setLibraryBookList(List<Libro> bookList) {
        this.bookList = bookList;
    }


    //Getters for all the variables

    public String getlibraryName() {
        return this.libraryName;
    }

    public List<Libro> getBookList(){
        return this.bookList;
    }


    /* >>>> For testing <<<< */

    public void initializeLibrary(){
        Bibliotecario Librarian = new Bibliotecario("Jose",
                "Saiz",20,"1234","4321","cca");
        Persona.getAllPersons().add(Librarian);
        Usuario User = new Usuario("Pedro", "Ruiz", 34,
                "600", "Calle Mayor nº4", "07100", "pruiz") ;
        Persona.getAllPersons().add(User);
        Libro libroPrueba = new Libro("3-333-33333-3", "Cayendo hacia arriba",
                "Clara Ribas", "Pompey", 20,10);
        Libro.getAllBooks().add(libroPrueba);

    }
}
