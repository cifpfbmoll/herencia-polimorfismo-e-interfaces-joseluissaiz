import java.util.Scanner;

public class Menu {
    public static Scanner reader = new Scanner(System.in);
    private static Biblioteca Library = new Biblioteca();
    private static Bibliotecario Librarian = new Bibliotecario();
    private static Usuario User = new Usuario();
    private static String levelAccess;


    public static void main(String[] args) {
        Library.setLibraryName(reader);
        Library.initializeLibrary();
        showLoginMenu();
        levelAccess = "User";
    }


    /* Show Menu functions */

    public static void showLoginMenu() {
        String choice = "";
        while (choice != "9") {
            int[] authInfo;
            boolean loginSuccessful = false;
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("LOGIN IS REQUIRED TO CONTINUE");
            System.out.println("");
            System.out.println("1- Login as Librarian");
            System.out.println("2- Login as User");
            System.out.println("9- Exit");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = reader.nextLine();
            switch (choice) {
                case "1":
                    while (!loginSuccessful) {
                        clearScreen();
                        authInfo = Bibliotecario.auth(reader);
                        if (authInfo[0] == 1) { //If login success
                            Librarian = (Bibliotecario) Persona.getAllPersons().get(authInfo[1]);  //set user
                            Librarian.setLoginStatus(true);
                            loginSuccessful = true;
                            levelAccess = "Librarian";
                        } else {
                            System.out.println("---Access denied---");
                            reader.nextLine();
                        }
                    }
                    showMainMenu();
                    break;
                case "2":
                    while (!loginSuccessful) {
                        clearScreen();
                        authInfo = Usuario.auth(reader);
                        if (authInfo[0] == 1) { //If loggin success
                            User = (Usuario) Persona.getAllPersons().get(authInfo[1]); //set user
                            User.setLoginStatus(true);
                            loginSuccessful = true;
                            levelAccess = "User";
                        } else {
                            System.out.println("---Access denied---");
                            reader.nextLine();
                        }
                    }
                    showMainMenu();
                    break;
                case "9":
                    System.exit(0);
            }
        }
    }

    public static void showMainMenu() {
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            if (levelAccess.equals("Librarian")) {
                System.out.println("1- Library manager");
                System.out.println("2- User manager");
                System.out.println("3- Book manager");
                System.out.println("4- Reserve manager");
            } else if (levelAccess.equals("User")) {
                System.out.println("1- Book search");
                System.out.println("2- Reserve manager");
            }
            System.out.println("7- Change password");
            System.out.println("8- Log out");
            System.out.println("9- Exit");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = reader.nextLine();
            switch (choice) {
                case "1":
                    if (levelAccess.equals("Librarian")) {
                        showLibraryMenu();
                    } else if (levelAccess.equals("User")) {
                        showBookMenu();
                    }
                    break;
                case "2":
                    if (levelAccess.equals("Librarian")) {
                        showUserMenu();
                    } else if (levelAccess.equals("User")) {
                        showReserveMenu();
                    }
                    break;
                case "3":
                    if (levelAccess.equals("Librarian")) {
                        showBookMenu();
                    } else if (levelAccess.equals("User")) {
                        continue;
                    }
                    break;
                case "4":
                    if (levelAccess.equals("Librarian")) {
                        showReserveMenu();
                    } else if (levelAccess.equals("User")) {
                        continue;
                    }
                    break;
                case "7":
                    if (levelAccess.equals("Librarian")){
                        Librarian.changePassword(reader);
                    } else if (levelAccess.equals("User")){
                        User.changePassword(reader);
                    }
                    reader.nextLine();
                    break;
                case "8":
                    showLoginMenu();
                case "9":
                    System.exit(0);
            }
        }
    }

    public static void showLibraryMenu() {
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            System.out.println("1- List all books");
            System.out.println("2- List all available books");
            System.out.println("3- List all staff");
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    Library.showAllBooks();
                    reader.nextLine();
                    break;
                case "2":
                    Library.showAvailableBooks();
                    reader.nextLine();
                    break;
                case "3":
                    Bibliotecario.showAllLibrarian();
                    reader.nextLine();
                    break;
                case "9":
                    showMainMenu();
                    break;
            }
        }
    }

    public static void showUserMenu() {
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            System.out.println("1- Create a new user");
            System.out.println("2- Delete user");
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    showCreateUserMenu();
                    break;
                case "2":
                    showDeleteUserMenu();
                case "9":
                    showMainMenu();
                    break;
            }
        }
    }

    public static void showCreateUserMenu(){
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            System.out.println("1- Create a new Librarian");
            System.out.println("2- Create a new User");
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    Bibliotecario.createNewLibrarian(reader);
                    break;
                case "2":
                    Usuario.createNewUser(reader);
                    break;
                case "9":
                    showUserMenu();
                    break;
            }
        }
    }

    public static void showDeleteUserMenu(){
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            System.out.println("1- Delete Librarian");
            System.out.println("2- Delete User");
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    Librarian.deleteLibrarian(reader);
                    break;
                case "2":
                    Usuario.deleteUser(reader);
                    break;
                case "9":
                    showUserMenu();
                    break;
            }
        }
    }

    public static void showBookMenu() {
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            if (levelAccess.equals("Librarian")) {
                System.out.println("1- Add new book");
                System.out.println("2- Delete book");
                System.out.println("3- Search book by title");
                System.out.println("4- Add a book variant");
            } else if (levelAccess.equals("User")) {
                System.out.println("1- Search book by title");
            }
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    if (levelAccess.equals("Librarian")) {
                        Libro.addBook(reader);
                        Library.setLibraryBookList(Libro.getAllBooks()); //Update the current list
                        reader.nextLine();
                    } else if (levelAccess.equals("User")) {
                        Libro.searchBookByTitle(reader);
                        reader.nextLine();
                    }
                    break;
                case "2":
                    if (levelAccess.equals("Librarian")) {
                        showDeleteBookMenu();
                    } else if (levelAccess.equals("User")) {
                        continue;
                    }
                    break;
                case "3":
                    if (levelAccess.equals("Librarian")) {
                        Libro.searchBookByTitle(reader);
                        reader.nextLine();
                    } else if (levelAccess.equals("User")) {
                        continue;
                    }
                    break;
                case "4":
                    if (levelAccess.equals("Librarian")){
                        Libro.addBookCopy(reader);
                        Library.setLibraryBookList(Libro.getAllBooks()); //Update the current list
                        reader.nextLine();
                    } else if (levelAccess.equals("User")){
                        continue;
                    }
                    break;
                case "9":
                    showMainMenu();
                    break;
            }
        }
    }

    public static void showDeleteBookMenu(){
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Delete book");
            System.out.println("_____________________________________");
            System.out.println("Do you want to delete one copy or all copies?");
            System.out.println("");
            System.out.println("1- One copy");
            System.out.println("2- All copies (delete this book completely)");
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    Libro.deleteBook(reader, "oneCopy");
                    reader.nextLine();
                    break;
                case "2":
                    Libro.deleteBook(reader, "allCopies");
                    reader.nextLine();
                    break;
                case "9":
                    showBookMenu();
                    break;
            }
        }
    }

    public static void showReserveMenu(){
        String choice = "";
        while (choice != "9") {
            clearScreen();
            System.out.println("Library manager v1.0");
            System.out.println("______________________________");
            System.out.println("Select one option please");
            System.out.println("");
            if (levelAccess.equals("Librarian")){
                System.out.println("1- Make a reserve");
                System.out.println("2- Search user reserves");
                System.out.println("3- Return book");
            } else if (levelAccess.equals("User")){
                System.out.println("1- My reserves");
            }
            System.out.println("9- Back");
            System.out.println("______________________________");
            System.out.println("Choice: ");
            choice = Menu.reader.nextLine();
            switch (choice) {
                case "1":
                    if (levelAccess.equals("Librarian")){
                        Reserva.makeReserve(reader);
                    } else if (levelAccess.equals("User")){
                        User.showUserReserves();
                        reader.nextLine();
                    }
                    break;
                case "2":
                    if (levelAccess.equals("Librarian")){
                        Bibliotecario.searchUserReserves(reader);
                    } else if (levelAccess.equals("User")){
                        continue;
                    }
                    break;
                case "3":
                    if (levelAccess.equals("Librarian")){
                        Reserva.returnBook(reader);
                    } else if (levelAccess.equals("User")){
                        continue;
                    }
                    break;
                case "9":
                    showMainMenu();
                    break;
            }
        }
    }


    /* functionality */

    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}
