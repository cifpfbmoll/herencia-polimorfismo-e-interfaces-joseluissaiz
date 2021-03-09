package Menus.GestorObjetos;

import Menus.Paneles.MenuBibliotecario;
import Menus.Paneles.MenuLogin;
import Menus.Paneles.MenuUsuario;
import ObjetosBiblioteca.Biblioteca;
import ObjetosBiblioteca.Libro;
import ObjetosBiblioteca.Personas.Bibliotecario;
import ObjetosBiblioteca.Personas.Persona;
import ObjetosBiblioteca.Personas.Usuario;
import ObjetosBiblioteca.Reserva;
import Registro.Registro;
import Utilidades.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  La clase ObjectManager contiene todos los menús que controlan y gestionan los distintos objetos del programa, sirve
 *  como extensión de los  Paneles y actua de puente entre los distintos métodos de los objetos y el menú del usuario de
 *  forma que facilita su aplicación directa sobre dichos paneles.
 */
public class ObjectManager
{

	private static final Scanner reader = new Scanner(System.in);



	/*
	*  Para los libros
	*/

	public static void añadirLibro()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Libro nuevoLibro = new Libro();

		Utils.borrarPantalla();
		System.out.println("Añadir un libro");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el título de la obra : ");
		nuevoLibro.setTitulo(reader.nextLine());
		if (nuevoLibro.getTitulo() != null)
		{
			System.out.print("Introduce el autor de la obra : ");
			nuevoLibro.setAutor(reader.nextLine());
			if (nuevoLibro.getAutor() != null)
			{
				System.out.print("Introduce el código isbn : ");
				nuevoLibro.setIsbn(reader.nextLine());
				if (nuevoLibro.getIsbn() != null)
				{
					System.out.print("Introduce la editorial : ");
					nuevoLibro.setEditorial(reader.nextLine());
					if (nuevoLibro.getEditorial() != null)
					{
						System.out.print("Introduce el número de copias que deseas añadir : ");
						nuevoLibro.setCopias(Integer.parseInt(reader.nextLine()));
						nuevoLibro.setCopiasDisponibles(nuevoLibro.getCopias());

						System.out.print("Desea guardar los cambios? : [s/n] (Por defecto [n])");
						String guardar = reader.nextLine();
						Utils.borrarPantalla();

						if (guardar.equals("s") || guardar.equals("si"))
						{
							biblioteca.getListaLibros().add(nuevoLibro);

							System.out.println("================== Libro guardado ==================");
							nuevoLibro.toString2();
						}
						else
						{
							System.out.println("================== Cambios descartados ==================");
						}
					}
				}
			}
		}
		reader.nextLine();
		Utils.borrarPantalla();
	}

	public static void añadirLibroCopia()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Utils.borrarPantalla();
		System.out.println("Añadir una variante a partir de un libro existente");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el ISBN de un libro: ");
		List<Libro> busquedaLibros = Libro.buscarLibros(biblioteca, "isbn", reader.nextLine());
		if (!busquedaLibros.isEmpty())
		{
			Libro nuevoLibro = new Libro(busquedaLibros.get(0));

			System.out.print("Introduce el ISBN de la nueva variante: ");
			String nuevoIsbn = reader.nextLine();
			if (!nuevoIsbn.equalsIgnoreCase(busquedaLibros.get(0).getIsbn()))
			{
				nuevoLibro.setIsbn(nuevoIsbn);

				System.out.println("Puedes cambiar mas valores del libro (si no lo deseas hacer simplemente presiona ENTER)");
				System.out.print("Introduce el titulo del libro : ");
				String nuevoTitulo = reader.nextLine();
				if (nuevoTitulo != null)
				{
					nuevoLibro.setTitulo(nuevoTitulo);
				}
				System.out.print("Introduce el autor del libro : ");
				String nuevoAutor = reader.nextLine();
				if (nuevoAutor != null)
				{
					nuevoLibro.setAutor(nuevoAutor);
				}
				System.out.print("Introduce la editorial del libro : ");
				String nuevaEditorial = reader.nextLine();
				if (nuevaEditorial != null)
				{
					nuevoLibro.setEditorial(nuevaEditorial);
				}
				System.out.print("Establece la cantidad de copias : ");
				String nuevasCopias = reader.nextLine();
				if (nuevasCopias != null)
				{
					try
					{
						nuevoLibro.setCopias(Integer.parseInt(nuevasCopias));
						nuevoLibro.setCopiasDisponibles(Integer.parseInt(nuevasCopias));

					}
					catch (NumberFormatException e)
					{
						System.out.println("<<< No has introducido un valor de copias adecuado >>>");
					}
				}
				Utils.borrarPantalla();
				System.out.println("Variante del libro " + busquedaLibros.get(0).getTitulo() + " creada");
				System.out.println("===================================================================");
				System.out.println("Libro original");
				busquedaLibros.get(0).toString2();
				System.out.println("===================================================================");
				System.out.println("Variante");
				nuevoLibro.toString2();
			}
			else
			{
				System.out.println("<<< La variante no puede tener el mismo isbn que el original >>>");
			}
		}
		else
		{
			System.out.println("<<< No se ha podido encontrar ningún libro >>>");
		}
		reader.nextLine();
	}

	public static void eliminarLibro()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Utils.borrarPantalla();
		System.out.println("Eliminar un libro");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el ISBN  : ");
		String cadenaBusqueda = reader.nextLine();
		List<Libro> busquedaLibros = Libro.buscarLibros(biblioteca, "isbn", cadenaBusqueda);

		if (!busquedaLibros.isEmpty())
		{
			biblioteca.getListaLibros().remove(busquedaLibros.get(0));
			System.out.println("<<< Se ha eliminado el libro " +
					busquedaLibros.get(0).getTitulo() + " con ISBN : " +
					busquedaLibros.get(0).getIsbn() + " >>>");
		}
		else
		{
			System.out.println(">>> No se ha encotrado ningún libro con el isbn " + cadenaBusqueda + " <<<");
		}
		reader.nextLine();
	}

	public static void buscarLibro()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		List<Libro> listaResultados = new ArrayList<>();

		Utils.borrarPantalla();
		System.out.println("Buscar un libro");
		System.out.println("_______________________________________________________________");
		System.out.print("Buscar  : ");
		String cadenaBusqueda = reader.nextLine();

		listaResultados = Libro.buscarLibros(biblioteca, "sinFiltro", cadenaBusqueda);

		Utils.borrarPantalla();

		if (!listaResultados.isEmpty())
		{
			System.out.println("Resultados obtenidos para : " + cadenaBusqueda);
			System.out.println("_______________________________________________________________");

			for (Libro libro : listaResultados)
			{
				libro.toString2();
				System.out.println("_____________________________________");
			}
		}
		else
		{
			System.out.println("No se ha obtenido nada a partir de : " + cadenaBusqueda);
			System.out.println("_______________________________________________________________");
		}

		reader.nextLine();
		Utils.borrarPantalla();
	}

	public static void verLibrosDisponibles()
	{
		List<Biblioteca> listaBibliotecas = Registro.getListaBibliotecas();

		Utils.borrarPantalla();
		System.out.println("Mostrando todos los libros disponibles de Mallorca");
		System.out.println("_______________________________________________________________________________");
		System.out.println();
		System.out.println();
		System.out.println();

		for (Biblioteca biblioteca : listaBibliotecas)
		{
			System.out.println("===============  " + biblioteca.getNombre() + "  ===============");
			System.out.println(" Disponibles                                                   ");
			System.out.println("_______________________________________________________________");
			for (Libro libro : biblioteca.getListaLibros())
			{
				if (libro.estaDisponible())
				{
					libro.toString2();
					System.out.println("_________________________________________________");
				}
			}
			reader.nextLine();
			System.out.println();
			System.out.println();
			System.out.println();
		}
		reader.nextLine();
	}



	/*
	* Para las personas
	*/

	public static void iniciarSesiónBibliotecario()
	{
		Utils.borrarPantalla();
		System.out.println("Iniciar sesión como empleado");
		System.out.println("_______________________________________________________________");
		System.out.print("NIF  : ");
		String nif = reader.nextLine();
		if (nif != null)
		{
			System.out.print("Contraseña  : ");
			String contraseña = reader.nextLine();
			if (contraseña != null)
			{
				if (Registro.auth("Bibliotecario", nif, contraseña))
				{
					Registro.getSesionBibliotecario().setConectado(true);
					Utils.borrarPantalla();
					System.out.println("Bievenid@, " + Registro.getSesionBibliotecario().getNombre() + " "
							+ Registro.getSesionBibliotecario().getApellidos());
					System.out.println("_______________________________________________________________");
					reader.nextLine();
					MenuBibliotecario.mostrarMenuPrincipal();
				}
				else
				{
					Utils.borrarPantalla();
					System.out.println("ACCESO DENEGADO");
;					System.out.println("_______________________________________________________________");
					reader.nextLine();
				}
			}
		}
	}

	public static void crearBibliotecario()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Bibliotecario bibliotecario = new Bibliotecario();

		Utils.borrarPantalla();
		System.out.println("Crear un nuevo Bibliotecario");
		System.out.println("_______________________________________________________________");
		bibliotecario.solicitarDatosPersona();
		if (bibliotecario.getPuestoTrabajo() != null)
		{
			biblioteca.getListaPersonas().add(bibliotecario);

			Utils.borrarPantalla();
			System.out.println("Nuevo bibliotecario añadido a " + biblioteca.getNombre());
			System.out.println("============================================================");
			System.out.println(bibliotecario.toString());
		}
		reader.nextLine();
	}

	public static void mostrarBibliotecarios()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Utils.borrarPantalla();
		System.out.println("Lista de empleados en " + biblioteca.getNombre());
		System.out.println("_______________________________________________________________");
		for (Persona persona : biblioteca.getListaPersonas())
		{
			if (persona instanceof Bibliotecario)
			{
				System.out.println(persona.toString());
			}
		}
		reader.nextLine();
	}

	public static void cambiarContraseñaBibliotecario()
	{
		Bibliotecario bibliotecario = Registro.getSesionBibliotecario();

		Utils.borrarPantalla();
		System.out.println("Introduce una nueva contraseña");
		String antiguaContraseña = bibliotecario.getContraseña();
		bibliotecario.cambiarContraseña(reader.nextLine());
		if (!bibliotecario.getContraseña().equals(antiguaContraseña))
		{
			System.out.println(">>> Contraseña actualizada <<<");
			reader.nextLine();
			MenuLogin.mostrarMenu();

		}
		else
		{
			System.out.println("<<< Contraseña no válida, cambios no guardados >>>");
		}
		reader.nextLine();

	}




	public static void iniciarSesiónUsuario()
	{
		Utils.borrarPantalla();
		System.out.println("Iniciar sesión como cliente");
		System.out.println("_______________________________________________________________");
		System.out.print("Dirección de correo  : ");
		String correo = reader.nextLine();
		if (correo != null)
		{
			System.out.print("Número de teléfono  : ");
			String telefono = reader.nextLine();
			if (telefono != null)
			{
				if (Registro.auth("Usuario", correo, telefono))
				{
					Utils.borrarPantalla();
					System.out.println("Bievenid@, " + Registro.getSesionUsuario().getNombre() + " "
							+ Registro.getSesionUsuario().getApellidos());
					System.out.println("_______________________________________________________________");
					reader.nextLine();
					MenuUsuario.mostrarMenuPrincipal();
				}
				else
				{
					Utils.borrarPantalla();
					System.out.println("ACCESO DENEGADO");
					System.out.println("_______________________________________________________________");
					reader.nextLine();
				}
			}
		}
	}

	public static void crearUsuario()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Usuario usuario = new Usuario();

		Utils.borrarPantalla();
		System.out.println("Crear un nuevo Usuario");
		System.out.println("_______________________________________________________________");
		usuario.solicitarDatosPersona();
		if (usuario.getDireccion() != null)
		{
			biblioteca.getListaPersonas().add(usuario);

			Utils.borrarPantalla();
			System.out.println("Nuevo usuario añadido a " + biblioteca.getNombre());
			System.out.println("============================================================");
			System.out.println(usuario.toString());
		}
		reader.nextLine();
	}

	public static void mostrarUsuarios()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		Utils.borrarPantalla();
		System.out.println("Lista de clientes en " + biblioteca.getNombre());
		System.out.println("_______________________________________________________________");
		for (Persona persona : biblioteca.getListaPersonas())
		{
			if (persona instanceof Usuario)
			{
				System.out.println(persona.toString());
			}
		}
		reader.nextLine();
	}

	public static void cambiarContraseñaUsuario() {
		Usuario usuario = Registro.getSesionUsuario();

		Utils.borrarPantalla();
		System.out.println("Introduce un nuevo número de teléfono");
		String antiguoTelefono = usuario.getTelefono();
		usuario.cambiarContraseña(reader.nextLine());
		if (usuario.getTelefono() != null)
		{
			System.out.println(">>> Número de teléfono actualizado <<<");
			reader.nextLine();
			MenuLogin.mostrarMenu();

		}
		else
		{
			usuario.setTelefono(antiguoTelefono);
			System.out.println("<<< Teléfono no válido, cambios no válidos >>>");
		}
		reader.nextLine();

	}



	/*
	*  Para las reservas
	*/

	public static void crearReserva()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();
		Reserva reserva = new Reserva();
		Utils.borrarPantalla();
		System.out.println("Reservar un libro");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el correo del cliente : ");
		String correo = reader.nextLine();
		System.out.print("Introduce el teléfono del cliente : ");
		String telefono = reader.nextLine();

		Usuario usuario = Registro.autentificarUsuario(correo, telefono);

		if (usuario != null)
		{
			System.out.print("ISBN del libro  : ");
			String isbn = reader.nextLine();
			List<Libro> listaResultados = Libro.buscarLibros(biblioteca, "isbn", isbn);
			if (!listaResultados.isEmpty())
			{
				if (listaResultados.get(0).estaDisponible())
				{
					listaResultados.get(0).setCopiasDisponibles(listaResultados.get(0).getCopiasDisponibles() - 1);

					reserva.setLibro(listaResultados.get(0));
					reserva.setFechaReserva(Utils.obtenerFechaActual());
					reserva.setHoraReserva(Utils.obtenerHoraActual());
					reserva.setFechaDevolucion(reserva.getLibro());

					usuario.getListaReservas().add(reserva);

					Utils.borrarPantalla();
					System.out.println("Reserva completada");
					System.out.println("==========================================================");
					System.out.println("Cliente : " + usuario.getNombre() + " " + usuario.getApellidos());
					reserva.mostrarReservaChula();
				}
				else
				{
					System.out.println("<<< El libro " + listaResultados.get(0).getTitulo() +
							" con ISBN " + listaResultados.get(0).getIsbn() + " no está disponible >>>");
				}

			}
			else
			{
				System.out.println("<<< ISBN no encontrado >>>");
			}
		}
		else
		{
			System.out.println("<<< Usuario no registrado en la base de datos >>>");
		}

		reader.nextLine();
	}

	public static void devolverLibro()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();
		Reserva reserva = new Reserva();
		Utils.borrarPantalla();
		System.out.println("Devolver un libro");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el correo del cliente : ");
		String correo = reader.nextLine();
		System.out.print("Introduce el teléfono del cliente : ");
		String telefono = reader.nextLine();

		Usuario usuario = Registro.autentificarUsuario(correo, telefono);

		if (usuario != null) {
			System.out.print("ISBN del libro  : ");
			String isbn = reader.nextLine();

			boolean encontrado = false;
			int res = 0;
			while (!encontrado && res < usuario.getListaReservas().size()) {
				if (usuario.getListaReservas().get(res).getLibro().getIsbn().equalsIgnoreCase(isbn)) {
					encontrado = true;
					System.out.println("Libro devuelto");
					System.out.println("==========================================================");
					usuario.getListaReservas().get(res).getLibro().toString2();

					usuario.getListaReservas().get(res).getLibro().setCopiasDisponibles(
							usuario.getListaReservas().get(res).getLibro().getCopiasDisponibles() + 1);

					usuario.getListaReservas().remove(res);
				}
				res++;
			}
			if (!encontrado)
			{
				System.out.println("<<< Reserva no encontrada para el ISBN " + isbn + " >>>");
			}
		}
		else
		{
			System.out.println("<<< Usuario no registrado en la base de datos >>>");
		}
		reader.nextLine();
	}

	public static void buscarReservaUsuario()
	{
		Utils.borrarPantalla();
		System.out.println("Buscar reservas");
		System.out.println("_______________________________________________________________");
		System.out.print("Introduce el email del cliente : ");
		String correo = reader.nextLine();

		Usuario usuario = Registro.autentificarUsuario(correo, null);

		if (usuario != null)
		{
			Utils.borrarPantalla();
			System.out.println("Mostrando las reservas de " + usuario.getNombre() + " " + usuario.getApellidos());
			System.out.println("_______________________________________________________________");
			for (Reserva reserva : usuario.getListaReservas())
			{
				reserva.mostrarReservaChula();
				if (usuario.getListaReservas().size() > 1)
				{
					System.out.println("||| | | ||| | | | | | | | ||| | | | | | ||| | | | | | | ||");
				}
			}
		}

		reader.nextLine();

	}

	public static void verMisReservas()
	{
		Usuario usuario = Registro.getSesionUsuario();

		Utils.borrarPantalla();
		System.out.println("Mis reservas");
		System.out.println("_______________________________________________________________");

		for (Reserva reserva : usuario.getListaReservas())
		{
			reserva.mostrarReservaChula();
		}

	}

}
