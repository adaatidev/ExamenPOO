package examen;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal que actúa como controlador del sistema. Coordina la
 * interacción entre el usuario y la lógica del gestor
 */
public class Main {

	/**
	 * Punto de entrada de la aplicación. Gestiona el ciclo de vida del menú
	 * 
	 * @param args Argumentos de sistema
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ListadoEmpleados gestor = new ListadoEmpleados();
		int opcion = 0;

		// CONFIGURACIÓN POR DEFECTO
		Empleado.setPrecioHoraExtra(15.0);

		do {
			try {
				imprimirMenu();
				opcion = sc.nextInt();

				sc.nextLine(); // LIMPIA

				switch (opcion) {
				case 1 -> gestionarAlta(sc, gestor);
				case 2 -> gestor.listarTodos();
				case 3 -> gestionarBusqueda(sc, gestor);
				case 4 -> gestionarModificacionHoras(sc, gestor);
				case 5 -> gestionarModificacionBonificacion(sc, gestor);
				case 6 -> gestionarSeguroGlobal(sc);
				case 7 -> gestionarEliminacion(sc, gestor);
				case 8 -> gestionarEstadisticas(gestor);
				case 9 -> System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
				default -> System.out.println("Opción no válida, intente de nuevo");
				}
			} catch (InputMismatchException e) {
				System.out.println("ERROR: Entrada no válida. Por favor, use números.");
				sc.nextLine(); // LIMPIA
			}
		} while (opcion != 9);

		System.out.println("Has salido del programa");

		sc.close();
	}

	/** Muestra las opciones disponibles al usuario */
	private static void imprimirMenu() {
		System.out.println("===== BIENVENIDO A LA GESTIÓN DE EMPLEADOS AVANZADO =====");
		System.out.println("1. Añadir empleado");
		System.out.println("2. Listar empleados");
		System.out.println("3. Buscar empleado por DNI");
		System.out.println("4. Modificar horas extra");
		System.out.println("5. Modificar bonificación");
		System.out.println("6. Modificar precio hora extra (Global)");
		System.out.println("7. Eliminar empleado");
		System.out.println("8. Ver estadísticas");
		System.out.println("9. Salir");
		System.out.print("> ");
	}

	/**
	 * * Orquestación del alta de un nuevo vehículo. Valida que la matrícula no
	 * exista y los rangos de datos
	 */
	private static void gestionarAlta(Scanner sc, ListadoEmpleados gestor) {
		System.out.print("Dni: ");
		String dni = sc.nextLine();
		if (gestor.buscarPorDni(dni) != null) {
			System.out.println("Error: El empleado ya existe");
			return;
		}
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		double salarioBase = leerDoubleSeguro(sc, "Salario base: ");
		double porcentajeBonificacion = leerDoubleSeguro(sc, "Porcentaje de bonificación (0-30): ");
		try {
			Empleado nuevo = new Empleado(dni, nombre, salarioBase, porcentajeBonificacion);
			gestor.anadirEmpleado(nuevo);
			System.out.println("Vehículo registrado con éxito.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	/** Busca y muestra un vehículo específico */
	private static void gestionarBusqueda(Scanner sc, ListadoEmpleados gestor) {
		System.out.print("DNI a buscar: ");
		Empleado e = gestor.buscarPorDni(sc.nextLine());
		if (e != null) {
			System.out.println(e);
		} else {
			System.out.println("No se encontró ningún empleado con ese DNI");
		}
	}

	/** Actualiza los días que un vehículo ha sido alquilado */
	private static void gestionarModificacionHoras(Scanner sc, ListadoEmpleados gestor) {
		System.out.print("DNI: ");
		String dni = sc.nextLine();
		int horas = (int) leerDoubleSeguro(sc, "Nuevas horas extra: ");
		if (gestor.modificarHorasExtra(dni, horas)) {
			System.out.println("Horas extra actualizadas correctamente");
		} else {
			System.out.println("Error: Empleado no encontrado.");
		}
	}

	/** Modifica el recargo premium validando el rango de negocio */
	private static void gestionarModificacionBonificacion(Scanner sc, ListadoEmpleados gestor) {
		System.out.print("DNI: ");
		String dni = sc.nextLine();
		Empleado e = gestor.buscarPorDni(dni);
		if (e != null) {
			double nuevoPorcentaje = leerDoubleSeguro(sc, "Nuevo porcentaje (0-30): ");
			try {
				e.setPorcentajeBonificacion(nuevoPorcentaje);
				System.out.println("Bonificación actualizada: ");
			} catch (IllegalArgumentException e1) {
				System.out.println("Error: " + e1.getMessage());
			}
		} else {
			System.out.println("Vehículo no encontrado");
		}
	}

	/** Modifica el atributo estático que afecta a toda la flota */
	private static void gestionarSeguroGlobal(Scanner sc) {
		double nuevoPrecio = leerDoubleSeguro(sc, "Nuevo precio de horas extra (global): ");
		System.out.print("¿Confirmar eliminación? (S/N): ");
		if (sc.nextLine().equalsIgnoreCase("S")) {
			Empleado.setPrecioHoraExtra(nuevoPrecio);
			System.out.println("Precio de horas extra actualizado para todos los empleados");
		} else {
			System.out.println("Operación cancelada");
		}
	}

	/** Gestiona la eliminación con confirmación */
	private static void gestionarEliminacion(Scanner sc, ListadoEmpleados gestor) {
		System.out.print("DNI a eliminar: ");
		String dni = sc.nextLine();
		System.out.print("¿Confirmar eliminación? (S/N): ");
		if (sc.nextLine().equalsIgnoreCase("S")) {
			if (gestor.eliminarPorDni(dni)) {
				System.out.println("Empleado eliminado del gestor");
			} else {
				System.out.println("Error: El empleado no existe");
			}
		}
	}

	/** Muestra el balance económico y los vehículos premium */
	private static void gestionarEstadisticas(ListadoEmpleados gestor) {
		System.out.println("--- GASTO TOTAL EN BONIFICACIONES ---");
		System.out.printf("Total por Recargos Premium: ", gestor.calcularGastoBonificaciones());
		System.out.println("--- EMPLEADOS CON ALTO DESEMPEÑO (>15%%) ---");
		gestor.listarEmpleadosAltoDesempenio();
	}

	/**
	 * * Método de utilidad para leer números decimales evitando cierres por error
	 * 
	 * @return El número validado
	 */
	private static double leerDoubleSeguro(Scanner sc, String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				return sc.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Error: Formato numérico inválido");
				sc.nextLine(); // LIMPIA
			}
		}
	}

}
