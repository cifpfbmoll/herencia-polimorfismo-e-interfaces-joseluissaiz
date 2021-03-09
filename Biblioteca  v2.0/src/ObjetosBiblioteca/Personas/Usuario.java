package ObjetosBiblioteca.Personas;

import ObjetosBiblioteca.Reserva;
import Utilidades.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario extends Persona
{

	private String correo;
	private String telefono;
	private String direccion;
	private List<Reserva> listaReservas = new ArrayList<>();

	private final Scanner reader = new Scanner(System.in);


	/* Constructores */

	public Usuario()
	{}

	public Usuario(String nombre, String apellidos, int edad, String correo, String telefono, String direccion)
	{
		super(nombre, apellidos, edad);
		this.setCorreo(correo);
		this.setTelefono(telefono);
		this.setDireccion(direccion);
	}

	public Usuario(Usuario usuario)
	{
		super(usuario.getNombre(), usuario.getApellidos(), usuario.getEdad());
		this.setCorreo(usuario.getCorreo());
		this.setTelefono(usuario.getTelefono());
		this.setDireccion(usuario.getDireccion());
		this.setListaReservas(usuario.getListaReservas());
	}



	/*
	*  Métodos heredados
	*/

	@Override
	public String toString()
	{
		return "Nombre : " + this.getNombre() +
				", Apellidos : " + this.getApellidos() +
				", Edad : " + this.getEdad() +
				", Correo : " + this.getCorreo() +
				", Telefono : " + this.getTelefono() +
				", Direccion : " + this.getDireccion();
	}

	@Override
	public void solicitarDatosPersona() {
		super.solicitarDatosPersona();
		System.out.print("Introduce el Email : ");
		this.setCorreo(reader.nextLine());
		if (this.getCorreo() != null)
		{
			System.out.print("Introduce un número de teléfono : ");
			this.setTelefono(reader.nextLine());
			if (this.getTelefono() != null)
			{
				System.out.print("Especifica una dirección : ");
				this.setDireccion(reader.nextLine());
			}
		}
	}

	@Override
	public void cambiarContraseña(String contraseña)
	{
		this.setTelefono(contraseña);
	}



	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public void setCorreo(String correo)
	{
		if (Utils.comprobarLongitudCadena(correo, 30))
		{
			this.correo = correo;
		}
	}

	public void setTelefono(String telefono)
	{
		if (Utils.comprobarLongitudCadena(telefono, 10))
		{
			this.telefono = telefono;
		}
	}

	public void setDireccion(String direccion)
	{
		if (Utils.comprobarLongitudCadena(direccion, 30))
		{
			this.direccion = direccion;
		}
	}

	public void setListaReservas(List<Reserva> listaReservas) { this.listaReservas = listaReservas; }


	/*
	 * Getters
	 */

	public String getCorreo() { return this.correo; }

	public String getTelefono() { return this.telefono; }

	public String getDireccion() { return this.direccion; }

	public List<Reserva> getListaReservas() { return this.listaReservas; }

}
