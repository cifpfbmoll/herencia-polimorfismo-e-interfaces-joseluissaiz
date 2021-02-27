import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bibliotecario extends Persona {
    private boolean loginStatus;
    private String password;
    private String NIF;
    private String job;


    /* Constructor methods */

    public Bibliotecario() {
        super();
    }

    public Bibliotecario(String firstName, String lastName, int age, String NIF, String password, String job) {
        super(firstName, lastName, age);
        this.NIF = NIF;
        this.password = password;
        this.loginStatus = false;
        this.job = job;
    }

    public Bibliotecario(Bibliotecario Librarian) {
        super();
        this.loginStatus = Librarian.getLoginStatus();
        this.NIF = Librarian.getNIF();
        this.password = Librarian.getPassword();

    }


    /* Persona methods */

    @Override
    public String toString() {
        return this.toString();
    }

    public void toString2(){
        System.out.println("Name: " + this.getFirstName() + " " + this.getLastName());
        System.out.println("NIF: " + this.getNIF());
        System.out.println("Age: " + this.getAge());
        System.out.println("Job: " + this.getJob());
        if (this.getLoginStatus()){
            System.out.println("Login status: Connected");
        } else {
            System.out.println("Login status: Disconnected");
        }
        System.out.println("___________________________________________");
    }

    public static void showAllLibrarian(){
        System.out.println("Listing all staff");
        System.out.println("____________________________________");
        List<Bibliotecario> librarianList = getAllLibrarianList();
        for (int i = 0; i < librarianList.size(); i++){
            Bibliotecario LibrarianIterator = librarianList.get(i);
            LibrarianIterator.toString2();
        }
    }

    @Override
    protected void solicitarDatosPersona(Scanner reader, Persona Librarian) {
        System.out.println("Create a new Librarian");
        System.out.println("______________________");
        super.solicitarDatosPersona(reader, Librarian);
        System.out.println("Insert NIF");
        ((Bibliotecario) Librarian).setNIF(reader.nextLine());
        System.out.println("Insert password");
        ((Bibliotecario) Librarian).setPassword(reader.nextLine());
        System.out.println("Insert Job");
        ((Bibliotecario) Librarian).setJob(reader.nextLine());
        System.out.println("New Librarian created named " + Librarian.getFirstName());
        System.out.println("______________________");
        reader.nextLine();
    }

    @Override
    public void changePassword(Scanner reader) {
        System.out.println("Change " + this.getFirstName() + " password");
        System.out.println("____________________________________________");
        System.out.println("Enter the current password");
        String currentPassword = reader.nextLine();
        if (currentPassword.equals(this.getPassword())){
            System.out.println("Enter the new password");
            String newPassword = reader.nextLine();
            if (!newPassword.equals(currentPassword)){
                System.out.println("Confirm the new password");
                String confirmNewPassword = reader.nextLine();
                if (confirmNewPassword.equals(newPassword)){
                    this.setPassword(newPassword);
                    System.out.println(this.getFirstName() + " >>>>> password >>>>> actualized");
                } else {
                    System.out.println("<<< Passwords don't match >>>");
                }
            } else {
                System.out.println("<<< The new password cannot be equal than the current one >>>");
            }
        } else {
            System.out.println("<<< Access denied >>>");
        }
    }

    public static int searchLibrarianPosition(String NIF){
        int librarianPosition = -1;
        boolean found = false;
        int i = 0;
        while (!found && i < Persona.getAllPersons().size()) {
            if (Persona.getAllPersons().get(i) instanceof Bibliotecario) {
                Bibliotecario LibrarianIterator = (Bibliotecario) Persona.getAllPersons().get(i);
                if (LibrarianIterator.getNIF().equals(NIF)) {
                    found = true;
                    librarianPosition = i;
                }
            }
            i++;
        }
        return librarianPosition;
    }

    public static int[] auth(Scanner reader) {
        int[] authInfo = new int[2];
        System.out.println("Librarian login");
        System.out.println("______________________");
        System.out.println("Insert NIF");
        String NIF = reader.nextLine();
        System.out.println("Insert password");
        String password = reader.nextLine();
        authInfo[0] = -1;
        int i = 0;
        while (authInfo[0] == -1 && i < Persona.getAllPersons().size()) {
            if (Persona.getAllPersons().get(i) instanceof Bibliotecario) {
                Bibliotecario LibrarianItr = (Bibliotecario) Persona.getAllPersons().get(i);
                if (LibrarianItr.getNIF().equals(NIF) && LibrarianItr.getPassword().equals(password)) {
                    System.out.println("Loggeed in as " + LibrarianItr.getFirstName());
                    authInfo[0] = 1; //Adding result if its true
                    authInfo[1] = i; //Adding login index in list
                    reader.nextLine();
                }
            }
            i++;

        }
        return authInfo;
    }

    public static List<Bibliotecario> getAllLibrarianList(){
        List<Bibliotecario> librarianList = new ArrayList<>();
        for (int i = 0; i < Persona.getAllPersons().size(); i++){
            if (Persona.getAllPersons().get(i) instanceof Bibliotecario){
                librarianList.add((Bibliotecario) Persona.getAllPersons().get(i));
            }
        }
        return librarianList;
    }


    //Librarian functions

    public static void searchUserReserves(Scanner reader) {
        System.out.println("Search reserve");
        System.out.println("_____________________");
        System.out.println("Enter the user email");
        String email = reader.nextLine();
        int userPosition = Usuario.searchUserPositionByEmail(email);
        if (userPosition != -1) {
            Usuario User = (Usuario) Persona.getAllPersons().get(userPosition);
            System.out.println(User.getFirstName() + " all reserves");
            System.out.println("_______________________________");
            Reserva.showUserReserves(User);
            reader.nextLine();
        } else {
            System.out.println("User " + email + " not found <<<<<");
            reader.nextLine();
        }
    }

    public static void createNewLibrarian(Scanner reader) {
        Bibliotecario newLibrarian = new Bibliotecario();
        newLibrarian.solicitarDatosPersona(reader, newLibrarian);
        Persona.getAllPersons().add(newLibrarian);
    }

    public void deleteLibrarian(Scanner reader) {
        System.out.println("Delete Librarian");
        System.out.println("______________________");
        System.out.println("Enter the NIF");
        String NIF = reader.nextLine();
        int librarianPosition = searchLibrarianPosition(NIF);
        if (librarianPosition != -1){
            Bibliotecario Librarian = (Bibliotecario) Persona.getAllPersons().get(librarianPosition);
            if (!Librarian.getNIF().equals(this.getNIF())){
                System.out.println("<<<<< Deleted Librarian named " + Librarian.getFirstName() + " >>>>>");
                Persona.getAllPersons().remove(librarianPosition);
            } else {
                System.out.println("<<<<< You cannot delete your own account >>>>>");
            }
        } else {
            System.out.println("<<< There is no Librarian with the NIF " + NIF + " >>>");
        }
        reader.nextLine();
    }


    //--DTO after this line--
    //Setters for the instance variables

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginStatus(boolean status) {
        this.loginStatus = status;
    }

    public void setJob(String job) {
        this.job = job;
    }


    //Getters for all the variables

    public boolean getLoginStatus() {
        return this.loginStatus;
    }

    public String getNIF() {
        return this.NIF;
    }

    public String getPassword() {
        return this.password;
    }

    public String getJob() {
        return this.job;
    }
}
