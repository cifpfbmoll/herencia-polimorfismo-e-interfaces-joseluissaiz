import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Persona {
    private String firstName;
    private String lastName;
    private int age;
    private static List<Persona> allPersons = new ArrayList<Persona>();


    /* Constructor methods */

    public Persona() {

    }

    public Persona(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Persona(Persona persona) {
        this.firstName = persona.getFirstName();
        this.lastName = persona.getLastName();
        this.age = persona.getAge();
    }


    /* Object methods */

    @Override
    public String toString() {
        return super.toString();
    }


    /* Class methods */

    protected void solicitarDatosPersona(Scanner reader, Persona person) {
        System.out.println("First name");
        this.setFirstName(reader.nextLine());
        System.out.println("Last name");
        this.setLastName(reader.nextLine());
        System.out.println("Age");
        this.setAge(Integer.parseInt(reader.nextLine()));
    }

    public abstract void changePassword(Scanner reader);


    //--DTO after this line--
    //Setters for the instance variables

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }


    //Getters for all the variables

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public static List<Persona> getAllPersons() {
        return allPersons;
    }
}
