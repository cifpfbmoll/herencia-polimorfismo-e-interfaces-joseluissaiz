package ObjetosBiblioteca;

import Interfaces.Material;
import Utilidades.Utils;

public class Reserva implements Material
{

	private Libro libro;
	private String fechaReserva;
	private String horaReserva;
	private String fechaDevolucion;


	/* Constructores */

	public Reserva()
	{}

	public Reserva(Libro libro, String fechaReserva, String horaReserva, String fechaDevolucion)
	{
		this.setLibro(libro);
		this.setFechaReserva(fechaReserva);
		this.setHoraReserva(horaReserva);
		this.setFechaDevolucionConcreta(fechaDevolucion);
	}

	public Reserva(Reserva reserva)
	{
		this.setLibro(reserva.getLibro());
		this.setFechaReserva(reserva.getFechaReserva());
		this.setHoraReserva(reserva.getHoraReserva());
		this.setFechaDevolucionConcreta(reserva.getFechaDevolucion());
	}



	/*
	 * Métodos heredados
	 */

	@Override
	public String toString() {
		return "Libro : " + this.getLibro() +
				", Fecha de la reserva : " + this.getFechaReserva() +
				", Hora de la reserva : " + this.getHoraReserva() +
				", Fecha devolución : " + this.getFechaDevolucion();
	}

	@Override
	public void setFechaDevolucion(Object objetoBiblioteca) {
		if (objetoBiblioteca instanceof Libro)
		{
			this.fechaDevolucion = Utils.calcularFechaSumarMeses(1);
		}
	}

	@Override
	public void mostrarReservaChula() {
		System.out.println("==========================================================");
		System.out.println("Titulo del libro    : " + this.getLibro().getTitulo());
		System.out.println("ISBN                : " + this.getLibro().getIsbn());
		System.out.println("Fecha de la reserva : " + this.getFechaReserva());
		System.out.println("Hora de la reserva  : " + this.getHoraReserva());
		System.out.println("__________________________________________________________");
		System.out.println("Fecha de devolución : " + this.getFechaDevolucion());
		System.out.println("==========================================================");

	}



	/*
	 * DTO AFTER THIS LINE
	 */


	/*
	 * Setters
	 */

	public void setLibro(Libro libro)
	{
		this.libro = libro;
	}

	public void setFechaReserva(String fechaReserva)
	{
		this.fechaReserva = fechaReserva;
	}

	public void setHoraReserva(String horaReserva)
	{
		this.horaReserva = horaReserva;
	}

	public void setFechaDevolucionConcreta(String fechaDevolucion)
	{
		this.fechaDevolucion = fechaDevolucion;
	}


	/*
	 * Getters
	 */

	public Libro getLibro() {return this.libro;}

	public String getFechaReserva() {return this.fechaReserva;}

	public String getHoraReserva() {return this.horaReserva;}

	public String getFechaDevolucion() {return this.fechaDevolucion;}



}
