package gestion;

import vehiculos.*;
import ventas.Venta;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestor {
    GestorVehiculos gestorVehiculos;    // instancia pasada por parametros
    GestorVentas gestorVentas;          // instancia pasada por parametros

    // Singleton gestor
    private static Gestor instaciaGestor;

    private Gestor(GestorVehiculos gestorVehiculos, GestorVentas gestorVentas) {
        this.gestorVehiculos = gestorVehiculos;
        this.gestorVentas = gestorVentas;
    }

    public static Gestor nuevoGestor(GestorVehiculos gestorVehiculos, GestorVentas gestorVentas) {
        // si no existe la instncia crea una
        if (instaciaGestor == null)
            instaciaGestor = new Gestor(gestorVehiculos, gestorVentas);

        // retorna la instancia, ya sea la nueva (si no existia) o la existente
        return instaciaGestor;
    }

    public void iniciarPrograma() {
        final int VENDER_VEHICULO = 1,
                AGREGAR_VEHICULO = 2,
                BUSCAR_POR_CODIGO = 3,
                BUSCAR_POR_PATENTE = 4,
                LISTAR_DISPONIBLES = 5,
                LISTAR_VENDIDOS = 6,
                LISTAR_POR_TIPO = 7,
                MODIFICAR_VEHICULO = 8,
                LISTAR_VENTAS = 9,
                SALIR = 0;

        // loop infinito con opciones para el usuario
        while(true) {
            System.out.println();
            System.out.println("-- Gestion Concesionaria Automotriz --");
            System.out.println("Que desea hacer?:");
            System.out.println("1. Vender un vehiculo (codigo)");
            System.out.println("2. Agregar un vehiculo");
            System.out.println("3. Buscar por codigo");
            System.out.println("4. Buscar por patente");
            System.out.println("5. Listar vehiculos disponibles");
            System.out.println("6. Listar vehiculos vendidos");
            System.out.println("7. Listar vehiculos por tipo");
            System.out.println("8. Modificar un vehiculo (codigo)");
            System.out.println("9. Listar ventas realizadas");
            System.out.println("0. Salir");
            System.out.println();

            // inicializar scanner
            System.out.print("Opcion: ");
            Scanner scanner = new Scanner(System.in);

            try {
                // usar funcion que seleccione el usuario
                switch(scanner.nextInt()) {
                    case VENDER_VEHICULO -> venderVehiculo();
                    case AGREGAR_VEHICULO -> agregarVehiculo();
                    case BUSCAR_POR_CODIGO -> buscarPorCodigo();
                    case BUSCAR_POR_PATENTE -> buscarPorPatente();
                    case LISTAR_DISPONIBLES -> listarDisponibles();
                    case LISTAR_VENDIDOS -> listarVendidos();
                    case LISTAR_POR_TIPO -> listarPorTipo();
                    case MODIFICAR_VEHICULO -> modificarVehiculo();
                    case LISTAR_VENTAS -> listarVentas();
                    case SALIR -> System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una de las opciones disponibles...");
                scanner.nextLine();
            }
        }
    }

    void venderVehiculo() {
        Venta venta = crearVenta(); // creamos venta del vehiculo

        gestorVentas.crearVenta(venta);
        gestorVehiculos.venderVehiculo(venta.getCodigo());
    }

    void agregarVehiculo() {
        Vehiculo vehiculoCreado = crearVehiculo();

        // agrega el vehiculo creado
        gestorVehiculos.agregarVehiculo(vehiculoCreado);
    }

    void buscarPorCodigo() {
        Scanner scanner = new Scanner(System.in);

        // ingreso codigo vehiculo a modificar
        int codigo;
        while (true) {
            try {
                System.out.print("Codigo del vehiculo: ");
                codigo = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese los datos solicitados...");
                scanner.nextLine();
            }
        }

        gestorVehiculos.buscarPorCodigo(codigo);
    }

    void buscarPorPatente() {
        Scanner scanner = new Scanner(System.in);

        // ingreso codigo vehiculo a modificar
        String patente;
        while (true) {
            try {
                System.out.print("Patente del vehiculo: ");
                patente = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese los datos solicitados...");
                scanner.nextLine();
            }
        }

        gestorVehiculos.buscarPorPatente(patente);
    }

    void listarDisponibles() {
        gestorVehiculos.listarDisponibles();
    }

    void listarVendidos() {
        gestorVehiculos.listarVendidos();
    }

    void listarPorTipo() {
        Scanner scanner = new Scanner(System.in);

        int codigo;
        while (true) {
            try {
                System.out.println("Que tipo de vehiculo desea listar?");
                System.out.println("1. Auto");
                System.out.println("2. Camioneta");
                System.out.println("3. Motocicleta");

                codigo = scanner.nextInt();
                if(codigo < 1 || codigo > 3)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese los datos solicitados...");
                scanner.nextLine();
            }
        }

        TipoVehiculo tipo = switch (codigo) {
            case 2 -> TipoVehiculo.CAMIONETA;
            case 3 -> TipoVehiculo.MOTOCICLETA;
            default -> TipoVehiculo.AUTO;
        };

        gestorVehiculos.listarPorTipo(tipo);
    }

    void modificarVehiculo() {
        Scanner scanner = new Scanner(System.in);

        // ingreso codigo vehiculo a modificar
        int codigo;
        while (true) {
            try {
                System.out.print("Codigo del vehiculo a modificar: ");
                codigo = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese los datos solicitados...");
                scanner.nextLine();
            }
        }

        // creacion vehiculo modificado
        Vehiculo modificado = crearVehiculo();

        // llamar metodo para modificar vehiculo en concesionaria
        gestorVehiculos.modificarVehiculo(codigo, modificado);
    }

    void listarVentas() {
        gestorVentas.listarVentas();
    }

    Vehiculo crearVehiculo() {

        int tipo;
        String marca;
        String modelo;
        int fabricado;
        int kilometraje;
        String patente;

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Por favor proporcione los siguientes datos:");

                // tipo
                System.out.println("Seleccione el tipo de vehiculo:");
                System.out.println("1. Auto");
                System.out.println("2. Camioneta");
                System.out.println("3. Motocicleta");
                tipo = scanner.nextInt();
                scanner.nextLine();

                // marca
                System.out.print("Marca: ");
                marca = scanner.nextLine();

                // modelo
                System.out.print("Modelo: ");
                modelo = scanner.nextLine();

                // fabricacion
                System.out.print("Año: ");
                fabricado = scanner.nextInt();
                scanner.nextLine();

                // kilometraje
                System.out.print("Kilometraje: ");
                kilometraje = scanner.nextInt();
                scanner.nextLine();

                // patente
                System.out.print("Patente: ");
                patente = scanner.nextLine();

                // si el tipo no esta en el rango de seleccion
                if (tipo > 3 || tipo < 1)
                    throw new InputMismatchException();

                // si la seleccion fue correcta
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nError, por favor ingrese los datos solicitados...\n");
            }
        }

        // dependiendo del vehiculo retorna uno u otro
        return switch (tipo) {
            case 2 -> new Camioneta(marca, modelo, fabricado, kilometraje, patente);
            case 3 -> new Motocicleta(marca, modelo, fabricado, kilometraje, patente);
            default -> new Auto(marca, modelo, fabricado, kilometraje, patente);
        };
    }

    Venta crearVenta() {
        float monto;
        Date fecha = new Date(); // establecer fecha actual
        int codigo;
        String nombreComprador;
        String apellidoComprador;
        String rut;

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Por favor proporcione los siguientes datos:");

                // monto
                System.out.print("Monto: ");
                monto = scanner.nextFloat();

                // codigo
                System.out.print("Codigo vehiculo: ");
                codigo = scanner.nextInt();
                scanner.nextLine();

                // nombreComprador
                System.out.print("Nombre comprador: ");
                nombreComprador = scanner.nextLine();

                // apellidoComprador
                System.out.print("Apellido comprador: ");
                apellidoComprador = scanner.nextLine();

                // rut
                System.out.print("Rut: ");
                rut = scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.out.println("\nError, por favor ingrese los datos solicitados...\n");
            }
        }

        // TODO: checar si existe el vehiculo por codigo

        return new Venta(monto, fecha, codigo, nombreComprador, apellidoComprador, rut);
    }
}
