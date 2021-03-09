package ObjetosBiblioteca;

import Utilidades.Utils;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Libro
{

	String titulo;
	String autor;
	String isbn;
	String editorial;
	int copias;
	int copiasDisponibles;


	/* Constructores */

	public Libro()
	{}

	public Libro(String titulo, String autor, String isbn, String editorial, int copias, int copiasDisponibles)
	{
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setIsbn(isbn);
		this.setEditorial(editorial);
		this.setCopias(copias);
		this.setCopiasDisponibles(copiasDisponibles);
	}

	public Libro(Libro libro)
	{
		this.setTitulo(libro.getTitulo());
		this.setAutor(libro.getAutor());
		this.setIsbn(libro.getIsbn());
		this.setEditorial(libro.getEditorial());
		this.setCopias(libro.getCopias());
		this.setCopiasDisponibles(libro.getCopiasDisponibles());
	}



	/*
	* Métodos heredados
	*/


	@Override
	public String toString() {
		return  "Titulo: " + this.getTitulo() +
				", Autor: " + this.getAutor() +
				", Editorial: " + this.getEditorial() +
				", ISBN: " + this.getIsbn() +
				", Copias: " + this.getCopias() +
				", Copias disponibles: " + this.getCopiasDisponibles();
	}




	/*
	* Métodos de Libro
	*/


	public void toString2()
	{
		System.out.println("Titulo de la obra  : " + this.getTitulo());
		System.out.println("Autor de la obra   : " + this.getAutor());
		System.out.println("Editorial          : " + this.getEditorial());
		System.out.println("ISBN               : " + this.getIsbn());
		System.out.println("Total de copias    : " + this.getCopias());
		System.out.println("Copias disponibles : " + this.getCopiasDisponibles());
	}




	/**
	 * 	Método de búsqueda de libros por distintos modos, devuelve una lista de libros en la cual se encuentran
	 * 	los distintos resultados obtenidos en caso de haber más de uno.
	 *
	 * @param biblioteca | Introduce la biblioteca en la cual se debe realizar la búsqueda.
	 * @param filtro | Selecciona el filtro de búsqueda 'autor', 'isbn', 'titulo', 'editorial' o 'sinFiltro'.
	 * @param cadenaBusqueda | La cadena de texto la cual debe ser buscada.
	 * @return | Devuelve una lista de libros pertenecientes a la lista de la biblioteca.
	 */
	public static List<Libro> buscarLibros(Biblioteca biblioteca, String filtro, String cadenaBusqueda)
	{

		boolean encontrado = false;
		int i = 0;
		List<Libro> listaLibros = new ArrayList<>();


		while (!encontrado && i < biblioteca.getListaLibros().size())
		{
			Libro libroIterator = biblioteca.getListaLibros().get(i);


			switch (filtro)
			{
				case "isbn":
					if (libroIterator.getIsbn().equalsIgnoreCase(cadenaBusqueda))
					{
						listaLibros.add(libroIterator);
						encontrado = true;
					}
					break;

				case "titulo":
					if (libroIterator.getTitulo().toLowerCase().contains(cadenaBusqueda.toLowerCase()))
					{
						listaLibros.add(libroIterator);
					}
					break;

				case "autor":
					if (libroIterator.getAutor().toLowerCase().contains(cadenaBusqueda.toLowerCase()))
					{
						listaLibros.add(libroIterator);
					}
					break;

				case "editorial":
					if (libroIterator.getEditorial().toLowerCase().contains(cadenaBusqueda.toLowerCase()))
					{
						listaLibros.add(libroIterator);
					}
					break;

				case "sinFiltro":
					if (libroIterator.getIsbn().equalsIgnoreCase(cadenaBusqueda) ||
							libroIterator.getTitulo().toLowerCase().contains(cadenaBusqueda.toLowerCase()) ||
							libroIterator.getAutor().toLowerCase().contains(cadenaBusqueda.toLowerCase()) ||
							libroIterator.getEditorial().toLowerCase().contains(cadenaBusqueda.toLowerCase())
					)
					{
						listaLibros.add(libroIterator);
					}
					break;

				default:
					System.out.println("<<Trata de selecionar un modo : 'isbn', 'titulo', 'autor', etc.>>");
			}

			i++;
		}

		return listaLibros;
	}




	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public void setCopiasDisponibles(int copiasDisponibles)
	{
		if (copiasDisponibles <= this.copias)
		{
			this.copiasDisponibles = copiasDisponibles;
		}
		else
		{
			System.out.println("<<Error, no puede haber más copias disponibles que copias totales>>");
		}
	}

	public void setCopias(int copias)
	{
		this.copias = copias;
	}

	public void setEditorial(String editorial)
	{
		if (Utils.comprobarLongitudCadena(editorial, 30))
		{
			this.editorial = editorial;
		}
	}

	public void setIsbn(String isbn)
	{
		if (Utils.comprobarLongitudCadena(isbn, 15))
		{
			this.isbn = isbn;
		}
	}

	public void setAutor(String autor)
	{
		if (Utils.comprobarSoloLetras(autor) && Utils.comprobarLongitudCadena(autor, 30))
		{
			this.autor = autor;
		}
	}

	public void setTitulo(@NotNull String titulo)
	{
		if (Utils.comprobarLongitudCadena(titulo, 30) && titulo.length() > 0)
		{
			this.titulo = titulo;
		}
	}



	/*
	 * Getters
	 */

	public int getCopiasDisponibles() {return this.copiasDisponibles;}

	public int getCopias() {return this.copias;}

	public String getEditorial() {return this.editorial;}

	public String getIsbn() {return this.isbn;}

	public String getAutor() {return this.autor;}

	public String getTitulo() {return this.titulo;}

	public boolean estaDisponible(){return this.getCopiasDisponibles() > 0;}

}
