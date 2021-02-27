import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario extends Persona {
    private String phoneNumber;
    private String address;
    private String CP;
    private String emailAddress;
    private List<Reserva> allReserves = new ArrayList<Reserva>();
    private static List<Usuario> allUsers = new ArrayList<Usuario>();
    private boolean loginStatus;


    /* Constructor methods */

    public Usuario() {

    }

    public Usuario(String firstName, String lastName, int age, String phoneNumber, String address, String CP, String emailAddress) {
        super(firstName, lastName, age);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.CP = CP;
        this.emailAddress = emailAddress;
        this.loginStatus = false;
    }

    public Usuario(Usuario User) {
        super();
        this.phoneNumber = User.getPhoneNumber();
        this.address = User.getAddress();
        this.CP = User.getCP();
        this.emailAddress = User.getEmailAddress();
        this.loginStatus = User.getLoginStatus();
    }


    /* Object methods */

    @Override
    public String toString() {
        return super.toString();
    }


    /* Instantiate methods */

    @Override
    protected void solicitarDatosPersona(Scanner reader, Persona User) {
        System.out.println("Create a new User");
        System.out.println("______________________");
        super.solicitarDatosPersona(reader, User);
        System.out.println("Insert phone number");
        ((Usuario) User).setPhoneNumber(reader.nextLine());
        System.out.println("Insert address");
        ((Usuario) User).setAddress(reader.nextLine());
        System.out.println("Insert Postal Code");
        ((Usuario) User).setCP(reader.nextLine());
        System.out.println("Insert Email address");
        ((Usuario) User).setEmailAddress(reader.nextLine());
        System.out.println("New User created as " + ((Usuario) User).getFirstName());
        System.out.println("______________________");
        reader.nextLine();
    }

    @Override
    public void changePassword(Scanner reader) {
        System.out.println("Change " + this.getFirstName() + " telephone number");
        System.out.println("____________________________________________");
        System.out.println("Enter the current telephone number");
        String currentPhoneNumber = reader.nextLine();
        if (currentPhoneNumber.equals(this.getPhoneNumber())){
            System.out.println("Enter the new telephone number");
            String newPhoneNumber = reader.nextLine();
            if (!newPhoneNumber.equals(currentPhoneNumber)){
                System.out.println("Confirm the new telephone number");
                String confirmNewPhoneNumber = reader.nextLine();
                if (confirmNewPhoneNumber.equals(newPhoneNumber)){
                    this.setPhoneNumber(newPhoneNumber);
                    System.out.println(this.getFirstName() + " >>>>> telephone number >>>>> actualized");
                } else {
                    System.out.println("<<< Numbers don't match >>>");
                }
            } else {
                System.out.println("<<< The new phone number cannot be equal than the current one >>>");
            }
        } else {
            System.out.println("<<< Access denied >>>");
        }
    }

    public static void createNewUser(Scanner reader) {
        Usuario newUser = new Usuario("", "", 0, "", "", "", "");
        newUser.solicitarDatosPersona(reader, newUser);
        Persona.getAllPersons().add(newUser);
    }

    public static int[] auth(Scanner reader) {
        int[] authInfo = new int[2];
        System.out.println("User login");
        System.out.println("______________________");
        System.out.println("Insert email address");
        String emailAddress = reader.nextLine();
        System.out.println("Insert phone number");
        String phoneNumber = reader.nextLine();
        authInfo[0] = -1;
        int i = 0;
        while (authInfo[0] == -1 && i < Persona.getAllPersons().size()) {
            if (Persona.getAllPersons().get(i) instanceof Usuario){
                Usuario UserItr = (Usuario) Persona.getAllPersons().get(i);
                if (UserItr.getEmailAddress().equals(emailAddress) && UserItr.getPhoneNumber().equals(phoneNumber)){
                    System.out.println("Loggeed in as " + UserItr.getFirstName());
                    authInfo[0] = 1; //Adding result if its true
                    authInfo[1] = i; //Adding login index in list
                    reader.nextLine();
                }
            }
            i++;

        }
        return authInfo;
    }


    /* User functions */

    public void showUserReserves(){
        System.out.println("Showing all reserves of " + this.getFirstName());
        System.out.println("____________________________________");
        Reserva.showUserReserves(this);
    }

    public static int searchUserPosition(String phoneNumber, String emailAddress){
        int UserPosition = -1;
        boolean found = false;
        int i = 0;
        while (!found && i < Persona.getAllPersons().size()){
            if (Persona.getAllPersons().get(i) instanceof Usuario){
                Usuario UserIterator = (Usuario)Persona.getAllPersons().get(i);
                if (UserIterator.getPhoneNumber().equals(phoneNumber) && UserIterator.getEmailAddress().equals(emailAddress)){
                    found = true;
                    UserPosition = i;
                }
            }
            i++;
        }
        return UserPosition;
    }

    public static int searchUserPositionByEmail(String emailAddress){
        int UserPosition = -1;
        boolean found = false;
        int i = 0;
        while (!found && i < Persona.getAllPersons().size()){
            if (Persona.getAllPersons().get(i) instanceof Usuario){
                Usuario UserIterator = (Usuario)Persona.getAllPersons().get(i);
                if (UserIterator.getEmailAddress().equals(emailAddress)){
                    found = true;
                    UserPosition = i;
                }
            }
            i++;
        }
        return UserPosition;
    }

    public static void deleteUser(Scanner reader){
        System.out.println("Delete User");
        System.out.println("______________________");
        System.out.println("Enter the email address");
        String emailAddress = reader.nextLine();
        int userPosition = Usuario.searchUserPositionByEmail(emailAddress);
        if (userPosition != -1){
            Usuario User = (Usuario) Persona.getAllPersons().get(userPosition);
            System.out.println("<<<<< Deleted User named " + User.getFirstName() + " >>>>>");
            Persona.getAllPersons().remove(userPosition);
        } else {
            System.out.println("<<< There is no User with the email address " + emailAddress + " >>>");
        }
        reader.nextLine();
    }


    //--DTO after this line--
    //Setters for the instance variables

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setLoginStatus(boolean status){
        this.loginStatus = status;
    }


    //Getters for all the variables

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCP() {
        return this.CP;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public boolean getLoginStatus() {
        return this.loginStatus;
    }

    public static List<Usuario> getList() {
        return allUsers;
    }

    public List<Reserva> getAllReserves(){
        return this.allReserves;
    }
}
