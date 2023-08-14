package gestion;

import utils.Utils;
import vehiculos.*;

import java.util.ArrayList;
import java.util.HashSet;

public class GestorVehiculos {
    private ArrayList<Vehiculo> disponibles = new ArrayList<>();
    private ArrayList<Vehiculo> vendidos = new ArrayList<>();
    private HashSet<Integer> codigos = new HashSet<>();

    private final int CODIGO_MINIMO = 0, CODIGO_MAXIMO = 999999999;

    // cuando la concesionaria compra un vehículo y lo agrega a los disponibles
    public void agregarVehiculo(Vehiculo vehiculo) {
        // generar código
        while (true) {
            int codigo = Utils.generarCodigo(CODIGO_MINIMO, CODIGO_MAXIMO);

            // comprobar si ya existe el código
            if(!codigos.contains(codigo)) {
                vehiculo.setCodigo(codigo);
                break;
            }
        }

        // TODO: comprobar si ya existe un vehiculo con la misma patente

        // añadir vehículo a la lista de disponibles (código ya asignado)
        disponibles.add(vehiculo);

        System.out.println("Vehiculo agregado con exito:");
        System.out.println(vehiculo);
    }


    // cuando la concesionaria vende un vehículo y lo agrega a los vendidos
    public void venderVehiculo(int codigo) {
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigo, disponibles);

        // si no lo encuentra informa y retorna
        if(vehiculo == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        disponibles.remove(vehiculo);   // si existe lo elimina de los disponibles
        vendidos.add(vehiculo);         // y lo añade a la lista de vendidos

        System.out.println("Vehiculo codigo: " + vehiculo.getCodigo() + " vendido con exito");
    }

    // cuando la concesionaria necesita modificar un vehículo
    public void modificarVehiculo(int codigo, Vehiculo vehiculoModificado) {
        boolean vendido = false;

        // busca el vehículo en los disponibles
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigo, disponibles);

        // si el vehículo no existe en los disponibles lo busca en los vendidos
        if(vehiculo == null) {
            vehiculo = buscarVehiculoPorCodigo(codigo, vendidos);
            vendido = true;
        }

        // si no lo encuentra informa y retorna
        if(vehiculo == null) {
            System.out.println("El vehiculo solicitado no existe");
            return;
        }

        // asigna el código del vehículo al modificado
        vehiculoModificado.setCodigo(vehiculo.getCodigo());

        // elimina el anterior y añade el modificado (disponible/vendido)
        if(vendido) {
            vendidos.remove(vehiculo);
            vendidos.add(vehiculoModificado);
        } else {
            disponibles.remove(vehiculo);
            disponibles.add(vehiculoModificado);
        }
    }

    // listar los vehículos disponibles
    public void listarDisponibles() {
        if(disponibles.isEmpty())
            System.out.println("No hay vehiculos disponibles");
        else
            System.out.println("Vehiculos disponibles:");

        int i = 1;
        for(Vehiculo vehiculo : disponibles) {
            System.out.println("Disponible #"+(i++)+":");
            System.out.println(vehiculo);
        }
    }

    // listar los vehículos vendidos
    public void listarVendidos() {
        if(vendidos.isEmpty())
            System.out.println("No hay vehiculos vendidos");
        else
            System.out.println("Vehiculos vendidos:");

        int i = 1;
        for(Vehiculo vehiculo : vendidos) {
            System.out.println("Vendido #"+(i++)+":");
            System.out.println(vehiculo);
        }
    }

    private Vehiculo buscarVehiculoPorCodigo(int codigo, ArrayList<Vehiculo> lista) {
        // buscar vehiculo y retornarlo
        for(Vehiculo vehiculo : lista) {
            if(vehiculo.getCodigo() == codigo) return vehiculo;
        }

        // si no lo encuentra retorna null
        return null;
    }
}
