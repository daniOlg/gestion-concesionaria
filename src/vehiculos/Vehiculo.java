package vehiculos;

public abstract class Vehiculo {
    private int codigo;
    private String marca;
    private String modelo;
    private int fabricado;
    private int kilometraje;
    private String patente;

    public abstract String toString();

    public abstract void setCodigo(int codigo);

    public abstract int getCodigo();

    public abstract int getFabricado();
    public abstract TipoVehiculo getTipo();
    public abstract String getPatente();

    public abstract String getDatosCreacion();
}
