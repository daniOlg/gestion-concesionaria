package gestion;

import utils.Utils;
import vehiculos.*;

import java.util.*;

public class GestorVehiculos {
    // usamos treemap para mapear por fecha asi listar siempre ordenado por fecha
    private TreeMap<Integer, Vehiculo> disponibles = new TreeMap<>();
    private TreeMap<Integer, Vehiculo> vendidos = new TreeMap<>();
    private HashSet<Integer> codigos = new HashSet<>();

    private final int CODIGO_MINIMO = 0, CODIGO_MAXIMO = 999999999;


    // Singleton
    private static GestorVehiculos instaciaGestorVehiculos;
    private GestorVehiculos() {}
    public static GestorVehiculos nuevoGestorVehiculos() {
        // si no existe la instncia crea una
        if (instaciaGestorVehiculos == null)
            instaciaGestorVehiculos = new GestorVehiculos();

        // retorna la instancia, ya sea la nueva (si no existia) o la existente
        return instaciaGestorVehiculos;
    }


    // cuando la concesionaria compra un vehículo y lo agrega a los disponibles
    public void agregarVehiculo(Vehiculo vehiculo) {
        // generar código para vehiculo a agregar
        while (true) {
            int codigo = Utils.generarCodigo(CODIGO_MINIMO, CODIGO_MAXIMO);

            // comprobar si ya existe el código
            if(!codigos.contains(codigo)) {
                vehiculo.setCodigo(codigo);
                break;
            }
        }

        // añadir vehículo a la lista de disponibles con la fecha de fabricacion
        disponibles.put(vehiculo.getFabricado(), vehiculo);

        System.out.println("Vehiculo agregado con exito:");
        System.out.println(vehiculo.getDatosCreacion());
    }

    public void buscarPorCodigo(int codigo) {
        Vehiculo disponible = buscarVehiculoPorCodigo(codigo, disponibles);
        Vehiculo vendido = buscarVehiculoPorCodigo(codigo, vendidos);

        // si existe informa y retorna al menu
        if(disponible == null && vendido == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        // dependiendo de si esta vendido o no muestra feedback
        if(disponible != null) {
            System.out.println(disponible);
            System.out.println("\t- Listado: Disponible");
        } else {
            System.out.println(vendido);
            System.out.println("\t- Listado: Vendido ]");
        }
    }

    public void buscarPorPatente(String patente) {
        Vehiculo disponible = buscarVehiculoPorPatente(patente, disponibles);
        Vehiculo vendido = buscarVehiculoPorPatente(patente, vendidos);

        // si existe informa y retorna al menu
        if(disponible == null && vendido == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        // dependiendo de si esta vendido o no muestra feedback
        if(disponible != null) {
            System.out.println(disponible);
            System.out.println("\t- Listado: Disponible");
        } else {
            System.out.println(vendido);
            System.out.println("\t- Listado: Vendido ]");
        }
    }


    // al vender se elimina de los disponibles y se agrega a los vendidos
    public void venderVehiculo(int codigo) {
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigo, disponibles);

        // si existe informa y retorna al menu
        if(vehiculo == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        disponibles.remove(vehiculo.getFabricado()); // si existe lo elimina de los disponibles
        vendidos.put(vehiculo.getFabricado(), vehiculo); // y lo añade a la lista de vendidos

        System.out.println("Vehiculo codigo #" + vehiculo.getCodigo() + " vendido con exito");
    }

    // modificar un vehículo ya creado
    public void modificarVehiculo(int codigo, Vehiculo vehiculoModificado) {
        boolean vendido = false;

        // busca el vehículo en los disponibles
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigo, disponibles, vendidos);

        // si no lo encuentra informa y retorna
        if(vehiculo == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        // asigna el código del vehículo al modificado
        vehiculoModificado.setCodigo(vehiculo.getCodigo());

        // elimina el anterior y añade el modificado (disponible/vendido)
        if(vendido) {
            vendidos.remove(vehiculo.getFabricado());
            vendidos.put(vehiculoModificado.getFabricado(), vehiculoModificado);
        } else {
            disponibles.remove(vehiculo.getFabricado());
            disponibles.put(vehiculoModificado.getFabricado(), vehiculoModificado);
        }
    }

    // listar los vehículos disponibles
    public void listarDisponibles() {
        if(disponibles.isEmpty())
            System.out.println("No hay vehiculos disponibles"); // si no hay vehiculos disponibles
        else
            System.out.println("Vehiculos disponibles:");// si hay uno o mas vehiculos disponibles

        int i = 1;
        for(Map.Entry<Integer, Vehiculo> entry: disponibles.entrySet()) {
            System.out.println("[+] Disponible #"+(i++)+":");
            System.out.println(entry.getValue());
            System.out.println();
        }
    }

    // listar los vehículos vendidos
    public void listarVendidos() {
        if(vendidos.isEmpty())
            System.out.println("No hay vehiculos vendidos"); // si no hay vehiculos vendidos
        else
            System.out.println("Vehiculos vendidos:"); // si hay uno o mas vehiculos vendidos

        int i = 1;
        for(Map.Entry<Integer, Vehiculo> entry : vendidos.entrySet()) {
            System.out.println("[-] Vendido #"+(i++)+":");
            System.out.println(entry.getValue());
            System.out.println();
        }
    }

    // listar por tipo
    public void listarPorTipo(TipoVehiculo tipo) {
        int i = 1;
        for(Map.Entry<Integer, Vehiculo> entry: disponibles.entrySet()) {
            if(entry.getValue().getTipo() == tipo) {
                System.out.println("[+] Disponible #"+(i++)+":");
                System.out.println(entry.getValue());
                System.out.println();
            }
        }
    }

    // buscar vehiculo por codigo en alguna lista y retornarlo
    private Vehiculo buscarVehiculoPorCodigo(int codigo, TreeMap<Integer, Vehiculo> lista) {
        for(Map.Entry<Integer, Vehiculo> entry: lista.entrySet())
            if(entry.getValue().getCodigo() == codigo)
                return entry.getValue();

        // si no lo encuentra retorna null
        return null;
    }

    // sobrecarga para comprobar mas de una lista
    private Vehiculo buscarVehiculoPorCodigo(int codigo, TreeMap<Integer, Vehiculo>... listas) {
        for(TreeMap<Integer, Vehiculo> lista : listas)
            for(Map.Entry<Integer, Vehiculo> entry: lista.entrySet())
                if(entry.getValue().getCodigo() == codigo)
                    return entry.getValue();

        // si no lo encuentra retorna null
        return null;
    }

    // buscar vehiculo por patente en alguna lista y retornarlo
    private Vehiculo buscarVehiculoPorPatente(String patente, TreeMap<Integer, Vehiculo> lista) {
        for(Map.Entry<Integer, Vehiculo> entry: lista.entrySet())
            if(Objects.equals(entry.getValue().getPatente(), patente))
                return entry.getValue();

        // si no lo encuentra retorna null
        return null;
    }

    // sobrecarga para comprobar mas de una lista
    private Vehiculo buscarVehiculoPorPatente(String patente, TreeMap<Integer, Vehiculo>... listas) {
        for(TreeMap<Integer, Vehiculo> lista : listas)
            for(Map.Entry<Integer, Vehiculo> entry: lista.entrySet())
                if(Objects.equals(entry.getValue().getPatente(), patente))
                    return entry.getValue();

        // si no lo encuentra retorna null
        return null;
    }
}
