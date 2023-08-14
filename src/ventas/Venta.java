package ventas;

import java.util.Date;

public class Venta {
    private float monto;
    private Date fecha;
    private int codigo;
    private String nombreComprador;
    private String apellidoComprador;
    private String rut;

    public Venta(float monto, Date fecha, int codigo, String nombreComprador, String apellidoComprador, String rut) {
        this.monto = monto;
        this.fecha = fecha;
        this.codigo = codigo;
        this.nombreComprador = nombreComprador;
        this.apellidoComprador = apellidoComprador;
        this.rut = rut;
    }

    public String toString() {
        return "\t- Monto: $" + monto +
                "\n\t- Fecha: " + fecha +
                "\n\t- Codigo: " + codigo +
                "\n\t- Nombre: " + nombreComprador + " " + apellidoComprador +
                "\n\t- Rut: " + rut;
    }

    public int getCodigo() {
        return this.codigo;
    }
}
