package Menus.Paneles;

import Menus.GestorObjetos.ObjectManager;
import ObjetosBiblioteca.Biblioteca;
import ObjetosBiblioteca.Personas.Usuario;
import Registro.Registro;
import Utilidades.Utils;

import java.util.Scanner;

/**
 * Clase para mostrar la interfaz de usuario al Usuario en cuestión
 */
public class MenuUsuario
{


	private static final Scanner reader = new Scanner(System.in);


	public static void mostrarMenuPrincipal()
	{
		Usuario usuario = Registro.getSesionUsuario();

		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Bienvenid@ : " + usuario.getNombre());
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   1. - Buscar libros                      |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Ver libros disponibles             |");
			System.out.println("|                                           |");
			System.out.println("|   3. - Ver mis reservas                   |");
			System.out.println("|                                           |");
			System.out.println("|   4. - Cambiar número de teléfono         |");
			System.out.println("|                                           |");
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
					ObjectManager.buscarLibro();
					break;

				case "2":
					ObjectManager.verLibrosDisponibles();
					break;

				case "3":
					ObjectManager.verMisReservas();
					break;

				case "4":
					ObjectManager.cambiarContraseñaUsuario();
					break;

				case "8":
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
}
