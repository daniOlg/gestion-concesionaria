package gestion;

import vehiculos.Vehiculo;
import ventas.Venta;

import java.util.ArrayList;
import java.util.Map;

public class GestorVentas {
    private ArrayList<Venta> ventas = new ArrayList<>();

    // Singleton
    private static GestorVentas instaciaGestorVentas;
    private GestorVentas() {}
    public static GestorVentas nuevoGestorVentas() {
        // si no existe la instncia crea una
        if (instaciaGestorVentas == null)
            instaciaGestorVentas = new GestorVentas();

        // retorna la instancia, ya sea la nueva (si no existia) o la existente
        return instaciaGestorVentas;
    }

    // agregar venta a la lista de ventas
    public void crearVenta(Venta venta) {
        ventas.add(venta);
    }


    // listar ventas realizadas
    public void listarVentas() {
        if(ventas.isEmpty())
            System.out.println("No hay ventas realizadas"); // si no hay ventas
        else
            System.out.println("Ventas realizadas:");// si hay una o mas ventas

        int i = 1;
        for(Venta venta: ventas) {
            System.out.println("Venta #"+(i++)+":");
            System.out.println(venta);
            System.out.println();
        }
    }
}
