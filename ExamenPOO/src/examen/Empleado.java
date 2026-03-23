package examen;

public class Empleado {
	private String dni;
	private String nombre;
	private double salarioBase;
	private double porcentajeBonificacion;
	private static double precioHoraExtra;
	private int horasExtras;

	/**
	 * 
	 * @param dni
	 * @param nombre
	 * @param salarioBase
	 * @param porcentajeBonificacion
	 * @param horasExtras
	 */
	public Empleado(String dni, String nombre, double salarioBase, double porcentajeBonificacion) {
		this.dni = dni;
		this.nombre = nombre;
		this.salarioBase = salarioBase;
		this.porcentajeBonificacion = porcentajeBonificacion;
		this.horasExtras = 0;
	}

	// GETTERS Y SETTERS
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public double getPorcentajeBonificacion() {
		return porcentajeBonificacion;
	}

	public void setPorcentajeBonificacion(double porcentajeBonificacion) {
		this.porcentajeBonificacion = porcentajeBonificacion;
	}

	public static double getPrecioHoraExtra() {
		return precioHoraExtra;
	}

	public static void setPrecioHoraExtra(double precioHoraExtra) {
		Empleado.precioHoraExtra = precioHoraExtra;
	}

	public int getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
	}

	// MÉTODOS
	public double getImporteBonificacion() {
		return salarioBase * porcentajeBonificacion / 100;
	}

	public double getImporteHorasExtra() {
		return horasExtras * precioHoraExtra;
	}

	public double getImporteTotal() {
		return salarioBase + getImporteBonificacion() + getImporteBonificacion();
	}

	public boolean tieneBonificacion() {
		return porcentajeBonificacion > 15;
	}

	@Override
	public String toString() {
		return String.format("%s - %s\nSalario base: %s | Bonificación: %s\nHoras Extra: %s | Total Bruto: %s", dni,
				nombre, salarioBase, porcentajeBonificacion, horasExtras, getImporteTotal());
	}

	@Override
	public boolean equals(Object objeto) {
		boolean iguales = false;
		Empleado otroEmpleado = (Empleado) objeto; // realizamos un cast
		if (this.dni.equals(otroEmpleado.dni)) {
			iguales = true;
		}
		return iguales;
	}

}
