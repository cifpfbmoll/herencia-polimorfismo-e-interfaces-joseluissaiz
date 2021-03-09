package Menus.Paneles;

import Menus.GestorObjetos.ObjectManager;
import Registro.Registro;
import ObjetosBiblioteca.Biblioteca;
import Utilidades.Utils;

import java.util.Scanner;

/**
 * Clase para mostrar la interfaz de usuario al Bibliotecario en cuestión
 */
public class MenuBibliotecario
{


	private static final Scanner reader = new Scanner(System.in);
	

	public static void mostrarMenuPrincipal()
	{
		Biblioteca biblioteca = Registro.getSesionBiblioteca();

		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Gestor de bibliotecas : " + biblioteca.getNombre());
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   1. - Biblioteca                         |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Usuarios                           |");
			System.out.println("|                                           |");
			System.out.println("|   3. - Cambiar contraseña                 |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   8. - Cerrar sesión                      |");
			System.out.println("|                                           |");
			System.out.println("|   9. - Salir                              |");
			System.out.println("|___________________________________________|");

			System.out.print("Escoge una opcion : ");
			opcion = reader.nextLine();

			switch (opcion)
			{

				case "1":
					mostrarMenuBiblioteca();
					break;

				case "2":
					mostrarMenuUsuarios();
					break;

				case "3":
					ObjectManager.cambiarContraseñaBibliotecario();
					break;

				case "8":
					Registro.getSesionBibliotecario().setConectado(false);
					MenuLogin.mostrarMenu();
					break;

				case "9":
					System.exit(0);
					break;

				default:
					break;
			}
		} while (true);
	}

	private static void mostrarMenuBiblioteca()
	{
		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Biblioteca/");
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   1. - Buscar libro                       |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Ver libros disponibles             |");
			System.out.println("|                                           |");
			System.out.println("|   3. - Añadir libro                       |");
			System.out.println("|                                           |");
			System.out.println("|   4. - Crear variante libro               |");
			System.out.println("|                                           |");
			System.out.println("|   5. - Borrar libro                       |");
			System.out.println("|                                           |");
			System.out.println("|   6. - Reservas                           |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   9. - Atrás                              |");
			System.out.println("|___________________________________________|");

			System.out.print("Escoge una opcion : ");
			opcion = reader.nextLine();

			switch (opcion)
			{
				case "1":
					ObjectManager.buscarLibro();
					break;

				case "2":
					ObjectManager.verLibrosDisponibles();
					break;

				case "3":
					ObjectManager.añadirLibro();
					break;

				case "4":
					ObjectManager.añadirLibroCopia();
					break;

				case "5":
					ObjectManager.eliminarLibro();
					break;

				case "6":
					mostrarMenuReservas();
					break;
				case "9":
					mostrarMenuPrincipal();
					break;

				default:
					break;
			}
		} while (!opcion.equals("9"));
	}

	private static void mostrarMenuReservas()
	{
		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Biblioteca/Reservas");
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   1. - Buscar reserva                     |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Crear reserva                      |");
			System.out.println("|                                           |");
			System.out.println("|   3. - Devolver libro                     |");
			System.out.println("|                                           |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   9. - Atrás                              |");
			System.out.println("|___________________________________________|");

			System.out.print("Escoge una opcion : ");
			opcion = reader.nextLine();

			switch (opcion)
			{
				case "1":
					ObjectManager.buscarReservaUsuario();
					break;

				case "2":
					ObjectManager.crearReserva();
					break;

				case "3":
					ObjectManager.devolverLibro();
					break;

				case "9":
					mostrarMenuBiblioteca();
					break;

				default:
					break;
			}
		} while (!opcion.equals("9"));
	}

	private static void mostrarMenuUsuarios()
	{
		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Biblioteca/Usuarios");
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   1. - Crear un nuevo Bibliotecario       |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Crear un nuevo Usuario             |");
			System.out.println("|                                           |");
			System.out.println("|   3. - Mostrar todos los empleados        |");
			System.out.println("|                                           |");
			System.out.println("|   4. - Mostrar todos los clientes         |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   9. - Atrás                              |");
			System.out.println("|___________________________________________|");

			System.out.print("Escoge una opcion : ");
			opcion = reader.nextLine();

			switch (opcion)
			{
				case "1":
					ObjectManager.crearBibliotecario();
					break;

				case "2":
					ObjectManager.crearUsuario();
					break;

				case "3":
					ObjectManager.mostrarBibliotecarios();
					break;

				case "4":
					ObjectManager.mostrarUsuarios();
					break;

				case "9":
					mostrarMenuPrincipal();
					break;

				default:
					break;
			}
		} while (!opcion.equals("9"));
	}
}
