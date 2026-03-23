package examen;

public class Empleado {
	private String dni;
	private String nombre;
	private double salarioBase;
	private double porcentajeBonificacion;
	private static double precioHoraExtra;
	private int horasExtras;

	/**
	 * Constructor para crear un nuevo empleado
	 * 
	 * @param dni                    Identificador único del empleado
	 * @param nombre                 Nombre del empleado
	 * @param salarioBase            Salario base sin extras (ni bonificaciones y
	 *                               tal)
	 * @param porcentajeBonificacion Porcentaje entre 0 y 30
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

	/**
	 * Actualiza el porcentaje de bonificación validando el rango permitido (0-25%)
	 * 
	 * @param porcentajeBonificacion
	 */
	public void setPorcentajeBonificacion(double porcentajeBonificacion) {
		this.porcentajeBonificacion = porcentajeBonificacion;
	}

	/**
	 * 
	 * @param precioHoraExtra Nuevo coste de las horas extra aplicable a todos los
	 *                        empleados
	 */
	public static void setPrecioHoraExtra(double precioHoraExtra) {
		Empleado.precioHoraExtra = precioHoraExtra;
	}

	public int getHorasExtras() {
		return horasExtras;
	}

	/**
	 * @param horasExtras Número de horas extra realizadas por un empleado
	 */
	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
	}

	// MÉTODOS
	/**
	 * @return Importe calculado de las bonificaciones: salario base × porcentaje
	 *         bonificación / 100
	 */
	public double getImporteBonificacion() {
		return salarioBase * porcentajeBonificacion / 100;
	}

	/** @return Importe total de las horas extras según su precio */
	public double getImporteHorasExtra() {
		return horasExtras * precioHoraExtra;
	}

	/** @return Suma del salario base, bonificación y horas extra */
	public double getImporteTotal() {
		return salarioBase + getImporteBonificacion() + getImporteHorasExtra();
	}

	/**
	 * @return true si tiene bonificación es estrictamente superior al 15%
	 */
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
