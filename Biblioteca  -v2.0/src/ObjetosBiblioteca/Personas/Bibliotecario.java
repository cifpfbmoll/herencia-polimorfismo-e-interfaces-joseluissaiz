package ObjetosBiblioteca.Personas;

import Utilidades.Utils;
import com.sun.istack.internal.NotNull;

import java.util.Scanner;

public class Bibliotecario extends Persona{

	private String nif;
	private String contraseña;
	private String puestoTrabajo;
	private boolean conectado;

	private final Scanner reader = new Scanner(System.in);

	public Bibliotecario()
	{}

	public Bibliotecario(String nombre, String apellidos, int edad, String nif, String contraseña, String puestoTrabajo){
		super(nombre, apellidos, edad);
		this.setNif(nif);
		this.setContraseña(contraseña);
		this.setPuestoTrabajo(puestoTrabajo);
		this.setConectado(false);
	}


	public Bibliotecario(Bibliotecario bibliotecario)
	{
		super(bibliotecario.getNombre(), bibliotecario.getApellidos(), bibliotecario.getEdad());
		this.setNif(bibliotecario.getNif());
		this.setContraseña(bibliotecario.getContraseña());
		this.setPuestoTrabajo(bibliotecario.getPuestoTrabajo());
	}



	/*
	* Métodos heredados
	*/

	@Override
	public String toString() {
		return "NIF : " + this.getNif() +
				", Cargo : " + this.getPuestoTrabajo();
	}


	@Override
	public void solicitarDatosPersona() {
		super.solicitarDatosPersona();
		System.out.print("Introduce el NIF : ");
		this.setNif(reader.nextLine());
		if (this.getNif() != null)
		{
			System.out.print("Introduce una contraseña : ");
			this.setContraseña(reader.nextLine());
			if (this.getContraseña() != null)
			{
				System.out.print("Introduce el cargo : ");
				this.setPuestoTrabajo(reader.nextLine());
			}
		}
	}

	@Override
	public void cambiarContraseña(String contraseña) {
		this.setContraseña(contraseña);
	}




	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public void setNif(@NotNull String nif)
	{
		if (Utils.comprobarLongitudCadena(nif, 10) && nif.length() > 0)
		{
			this.nif = nif;
		}
	}

	public void setContraseña(@NotNull String contraseña)
	{
		if (contraseña.length() >= 4)
		{
			this.contraseña = contraseña;
		}
	}

	public void setPuestoTrabajo(@NotNull String puestoTrabajo)
	{
		if (puestoTrabajo.length() > 0)
		{
			this.puestoTrabajo = puestoTrabajo;
		}
	}

	public void setConectado(boolean conectado)
	{
		this.conectado = conectado;
	}



	/*
	 * Getters
	 */

	public String getNif() {return this.nif;}

	public String getContraseña() {return this.contraseña;}

	public String getPuestoTrabajo() {return this.puestoTrabajo;}

	public boolean estaConectado() {return this.conectado;}


}
