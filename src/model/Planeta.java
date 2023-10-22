package model;

public class Planeta {

    private String nombre;
    private int numeroSatelites;
    private double radio;
    private double masa;
    private Foto[] fotos;
    private int numFotos;
    private double volumen;
    private double densidad;
    
    public Planeta (String nombre, int numeroSatelites, double radio, double masa){
        this.nombre = nombre;
        this.numeroSatelites = numeroSatelites;
        this.radio = radio;
        this.masa = masa;
        this.fotos = new Foto[50];
        this.numFotos = 0;
    }

    public String getNombre (){
        return nombre;
    }
    
    public int getNumeroSatelites (){
        return numeroSatelites;
    }

    public double getRadio (){
        return radio;
    }
    
    public double getMasa (){
        return masa;
    }

    public void actualizarDatos(int numeroSatelites, double radio, double masa){
        this.numeroSatelites = numeroSatelites;
        this.radio = radio;
        this.masa = masa;  
    }

    public double calcularVolumen(){
        volumen = (4/3) * Math.PI *Math.pow(radio,3);
        return volumen;
    }

    public double calcularDensidad(){
        double densidad;
        densidad = (masa/volumen);
        return densidad;
    }
     
    public double getDensidad(){
        return densidad;
    }
    
    public void agregarFoto(Foto foto) {
        if (numFotos < 5) { // Comprueba si hay espacio para agregar la foto
            this.fotos[numFotos] = foto;
            numFotos++;
            System.out.println("Foto agregada al planeta: " + this.nombre);
        } else {
            System.out.println("No se pueden agregar más fotos al planeta: " + this.nombre);
        }
    }

    
}
