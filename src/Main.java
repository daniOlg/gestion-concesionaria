import gestion.Concesionaria;
import gestion.Gestor;
import vehiculos.Auto;
import vehiculos.Camioneta;
import vehiculos.Motocicleta;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Camioneta camioneta = new Camioneta("MAZDA", "CX3", 2020, 231421, "SH23IJ");
        Motocicleta motocicleta = new Motocicleta("YAMAHA", "YTZ", 2019, 9200, "KF123");
        Auto auto = new Auto("PEUGEOT", "307", 2001, 300142, "ABC123");
        Camioneta camioneta1 = new Camioneta("CAMIO123", "AB1", 2006, 231421, "ABCC12");
        Motocicleta motocicleta1 = new Motocicleta("MOTO123", "AC2", 2003, 9200, "AC234");
        Auto auto1 = new Auto("AU123", "AD3", 2008, 300142, "AAABBB");

        // crear instancias
        Concesionaria concesionaria = Concesionaria.nuevaConcesionaria();  // creamos singleton concesionaria
        Gestor gestor = Gestor.nuevoGestor(concesionaria); // agregamos concesionaria a singleton gestor

        // vehiculos de ejemplo de la concesionaria
        System.out.println("------------- Vehiculos de prueba -------------");
        concesionaria.agregarVehiculo(camioneta);
        concesionaria.agregarVehiculo(motocicleta);
        concesionaria.agregarVehiculo(auto);
        concesionaria.agregarVehiculo(camioneta1);
        concesionaria.agregarVehiculo(motocicleta1);
        concesionaria.agregarVehiculo(auto1);
        System.out.println("------------------------------");

        // iniciar programa usuario
        gestor.iniciarPrograma();
    }
}
