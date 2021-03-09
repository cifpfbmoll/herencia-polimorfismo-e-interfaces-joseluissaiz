package Registro;

import ObjetosBiblioteca.Biblioteca;
import ObjetosBiblioteca.Libro;
import ObjetosBiblioteca.Personas.Bibliotecario;
import ObjetosBiblioteca.Personas.Persona;
import ObjetosBiblioteca.Personas.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Registro es la base de datos auto-generada de toda la aplicación, su función es la de establecer los valores
 * de inicio de sesión para poder utilizar esas variables a lo largo del programa, como pueden ser el Usuario o
 * Bibliotecario que ha iniciado sesión o bien la Biblioteca donde ha tenido lugar ese inicio.
 */
public class Registro {

	private static List<Biblioteca> listaBibliotecas = new ArrayList<>();


	private static Biblioteca sesionBiblioteca;
	private static Bibliotecario sesionBibliotecario;
	private static Usuario sesionUsuario;




	/* >>> FOR TESTING >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public static void inicializarBibliotecas()
	{
		Biblioteca b1 = new Biblioteca("PalmaLibros");
		Biblioteca b2 = new Biblioteca("IncaLibros");
		Biblioteca b3 = new Biblioteca("ManacorLibros");
		Biblioteca b4 = new Biblioteca("CamposLibros");


		listaBibliotecas.add(b1);
		listaBibliotecas.add(b2);
		listaBibliotecas.add(b3);
		listaBibliotecas.add(b4);


		Bibliotecario bi1 = new Bibliotecario("Jorge", "Palomos", 24, "1234", "1234", "cca");
		Bibliotecario bi2 = new Bibliotecario("Antonio", "Giménez", 46, "12345", "1234", "cca");
		Bibliotecario bi3 = new Bibliotecario("Enrique", "Pascual", 61, "123456", "1234", "cca");
		Bibliotecario bi4 = new Bibliotecario("Pedro", "Ribas", 39, "1234567", "1234", "cca");
		Bibliotecario bi5 = new Bibliotecario("José", "Fernandez", 18, "12345678", "1234", "cca");
		Bibliotecario bi6 = new Bibliotecario("Sofia", "Castaño", 20, "123456789", "1234", "cca");
		Bibliotecario bi7 = new Bibliotecario("Julia", "Pastor", 55, "987654321", "1234", "cca");
		Bibliotecario bi8 = new Bibliotecario("Ana", "Mas", 20, "98765432", "1234", "cca");
		Bibliotecario bi9 = new Bibliotecario("Gabriela", "De Vigo", 33, "9876543", "1234", "cca");


		b1.getListaPersonas().add(bi1);
		b1.getListaPersonas().add(bi4);
		b1.getListaPersonas().add(bi7);

		b2.getListaPersonas().add(bi2);
		b2.getListaPersonas().add(bi5);
		b1.getListaPersonas().add(bi8);

		b3.getListaPersonas().add(bi3);
		b3.getListaPersonas().add(bi6);
		b4.getListaPersonas().add(bi9);

		Usuario u1 = new Usuario("Juanjo", "Perez", 19, "1234", "1234", "Calle Mayor nº 15");
		Usuario u2 = new Usuario("Carlos", "Ordoñez", 27, "12345", "1234", "Calle Mayor nº 15");
		Usuario u3 = new Usuario("Antonio", "Rojas", 49, "123456", "1234", "Calle Mayor nº 15");
		Usuario u4 = new Usuario("Pablo", "Villa", 70, "1234567", "1234", "Calle Mayor nº 15");
		Usuario u5 = new Usuario("Patricia", "Calvo", 32, "12345678", "1234", "Calle Mayor nº 15");
		Usuario u6 = new Usuario("Paula", "Castillo", 23, "123456789", "1234", "Calle Mayor nº 15");
		Usuario u7 = new Usuario("Penélope", "Ruiz", 76, "987654321", "1234", "Calle Mayor nº 15");
		Usuario u8 = new Usuario("Carla", "Vallés", 20, "98765432", "1234", "Calle Mayor nº 15");
		Usuario u9 = new Usuario("Carmen", "Zafón", 56, "9876543", "1234", "Calle Mayor nº 15");



		b1.getListaPersonas().add(u1);
		b1.getListaPersonas().add(u4);
		b1.getListaPersonas().add(u7);

		b2.getListaPersonas().add(u2);
		b2.getListaPersonas().add(u5);
		b1.getListaPersonas().add(u8);

		b3.getListaPersonas().add(u3);
		b3.getListaPersonas().add(u6);
		b4.getListaPersonas().add(u9);


		Libro l1 = new Libro("Surcando las olas", "Benito Ordoñez", "3-333-33333-3", "cAnaya", 5, 3);
		Libro l2 = new Libro("Caminando entre fantasmas", "Mario Pujadas", "3-444-33333-3", "Satélite", 10, 5);
		Libro l3 = new Libro("Rompiendo el silencio", "Nestor Ochoa", "3-555-33333-3", "cAnaya", 2, 1);
		Libro l4 = new Libro("Amanecer sin ti", "Clara Calvo", "3-666-33333-3", "cAnaya", 5, 4);
		Libro l5 = new Libro("El bruto y el silencioso", "Benito Ordoñez", "3-777-33333-3", "cAnaya", 9, 6);
		Libro l6 = new Libro("El té de las cinco", "Camila Torres", "3-888-33333-3", "cAnaya", 15, 10);
		Libro l7 = new Libro("Perdido en la marea", "Juan Perez", "3-999-33333-3", "Satélite", 14, 10);
		Libro l8 = new Libro("Lágrimas de miel", "Andrea Castro", "4-333-33333-3", "cAnaya", 7, 5);
		Libro l9 = new Libro("Primavera en Japón", "Camila Torres", "4-444-33333-3", "cAnaya", 13, 4);
		Libro l10 = new Libro("Lagunas de memoria", "Juan Perez", "4-444-33333-3", "Satélite", 3, 1);
		Libro l11 = new Libro("Miedo a la soledad", "Clara Calvo", "4-555-33333-3", "cAnaya", 2, 0);
		Libro l12 = new Libro("Terror y sacrificio", "Mario Pujadas", "4-666-33333-3", "Satélite", 10, 10);
		Libro l13 = new Libro("Mi media naranja", "Andrea Castro", "4-777-33333-3", "cAnaya", 5, 1);
		Libro l14 = new Libro("Trotamundos del mañana", "Benito Ordoñez", "4-888-33333-3", "cAnaya", 21, 12);
		Libro l15 = new Libro("Bajo el suelo", "Clara Calvo", "4-999-33333-3", "cAnaya", 3, 3);
		Libro l16 = new Libro("Mareas en Vietnam", "Camila Torres", "5-333-33333-3", "cAnaya", 3, 2);
		Libro l17 = new Libro("Sentido y apoyo", "Juan Perez", "5-444-33333-3", "Satélite", 1, 0);
		Libro l18 = new Libro("La orquesta de mi muerte", "Mario Pujadas", "5-555-33333-3", "Satélite", 12, 5);
		Libro l19 = new Libro("Enamorado de mi perro", "Nestor Ochoa", "5-666-33333-3", "cAnaya", 24, 3);
		Libro l20 = new Libro("Ritmo en Brasil", "Camila Torres", "5-777-33333-3", "cAnaya", 13, 4);
		Libro l21 = new Libro("Recarga Total", "Benito Ordoñez", "5-888-33333-3", "cAnaya", 3, 1);
		Libro l22 = new Libro("A solas con la pared", "Clara Calvo", "5-999-33333-3", "cAnaya", 4, 3);
		Libro l23 = new Libro("Supervivencia fortuita", "Juan Perez", "6-444-33333-3", "Satélite", 6, 3);
		Libro l24 = new Libro("A través de la rendija", "Mario Pujadas", "6-555-33333-3", "Satélite", 13, 7);
		Libro l25 = new Libro("Querer hoy y amar mañana", "Andrea Castro", "6-666-33333-3", "cAnaya", 10, 10);
		Libro l26 = new Libro("Veinte balas", "Benito Ordoñez", "6-777-33333-3", "cAnaya", 13, 7);
		Libro l27 = new Libro("Paseo en elefante", "Camila Torres", "6-888-33333-3", "cAnaya", 1, 0);
		Libro l28 = new Libro("Salvando una mentira", "Juan Perez", "6-999-33333-3", "Satélite", 5, 0);
		Libro l29 = new Libro("Bajo la lluvia", "Clara Calvo", "7-333-33333-3", "cAnaya", 6, 1);
		Libro l30 = new Libro("Niño cuando viene el verano", "Nestor Ochoa", "7-444-33333-3", "cAnaya", 4, 4);


		b1.getListaLibros().add(l1);
		b1.getListaLibros().add(l5);
		b1.getListaLibros().add(l9);
		b1.getListaLibros().add(l13);
		b1.getListaLibros().add(l17);
		b1.getListaLibros().add(l21);
		b1.getListaLibros().add(l25);
		b1.getListaLibros().add(l29);

		b2.getListaLibros().add(l2);
		b2.getListaLibros().add(l6);
		b2.getListaLibros().add(l10);
		b2.getListaLibros().add(l14);
		b2.getListaLibros().add(l18);
		b2.getListaLibros().add(l22);
		b2.getListaLibros().add(l26);
		b2.getListaLibros().add(l30);

		b3.getListaLibros().add(l3);
		b3.getListaLibros().add(l7);
		b3.getListaLibros().add(l11);
		b3.getListaLibros().add(l15);
		b3.getListaLibros().add(l19);
		b3.getListaLibros().add(l23);
		b3.getListaLibros().add(l27);

		b4.getListaLibros().add(l4);
		b4.getListaLibros().add(l8);
		b4.getListaLibros().add(l12);
		b4.getListaLibros().add(l16);
		b4.getListaLibros().add(l20);
		b4.getListaLibros().add(l24);
		b4.getListaLibros().add(l28);




	}
	/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


	/**
	 *   Este método se encarga de buscar entre todas las bibliotecas y usuarios, a aquel que corresponda con los
	 *   parámetros indicados, si es así, el metodo devuelve verdadero y se encarga de registrar el inicio de sesión
	 *   de esa persona dentro de esta clase, de tal forma que se podrá acceder a los datos de dicha persona y/o
	 *   biblioteca directamente accediendo al registro.
	 *
	 * @param tipoPersona | Establece un filtro : Bibliotecario, Usuario.
	 * @param idPersona | Indica el identificador de la persona : NIF, Correo electrónico
	 * @param contraseñaPersona | Indica la contraseña de la persona : Contraseña, Número teléfono.
	 * @return | Devuelve verdadero o falso dependiendo del éxito del registro.
	 */
	public static boolean auth(String tipoPersona, String idPersona, String contraseñaPersona)
	{
		boolean encontrado = false;

		int bib = 0;

		while (!encontrado && bib < getListaBibliotecas().size())
		{

			Biblioteca bibliotecaIterator = getListaBibliotecas().get(bib);

			int pers = 0;
			while(!encontrado && pers < bibliotecaIterator.getListaPersonas().size())
			{

				switch (tipoPersona)
				{
					case "Bibliotecario":
						if (bibliotecaIterator.getListaPersonas().get(pers) instanceof Bibliotecario)
						{
							Bibliotecario bibliotecarioIterator = (Bibliotecario) bibliotecaIterator.getListaPersonas().get(pers);

							if (bibliotecarioIterator.getNif().equals(idPersona) && bibliotecarioIterator.getContraseña().equals(contraseñaPersona))
							{
								setSesionBibliotecario(bibliotecarioIterator);
								setSesionBiblioteca(bibliotecaIterator);
								encontrado = true;
							}

						}
						break;

					case "Usuario":
						if (bibliotecaIterator.getListaPersonas().get(pers) instanceof Usuario)
						{
							Usuario usuarioIterator = (Usuario) bibliotecaIterator.getListaPersonas().get(pers);

							if (usuarioIterator.getCorreo().equals(idPersona) && usuarioIterator.getTelefono().equals(contraseñaPersona))
							{
								setSesionUsuario(usuarioIterator);
								setSesionBiblioteca(bibliotecaIterator);
								encontrado = true;
							}

						}
						break;

					default:
						System.out.println("Introduce un filtro de persona : 'Bibliotecario', 'Usuario'");
						break;
				}

				pers++;
			}

			bib++;
		}
		return encontrado;
	}


	public static Usuario autentificarUsuario(String correo, String telefono)
	{
		boolean encontrado = false;
		Usuario usuario = null;
		int bib = 0;

		while (!encontrado && bib < getListaBibliotecas().size()) {
			Biblioteca bibliotecaIterator = getListaBibliotecas().get(bib);

			int pers = 0;
			while (!encontrado && pers < bibliotecaIterator.getListaPersonas().size())
			{
				if (bibliotecaIterator.getListaPersonas().get(pers) instanceof Usuario)
				{
					Usuario usuarioIterator = (Usuario) bibliotecaIterator.getListaPersonas().get(pers);

					if (telefono != null)
					{
						if (usuarioIterator.getCorreo().equals(correo) && usuarioIterator.getTelefono().equals(telefono))
						{
							usuario = usuarioIterator;
							encontrado = true;
						}
					}
					else
					{
						if (usuarioIterator.getCorreo().equals(correo))
						{
							usuario = usuarioIterator;
							encontrado = true;
						}
					}
				}
				pers ++;
			}
			bib ++;
		}
		return usuario;
	}




	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public static void setSesionBibliotecario(Bibliotecario bibliotecario) { sesionBibliotecario = bibliotecario; }

	public static void setSesionBiblioteca(Biblioteca biblioteca) { sesionBiblioteca = biblioteca; }

	public static void setSesionUsuario(Usuario usuario) { sesionUsuario = usuario; }


	/*
	 * Getters
	 */

	public static Biblioteca getSesionBiblioteca() { return sesionBiblioteca; }

	public static Bibliotecario getSesionBibliotecario() { return sesionBibliotecario; }

	public static List<Biblioteca> getListaBibliotecas() { return listaBibliotecas; }

	public static Usuario getSesionUsuario() { return sesionUsuario; }

}
