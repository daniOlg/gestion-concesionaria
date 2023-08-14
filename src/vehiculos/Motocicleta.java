package vehiculos;

public class Motocicleta extends Vehiculo {
    private int codigo;
    private String marca;
    private String modelo;
    private int fabricado;
    private int kilometraje;
    private String patente;

    public Motocicleta(String marca, String modelo, int fabricado, int kilometraje, String patente) {
        this.marca = marca;
        this.modelo = modelo;
        this.fabricado = fabricado;
        this.kilometraje = kilometraje;
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "- Codigo: " + this.codigo+
                "\n- Marca: " + this.marca +
                "\n- Tipo: Motocicleta" +
                "\n- Modelo: " + this.modelo +
                "\n- Año: " + this.fabricado +
                "\n- Kilometraje: " + this.kilometraje +
                "\n- Patente: " + this.patente;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int getCodigo() {
        return this.codigo;
    }
}
