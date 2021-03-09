package Menus.Paneles;

import Menus.GestorObjetos.ObjectManager;
import Utilidades.Utils;

import java.util.Scanner;

/**
 *  Clase que muestra el primer menú que aparece en la aplicación, este se encarga de realizar los distintos tipos de
 *  registro e inicio de sesión.
 */
public class MenuLogin
{

	private static final Scanner reader = new Scanner(System.in);

	public static void mostrarMenu() {
		String opcion;
		do
		{
			Utils.borrarPantalla();

			System.out.println("Bibliotecas .NET");
			System.out.println(" ___________________________________________");
			System.out.println("|                                           |");
			System.out.println("|   INICIAR SESION                          |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   1. - Como empleado                      |");
			System.out.println("|                                           |");
			System.out.println("|   2. - Como usuario                       |");
			System.out.println("|                                           |");
			System.out.println("|___________________________________________|");
			System.out.println("|                                           |");
			System.out.println("|   9. - Salir                              |");
			System.out.println("|___________________________________________|");


			System.out.print("Escoge una opcion : ");
			opcion = reader.nextLine();

			switch (opcion)
			{
				case "1":
					ObjectManager.iniciarSesiónBibliotecario();
					break;

				case "2":
					ObjectManager.iniciarSesiónUsuario();
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
