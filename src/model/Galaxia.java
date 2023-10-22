package model;

import java.util.Scanner;

public class Galaxia {

    private String nombre;
    private double distancia;
    private FormaGalaxia forma;
    private AgujeroNegro agujeroNegro;
    private Planeta[] planetas;
    private int numPlanetas;
    private Foto[] fotos;
    private int numFotos;

    public Galaxia(String nombre, double distancia, FormaGalaxia forma) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.forma = forma;
        this.agujeroNegro = null;
        this.planetas = new Planeta[20];
        this.numPlanetas = 0;
        this.fotos = new Foto[30];
        this.numFotos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public FormaGalaxia getForma() {
        return forma;
    }

    public AgujeroNegro getAgujeroNegro() {
        return agujeroNegro;
    }

    public void agregarAgujeroNegro(String nombre, double masa, double distancia, TipoAgujeroNegro tipo) {
        if (agujeroNegro == null) {
            AgujeroNegro agujero = new AgujeroNegro(nombre, masa, distancia, tipo);
            this.agujeroNegro = agujero;
            System.out.println("Agujero negro agregado.");
        } else {
            System.out.println("Ya hay un agujero negro en esta galaxia.");
        }
    }

    public boolean verificarNombrePlanetaUnico(String nombrePlaneta) {
        for (int i = 0; i < numPlanetas; i++) {
            if (planetas[i] != null && planetas[i].getNombre().equalsIgnoreCase(nombrePlaneta)) {
                return false;
            }
        }
        return true;
    }

    public void agregarPlaneta(String nombre, int numeroSatelites, double radio, double masa) {
        if (numPlanetas < planetas.length) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (!verificarNombrePlanetaUnico(nombre)) {
                    System.out.println("El nombre de planeta ingresado ya está asignado a otro planeta en esta galaxia.\n");
                    System.out.println("Ingrese un nuevo nombre para el planeta: ");
                    nombre = scanner.nextLine();
                }
            }
            planetas[numPlanetas] = new Planeta(nombre, numeroSatelites, radio, masa);
            numPlanetas++;
            System.out.println("Planeta agregado con éxito.");
        } else {
            System.out.println("No se pueden agregar más planetas a esta galaxia. Límite alcanzado.");
        }
    }

    public Planeta buscarPlaneta(String nombrePlaneta) {
        for (int i = 0; i < numPlanetas; i++) {
            if (planetas[i] != null && planetas[i].getNombre().equalsIgnoreCase(nombrePlaneta)) {
                return planetas[i];
            }
        }
        return null;
    }

    public Planeta getPlaneta(int indice) {
        if (indice >= 0 && indice < numPlanetas) {
            return planetas[indice];
        } else {
            return null;
        }
    }

    public void eliminarPlaneta(String nombrePlaneta) {
        for (int i = 0; i < numPlanetas; i++) {
            if (planetas[i] != null && planetas[i].getNombre().equalsIgnoreCase(nombrePlaneta)) {
                System.out.println("El planeta " + planetas[i].getNombre() + " se ha eliminado.");
                planetas[i] = null;

                for (int j = i; j < numPlanetas - 1; j++) {
                    planetas[j] = planetas[j + 1];
                }
                numPlanetas--;
                return;
            }
        }
        System.out.println("No se encontró un planeta en esta galaxia con ese nombre.");
    }

    public Planeta getPlaneta(String nombrePlaneta) {
        for (int i = 0; i < numPlanetas; i++) {
            if (planetas[i] != null && planetas[i].getNombre().equalsIgnoreCase(nombrePlaneta)) {
                return planetas[i];
            }
        }
        return null;
    }

    public void actualizarDatosPlaneta(String nombrePlaneta, int numeroSatelites, double radio, double masa) {
        Planeta planeta = getPlaneta(nombrePlaneta);
        if (planeta != null) {
            planeta.actualizarDatos(numeroSatelites, radio, masa);
            System.out.println("Datos del planeta actualizados con éxito.");
        } else {
            System.out.println("No se encontró un planeta con ese nombre indicado.");
        }
    }

    public int getNumPlanetas() {
        return numPlanetas;
    }

    public void agregarFoto(Foto foto) {
        if (numFotos < 30) {
            this.fotos[numFotos] = foto;
            numFotos++;
            System.out.println("Foto agregada a la galaxia: " + this.nombre);
        } else {
            System.out.println("No se pueden agregar más fotos a la galaxia: " + this.nombre);
        }
    }

    public AgujeroNegro getAgujeroNegro(String nombreAgujeroNegro) {
        return null;
    }
}
