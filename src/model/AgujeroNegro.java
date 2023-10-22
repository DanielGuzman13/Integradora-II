package model;



public class AgujeroNegro {

    private String nombre;
    private double masa;
    private double distancia;
    private TipoAgujeroNegro tipo;
    private Foto[] fotos;
    private int numFotos;

    public AgujeroNegro(String nombre, double masa, double distancia, TipoAgujeroNegro tipo) {
        this.nombre = nombre;
        this.masa = masa;
        this.distancia = distancia;
        this.tipo = tipo;
        this.fotos = new Foto[5];
        this.numFotos = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public double getMasa(){
        return masa;
    }

    public double getDistancia(){
        return distancia;
    }

    public TipoAgujeroNegro getTipo(){
        return tipo;
    }

    public void agregarFoto(Foto foto) {
        if (numFotos < 5) { // Comprueba si hay espacio para agregar la foto
            this.fotos[numFotos] = foto;
            numFotos++;
            System.out.println("Foto agregada al Agujero Negro: " + this.nombre);
        } else {
            System.out.println("No se pueden agregar más fotos al Agujero Negro: " + this.nombre);
        }
    }
 
}
