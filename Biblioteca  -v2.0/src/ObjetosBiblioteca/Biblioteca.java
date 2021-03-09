package ObjetosBiblioteca;

import ObjetosBiblioteca.Personas.Persona;
import Utilidades.Utils;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	String nombre;
	List<Libro> listaLibros = new ArrayList<>();
	List<Persona> listaPersonas = new ArrayList<>();


	/* Constructores */

	public Biblioteca()
	{}

	public Biblioteca(String nombre)
	{
		this.setNombre(nombre);
	}

	public Biblioteca(Biblioteca biblioteca)
	{
		this.setNombre(biblioteca.getNombre());
	}


	@Override
	public String toString()
	{
		return super.toString() + "Nombre: " + this.getNombre();
	}




	/*
	* DTO AFTER THIS LINE
	*/


	/*
	 * Setters
	 */

	public void setNombre(String nombre)
	{
		if (Utils.comprobarLongitudCadena(nombre, 30) && Utils.comprobarSoloLetras(nombre))
		{
			this.nombre = nombre;
		}
		else
		{
			System.out.println("<<Error al introducir " + nombre + " como nombre>>");
			System.out.println("<<Por favor revise la longitud e inténtelo de nuevo>>");

		}
	}

	public void setListaLibros(List<Libro> listaLibros) {this.listaLibros = listaLibros;}

	public void setListaPersonas(List<Persona> listaPersonas) {this.listaPersonas = listaPersonas;}

	/*
	 * Getters
	 */

	public String getNombre() {return this.nombre;}

	public List<Libro> getListaLibros() {return this.listaLibros;}

	public List<Persona> getListaPersonas() {return this.listaPersonas;}

}
