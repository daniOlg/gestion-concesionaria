import gestion.GestorVehiculos;
import gestion.Gestor;
import vehiculos.Auto;
import vehiculos.Camioneta;
import vehiculos.Motocicleta;

public class Main {
    public static void main(String[] args) {
        Camioneta camioneta1 = new Camioneta("TOYOTA", "HILUX", 2023, 9200, "FJKL01");
        Camioneta camioneta = new Camioneta("MAZDA", "CX3", 2007, 231421, "SH23IJ");

        Auto auto = new Auto("PEUGEOT", "306", 2001, 300142, "AB12CD");
        Auto auto1 = new Auto("FORD", "MUSTANG SHELBY GT500", 2016, 120322, "HK14XF");

        Motocicleta motocicleta = new Motocicleta("YAMAHA", "YTZ", 2011, 123001, "KF123");
        Motocicleta motocicleta1 = new Motocicleta("HARLEY-DAVIDSON", "SPORTSTER", 2022, 3676, "DF124");

        // crear instancias
        GestorVehiculos gestorVehiculos = GestorVehiculos.nuevaConcesionaria();  // creamos singleton concesionaria
        Gestor gestor = Gestor.nuevoGestor(gestorVehiculos); // agregamos concesionaria a singleton gestor

        // vehiculos de ejemplo de la concesionaria
        System.out.println("------------- Vehiculos de prueba -------------");
        gestorVehiculos.agregarVehiculo(camioneta);
        gestorVehiculos.agregarVehiculo(motocicleta);
        gestorVehiculos.agregarVehiculo(auto);
        gestorVehiculos.agregarVehiculo(camioneta1);
        gestorVehiculos.agregarVehiculo(motocicleta1);
        gestorVehiculos.agregarVehiculo(auto1);
        System.out.println("------------------------------");

        // iniciar programa usuario
        gestor.iniciarPrograma();
    }
}
