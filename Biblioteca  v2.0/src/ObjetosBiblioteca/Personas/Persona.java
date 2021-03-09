package ObjetosBiblioteca.Personas;

import Utilidades.Utils;

import java.util.Scanner;

/**
 * Clase abstracta de Persona, sirve para organizar las distintas clases que van a depender de ella, ya que de esta
 * forma los Bibliotecarios y Usuarios comparten estos métodos y atributos, así, conseguimos simplificar el código o
 * bien poner distintas reestricciones u oblligaciones sobre las clases que hereden de Persona.
 */
public abstract class Persona
{

	private final Scanner reader = new Scanner(System.in);

	private String nombre;
	private String apellidos;
	private int edad;


	/* Constructores */

	public Persona()
	{}

	public Persona(String nombre, String apellidos, int edad) {
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setEdad(edad);
	}

	public Persona(Persona persona)
	{
		this.setNombre(persona.getNombre());
		this.setApellidos(persona.getApellidos());
		this.setEdad(persona.getEdad());
	}



	/*
	* Métodos heredados
	*/

	@Override
	public String toString() {
		return "Nombre : " + this.getNombre() +
				", Apellidos : " + this.getApellidos() +
				", Edad : " + this.getEdad();
	}




	/*
	 * Métodos de persona
	 */


	public void solicitarDatosPersona()
	{
		System.out.print("Introduce el nombre : ");
		this.setNombre(reader.nextLine());
		if (this.getNombre() != null)
		{
			System.out.print("Introduce los apellidos : ");
			this.setApellidos(reader.nextLine());
			if (this.getApellidos() != null)
			{
				System.out.print("Introduce la edad : ");
				try
				{
					this.setEdad(Integer.parseInt(reader.nextLine()));
				}
				catch (NumberFormatException e)
				{
					System.out.println("<<< No es una edad válida >>>");
				}

			}
		}
	}

	public abstract void cambiarContraseña(String contraseña);



	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public void setNombre(String nombre)
	{
		if (Utils.comprobarSoloLetras(nombre) && Utils.comprobarLongitudCadena(nombre, 30))
		{
			this.nombre = nombre;
		}
	}

	public void setApellidos(String apellidos)
	{
		if (Utils.comprobarSoloLetras(apellidos) && Utils.comprobarLongitudCadena(apellidos, 30))
		{
			this.apellidos = apellidos;
		}
	}

	public void setEdad(int edad)
	{
		if (edad > 18)
		{
			this.edad = edad;
		}
	}


	/*
	 * Getters
	 */

	public String getNombre() { return this.nombre; }

	public String getApellidos() { return this.apellidos; }

	public int getEdad() { return this.edad; }

}
