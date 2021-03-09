package Utilidades;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *  La clase Utils tiene funciones varias que pueden ser aplicadas a lo largo del conjunto de métodos y clases, estas
 *  tienen la opción de ser llamadas repetidas veces ya que no pertenecen a ningúna entidad en concreto.
 */
public class Utils
{



	/*
	* Comprobaciones
	*/

	public static boolean comprobarLongitudCadena(String cadena, int longitud)
	{
		return cadena.length() < longitud;
	}

	public static boolean comprobarSoloLetras(String cadena)
	{
		String regexAZ = "^[\\p{L} .'-]+$";
		Pattern letrasAZ = Pattern.compile(regexAZ);
		Matcher matcher = letrasAZ.matcher(cadena);

		return matcher.matches();
	}



	/*
	* Fechas y horarios
	*/

	public static String obtenerFechaActual()
	{
		Date date = new Date();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		 return fecha.format(date);

	}

	public static String obtenerHoraActual()
	{
		Date date = new Date();
		DateFormat hora = new SimpleDateFormat("HH:mm");
		return hora.format(date);
	}

	public static String calcularFechaSumarMeses(int meses)
	{
		LocalDate futureDate = LocalDate.now().plusMonths(meses);
		return futureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}



	/*
	* Para los menus
	*/

	public static void borrarPantalla()
	{
		for (int i = 0; i < 50; i++)
		{
			System.out.println();
		}
	}

}
