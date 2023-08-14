package vehiculos;

public class Camioneta extends Vehiculo {
    private int codigo;
    private String marca;
    private String modelo;
    private int fabricado;
    private int kilometraje;
    private String patente;
    private TipoVehiculo tipo = TipoVehiculo.CAMIONETA;

    public Camioneta(String marca, String modelo, int fabricado, int kilometraje, String patente) {
        this.marca = marca;
        this.modelo = modelo;
        this.fabricado = fabricado;
        this.kilometraje = kilometraje;
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "\t- Codigo: " + this.codigo+
                "\n\t- Marca: " + this.marca +
                "\n\t- Tipo: Camioneta" +
                "\n\t- Modelo: " + this.modelo +
                "\n\t- Año: " + this.fabricado +
                "\n\t- Kilometraje: " + this.kilometraje +
                "\n\t- Patente: " + this.patente;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int getCodigo() {
        return this.codigo;
    }
    public int getFabricado() {
        return this.fabricado;
    };
    public TipoVehiculo getTipo() {
        return this.tipo;
    }
    public String getPatente() {
        return this.patente;
    }
    public String getDatosCreacion() {
        return "- Codigo: " + this.codigo + ", Marca: " + this.marca + ", Patente: " + this.patente;
    }
}
