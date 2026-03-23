package examen;

import java.util.ArrayList;

/**
 * Gestor de la colección de empleados de la empresa. Permite realizar altas,
 * bajas, modificaciones y listados
 */
public class ListadoEmpleados {
	private ArrayList<Empleado> empleados;

	public ListadoEmpleados() {
		this.empleados = new ArrayList<>();
	}

	/**
	 * Añade un empleado si el dni no existe previamente
	 * 
	 * @param e Objeto empleado a añadir
	 * @return true si se añadió, false si el dni ya existía
	 */
	public boolean anadirEmpleado(Empleado e) {
		boolean anadido = false;
		if (buscarPorDni(e.getDni()) == null) {
			empleados.add(e);
			anadido = true;
		}
		return anadido;
	}

	/**
	 * Muestra por consola todos los empleados o un aviso si está vacía
	 */
	public void listarTodos() {
		if (empleados.isEmpty()) {
			System.out.println("Actualmente no hay empleados registrados en el sistema");
		} else {
			for (Empleado e : empleados) {
				System.out.println(e);
				System.out.println("---------------------------");
			}
		}
	}

	/**
	 * Busca un empleado por su dni
	 * 
	 * @param dni Identificador a buscar
	 * @return El objeto Empleado encontrado o null si no existe
	 */
	public Empleado buscarPorDni(String dni) {
		for (Empleado e : empleados) {
			if (e.getDni().equalsIgnoreCase(dni)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Modifica las horas extras de un empleado por las horas introducidas por el
	 * usuario
	 * 
	 * @param dni   Identificador que busca el empleado al que se le deben cambiar
	 *              las horas
	 * @param horas Horas a modificar
	 * @return True si se ha modificado correctamente, false si no
	 */
	public boolean modificarHorasExtra(String dni, int horas) {
		boolean modificado = false;
		Empleado e = buscarPorDni(dni);
		if (e != null) {
			e.setHorasExtras(horas);
			modificado = true;
		}
		return modificado;
	}

	/**
	 * Modifica el porcentaje de bonificación de un empleado
	 * 
	 * @param dni        Identificador para saber a qué empleado hay que modificarle
	 *                   el porcentaje
	 * @param porcentaje Nuevo porcentaje que introduce el usuario
	 * @return true si se ha podido modificar, false si no
	 */
	public boolean modificarBonificacion(String dni, double porcentaje) {
		boolean modificado = false;
		Empleado e = buscarPorDni(dni);
		if (e != null) {
			e.setPorcentajeBonificacion(porcentaje);
			modificado = true;
		}
		return modificado;
	}

	/**
	 * Elimina un vehículo por matrícula @return true si se eliminó con éxito
	 */
	public boolean eliminarPorDni(String dni) {
		Empleado e = buscarPorDni(dni);
		return empleados.remove(e);
	}

	/**
	 * @return Suma total de las bonificaciones de todos los empleados
	 */
	public double calcularGastoBonificaciones() {
		double total = 0;
		for (Empleado e : empleados) {
			total += e.getImporteBonificacion();
		}
		return total;
	}

	/** Muestra los empleados con bonificación > 15% */
	public void listarEmpleadosAltoDesempenio() {
		for (Empleado e : empleados) {
			if (e.tieneBonificacion())
				System.out.println(e);
		}
	}
}
