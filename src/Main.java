import gestion.GestorGeneral;
import vehiculos.Camioneta;
import vehiculos.Motocicleta;

public class Main {
    public static void main(String[] args) {
        Camioneta c = new Camioneta("MAZDA", "CX3", 2020, 231421, "SH23IJ");
        Motocicleta m = new Motocicleta("YAMAHA", "YTZ", 2023, 9200, "KF123");

        Motocicleta modif = new Motocicleta("YAMAHA", "YTZ", 2023, 9200, "KF321");

        GestorGeneral gestorGeneral = new GestorGeneral();
        gestorGeneral.iniciarPrograma();
    }
}
