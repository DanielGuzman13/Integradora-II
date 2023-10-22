package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ControllerGalaxia {
    private static Galaxia[] galaxias;
    private static int numGalaxias;
    private static final int capacidad = 50;

    public ControllerGalaxia() {
        galaxias = new Galaxia[capacidad];
        numGalaxias = 0;
    }

    public void agregarGalaxia(String nombre, double distancia, FormaGalaxia forma) {
        if (numGalaxias < capacidad) {
            Galaxia galaxia = new Galaxia(nombre, distancia, forma);
            galaxias[numGalaxias] = galaxia;
            numGalaxias++;
            System.out.println("Galaxia registrada exitosamente.");
        } else {
            System.out.println("No se pueden agregar más galaxias, se alcanzó la capacidad máxima de 50 galaxias.");
        }
    }

    public Planeta buscarPlaneta(String nombreGalaxia, String nombrePlaneta) {
        for (int i = 0; i < numGalaxias; i++) {
            if (galaxias[i] != null && galaxias[i].getNombre().equalsIgnoreCase(nombreGalaxia)) {
                return galaxias[i].buscarPlaneta(nombrePlaneta);
            }
        }
        return null;
    }

    public void agregarAgujeroNegro(int indice, String nombre, double distancia, double masa, TipoAgujeroNegro tipo) {
        if (indice >= 0 && indice < numGalaxias) {
            galaxias[indice].agregarAgujeroNegro(nombre, masa, distancia, tipo);
        } else {
            System.out.println("Índice de galaxia no válido.");
        }
    }

    public void actualizarDatosPlaneta(String nombreGalaxia, String nombrePlaneta, int nuevoNumeroSatelites, double nuevoRadioPlaneta, double nuevaMasaPlaneta) {
        Galaxia galaxia = buscarGalaxiaPorNombre(nombreGalaxia);
        if (galaxia != null) {
            Planeta planeta = galaxia.getPlaneta(nombrePlaneta);
            if (planeta != null) {
                planeta.actualizarDatos(nuevoNumeroSatelites, nuevoRadioPlaneta, nuevaMasaPlaneta);
                System.out.println("Datos del planeta actualizados con éxito.");
            } else {
                System.out.println("No se encontró un Planeta con el nombre especificado en la Galaxia: " + nombreGalaxia);
            }
        } else {
            System.out.println("No se encontró una Galaxia con el nombre especificado.");
        }
    }
    

    public void agregarPlaneta(int indice, String nombre, int numeroSatelites, double radio, double masa) {
        if (indice >= 0 && indice < numGalaxias) {
            galaxias[indice].agregarPlaneta(nombre, numeroSatelites, radio, masa);
        } else {
            System.out.println("Índice de galaxia no válido.");
        }
    }

    public int getNumGalaxias() {
        return numGalaxias;
    }

    public Galaxia getGalaxia(int indice) {
        if (indice >= 0 && indice < numGalaxias) {
            return galaxias[indice];
        } else {
            return null;
        }
    }

    public Galaxia buscarGalaxiaPorNombre(String nombreGalaxia) {

        for (int i = 0; i < numGalaxias; i++) {
            if (galaxias[i] != null && galaxias[i].getNombre().equalsIgnoreCase(nombreGalaxia)) {
                return galaxias[i];
            }
        }
        return null;
    }

    public Galaxia consultarGalaxiaMasLejana(Scanner scanner, ControllerGalaxia controller) {
        Galaxia galaxiaMasLejana = null;
        double distanciaMaxima = 0;

        for (int i = 0; i < numGalaxias; i++) {
            Galaxia galaxia = galaxias[i];
            if (galaxia != null && galaxia.getDistancia() > distanciaMaxima) {
                distanciaMaxima = galaxia.getDistancia();
                galaxiaMasLejana = galaxia;
            }
        }
        return galaxiaMasLejana;
    }

    public void agregarFotoAGalaxia(String nombreGalaxia, Foto foto) {
        Galaxia galaxia = buscarGalaxiaPorNombre(nombreGalaxia);
        if (galaxia != null) {
            galaxia.agregarFoto(foto);
            System.out.println("Foto agregada a la Galaxia: " + galaxia.getNombre());
        } else {
            System.out.println("No se encontró una Galaxia con el nombre especificado.");
        }
    }

    public void agregarFotoAPlaneta(String nombreGalaxia, String nombrePlaneta, Foto foto) {
        Galaxia galaxia = buscarGalaxiaPorNombre(nombreGalaxia);
        if (galaxia != null) {
            Planeta planeta = galaxia.getPlaneta(nombrePlaneta);
            if (planeta != null) {
                planeta.agregarFoto(foto);
                System.out.println("Foto agregada al Planeta: " + planeta.getNombre());
            } else {
                System.out.println("No se encontró un Planeta con el nombre especificado en la Galaxia: " + nombreGalaxia);
            }
        } else {
            System.out.println("No se encontró una Galaxia con el nombre especificado.");
        }
    }

    public void agregarFotoAAgujeroNegro(String nombreGalaxia, String nombreAgujeroNegro, Foto foto) {
        Galaxia galaxia = buscarGalaxiaPorNombre(nombreGalaxia);
        if (galaxia != null) {
            AgujeroNegro agujeroNegro = galaxia.getAgujeroNegro(nombreAgujeroNegro);
            if (agujeroNegro != null) {
                agujeroNegro.agregarFoto(foto);
                System.out.println("Foto agregada al Agujero Negro: " + agujeroNegro.getNombre());
            } else {
                System.out.println("No se encontró un Agujero Negro con el nombre especificado en la Galaxia: " + nombreGalaxia);
            }
        } else {
            System.out.println("No se encontró una Galaxia con el nombre especificado.");
        }
    }

    public void start() {
    }   
    
    public String telescopioConMasFotosRegistradas() {
        // Inicializamos las variables para llevar un seguimiento del telescopio con más fotos
        String telescopioMasFotos = null;
        int maxFotos = 0;
    
        // Recorremos todas las galaxias y sus elementos para contar las fotos por telescopio
        for (int i = 0; i < numGalaxias; i++) {
            Galaxia galaxia = galaxias[i];
            if (galaxia != null) {
                // Contador para fotos en la galaxia
                int fotosEnGalaxia = 0;
    
                // Recorremos las fotos de la galaxia y contamos las fotos para cada telescopio
                for (int j = 0; j < galaxia.numFotos; j++) {
                    Foto foto = galaxia.fotos[j];
                    if (foto != null) {
                        if (foto.getTelescopio() != null) {
                            fotosEnGalaxia++;
    
                            // Actualizamos el telescopio con más fotos si es necesario
                            if (fotosEnGalaxia > maxFotos) {
                                maxFotos = fotosEnGalaxia;
                                telescopioMasFotos = foto.getTelescopio();
                            }
                        }
                    }
                }
            }
        }
    
        if (telescopioMasFotos != null) {
            return "El telescopio con más fotos registradas es: " + telescopioMasFotos;
        } else {
            return "No se encontraron fotos registradas con telescopios.";
        }
    }

    public  void consultarGalaxia(Scanner scanner, ControllerGalaxia controller) {
        System.out.print("Ingrese el nombre de la galaxia que desea consultar: ");
        String nombreGalaxia = scanner.nextLine();
    
        Galaxia galaxia = controller.buscarGalaxiaPorNombre(nombreGalaxia);
    
        if (galaxia != null) {
            System.out.println("Información de la Galaxia: ");
            System.out.println("Nombre: " + galaxia.getNombre());
            System.out.println("Distancia al planeta Tierra (años luz): " + galaxia.getDistancia());
            System.out.println("Forma de la Galaxia: " + galaxia.getForma());
        } else {
            System.out.println("No se encontró una Galaxia con el nombre especificado.");
        }
    }

    public void agregarGalaxia (Scanner scanner, ControllerGalaxia controller){
        System.out.print("Ingrese el nombre de la galaxia: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la distancia al planeta tierra (años luz): ");
        double distancia = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("de las siguintes formas de galaxia: ");

        for (FormaGalaxia forma : FormaGalaxia.values()){
            System.out.println(forma.name());
        }
        System.out.println("Ingrese la forma de la galaxia: ");
        String formaInput = scanner.nextLine().toUpperCase();
        FormaGalaxia forma = FormaGalaxia.valueOf(formaInput);

        controller.agregarGalaxia(nombre, distancia, forma);
    }

    public  void agregarAgujeroNegro (Scanner scanner, ControllerGalaxia controller){
        System.out.println("De las siguientes galaxias:");
        for (int i=0; i<controller.getNumGalaxias(); i++ ){
            model.Galaxia galaxia = controller.getGalaxia(i);
            System.out.println((i+1)+ ". "+ galaxia.getNombre());
            model.AgujeroNegro agujeroNegro = galaxia.getAgujeroNegro();
            if (agujeroNegro != null){
                System.out.println("ya tiene registrado un agujero negro: " + agujeroNegro.getNombre());
            }
            else {
                System.out.println("N tiene registrado agujero negro.");
            }
        }
        
        System.out.println("Seleccione el numero de la galaxia donde agrgara el agujero negro: ");
        int indiceAN = scanner.nextInt();
        scanner.nextLine();       
        System.out.print("Ingrese el nombre del agujero negro: ");
        String nombreAN = scanner.nextLine();
        System.out.print("Ingrese la masa del agujero negro al planeta tierra (años luz): ");
        double masaAN = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese la distancia del agujero negro: ");
        double distanciaAN = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("de las siguiontes tipos de agujero negro: ");
        for (TipoAgujeroNegro tipo : TipoAgujeroNegro.values()){
            System.out.println(tipo.name());
        }
        System.out.println("Ingrese la forma de la galaxia: ");
        String tipoInput = scanner.nextLine().toUpperCase();
        TipoAgujeroNegro tipo = TipoAgujeroNegro.valueOf(tipoInput);

        controller.agregarAgujeroNegro(indiceAN-1, nombreAN, masaAN, distanciaAN, (model.TipoAgujeroNegro) tipo);
    }

    public void agregarPlaneta (Scanner scanner, ControllerGalaxia controller){
        System.out.println("de las siguientes galaxias:");
        for (int i=0; i<controller.getNumGalaxias(); i++ ){
            model.Galaxia galaxia = controller.getGalaxia(i);
            System.out.println((i+1)+ ". "+ galaxia.getNombre());
        }

        System.out.println("Seleccione el numero de la galaxia a la que pertenece el planeta: ");
        int indiceP = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del planeta: ");
        String nombreP = scanner.nextLine();
        System.out.println("Ingrese el numero de satelites que tiene el planeta: ");
        int numeroSatelites = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el radio del planeta: ");
        double radioP = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese la masa del planeta: ");
        double masaP = scanner.nextDouble();
        scanner.nextLine();
        
        controller.agregarPlaneta(indiceP-1, nombreP, numeroSatelites, radioP, masaP);
    }

    public void eliminarPlaneta (Scanner scanner, ControllerGalaxia controller){
        System.out.println("De las siguientes galaxias: ");
        for (int i = 0; i< controller.getNumGalaxias(); i++){
            model.Galaxia galaxia = controller.getGalaxia(i);
            System.out.println((i+1) + ". " + galaxia.getNombre());
        }

        System.out.println("Seleccione el numero de la galaxia donde esta el planeta que se eliminara: ");
        int indiceEliminarPlaneta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("ingrese el nombe del planeta que desea eliminar: ");
        String nombrePlanetaEliminar = scanner.nextLine();

        model.Galaxia galaxiaSeleccionada = controller.getGalaxia(indiceEliminarPlaneta-1);
        if (galaxiaSeleccionada != null){
            galaxiaSeleccionada.eliminarPlaneta(nombrePlanetaEliminar);
        }
        else{
            System.out.println("Índice de galaxia no válido");
        }
    }
    
    public void actualizarDatosPlaneta (Scanner scanner, ControllerGalaxia controller){
        System.out.print("Ingrese el nombre de la galaxia a la que pertenece el planeta: ");
        String nombreGalaxiaPlaneta = scanner.nextLine();
        System.out.print("Ingrese el nombre del planeta cuyos datos desea cambiar: ");
        String nombrePlanetaActualizar = scanner.nextLine();
        
        model.Planeta planetaActualizar = controller.buscarPlaneta(nombreGalaxiaPlaneta, nombrePlanetaActualizar);
        if (planetaActualizar != null) {
            System.out.print("Ingrese el nuevo número de satélites del planeta: ");
            int nuevoNumeroSatelites = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el nuevo radio del planeta: ");
            double nuevoRadioPlaneta = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Ingrese la nueva masa del planeta: ");
            double nuevaMasaPlaneta = scanner.nextDouble();
            scanner.nextLine();

            controller.actualizarDatosPlaneta(nombreGalaxiaPlaneta, nombrePlanetaActualizar, nuevoNumeroSatelites, nuevoRadioPlaneta, nuevaMasaPlaneta);
            System.out.println("Datos del planeta actualizados con éxito.");
        } 
        else {
            System.out.println("No se encontró un planeta con el nombre especificado en la galaxia especificada.");
        }
    }


    public void casosDePrueba(ControllerGalaxia controller) {
        // Crear algunas galaxias
        controller.agregarGalaxia("Via Láctea", 100000, FormaGalaxia.ESPIRAL);
        controller.agregarGalaxia("Andrómeda", 150000, FormaGalaxia.ESPIRAL);

        // Agregar agujeros negros a la galaxia
        controller.agregarAgujeroNegro(0, "Agujero Negro 1", 10000, 50000, TipoAgujeroNegro.SCHWARZSCHILD);
        controller.agregarAgujeroNegro(1, "Agujero Negro 2", 20000, 75000, TipoAgujeroNegro.KERR);

        // Agregar planetas a una galaxia
        controller.agregarPlaneta(0, "Tierra", 1, 6371, 5.972e24);
        controller.agregarPlaneta(1, "Marte", 2, 3389.5, 6.4171e23);

        // Consultar la galaxia más lejana
        Galaxia galaxiaMasLejana = consultarGalaxiaMasLejana(null, controller);
        System.out.println("La galaxia más lejana es: " + galaxiaMasLejana.getNombre());

        // Consultar y actualizar datos de un planeta
        Planeta planetaTierra = controller.buscarPlaneta("Via Láctea", "Tierra");
        if (planetaTierra != null) {
            System.out.println("Planeta Tierra - Masa: " + planetaTierra.getMasa());
            System.out.println("Actualizando datos del Planeta Tierra...");
            controller.actualizarDatosPlaneta("Via Láctea", "Tierra", 1, 6371, 5.972e24);
            System.out.println("Datos actualizados del Planeta Tierra - Masa: " + planetaTierra.getMasa());
        } else {
            System.out.println("No se encontró el Planeta Tierra.");
        }

        // Agregar fotos a la galaxia
        controller.agregarFotoAGalaxia("Via Láctea", new Foto("url1", new Date(), "Telescopio1"));
        controller.agregarFotoAGalaxia("Via Láctea", new Foto("url2", new Date(), "Telescopio2"));

        // Agregar fotos a un planeta
        controller.agregarFotoAPlaneta("Marte", "Planeta 1", new Foto("url3", new Date(), "Telescopio3"));

        // Agregar fotos a un agujero negro
        controller.agregarFotoAAgujeroNegro("Andrómeda", "Agujero Negro 2", new Foto("url4", new Date(), "Telescopio4"));
    }
    
    public void consultarAgujerosNegrosPorTipo(Scanner scanner, ControllerGalaxia controller) {
        System.out.println("Tipos de agujeros negros:");
        int index = 1;
        for (TipoAgujeroNegro tipo : TipoAgujeroNegro.values()) {
            System.out.println(index + ". " + tipo.name());
            index++;
        }

        System.out.print("Seleccione el tipo de agujero negro: ");
        int tipoSeleccionado = scanner.nextInt();
        scanner.nextLine();

        if (tipoSeleccionado >= 1 && tipoSeleccionado <= TipoAgujeroNegro.values().length) {
            TipoAgujeroNegro tipoElegido = TipoAgujeroNegro.values()[tipoSeleccionado - 1];
            
            System.out.println("Agujeros negros del tipo " + tipoElegido.name() + ":");
            for (int i = 0; i < controller.getNumGalaxias(); i++) {
                Galaxia galaxia = controller.getGalaxia(i);
                AgujeroNegro agujeroNegro = galaxia.getAgujeroNegro();
                
                if (agujeroNegro != null && agujeroNegro.getTipo() == tipoElegido) {
                    System.out.println("Nombre de la galaxia: " + galaxia.getNombre());
                    System.out.println("Nombre del agujero negro: " + agujeroNegro.getNombre());
                    System.out.println("----------------------------------------------");
                }
            }
        } else {
            System.out.println("Selección no válida. Por favor, elija un tipo válido.");
        }
    }


    public void consultarPlaneta(Scanner scanner, ControllerGalaxia controller) {
        System.out.print("Ingrese el nombre de la galaxia a la que pertenece el planeta: ");
        String nombreGalaxia = scanner.nextLine();
        
        Galaxia galaxia = controller.buscarGalaxiaPorNombre(nombreGalaxia);
    
        if (galaxia != null) {
            System.out.print("Ingrese el nombre del planeta que desea consultar: ");
            String nombrePlaneta = scanner.nextLine();
    
            Planeta planeta = galaxia.buscarPlaneta(nombrePlaneta);
    
            if (planeta != null) {
                System.out.println("Información del Planeta: ");
                System.out.println("Nombre: " + planeta.getNombre());
                System.out.println("Número de Satélites: " + planeta.getNumeroSatelites());
                System.out.println("Radio: " + planeta.getRadio());
                System.out.println("Masa: " + planeta.getMasa());
            } else {
                System.out.println("No se encontró un planeta con el nombre especificado en esta galaxia.");
            }
        } else {
            System.out.println("No se encontró una galaxia con el nombre especificado.");
        }
    }
    

    public void consultarPlanetaMayorDensidad(Scanner scanner, ControllerGalaxia controller) {
        double mayorDensidad = 0.0;
        Planeta planetaMayorDensidad = null;
    
        for (int i = 0; i < controller.getNumGalaxias(); i++) {
            Galaxia galaxia = controller.getGalaxia(i);
            for (int j = 0; j < galaxia.getNumPlanetas(); j++) {
                Planeta planeta = galaxia.getPlaneta(j);
                double densidad = planeta.calcularDensidad();
                if (densidad > mayorDensidad) {
                    mayorDensidad = densidad;
                    planetaMayorDensidad = planeta;
                }
            }
        }
    
        if (planetaMayorDensidad != null) {
            System.out.println("Planeta con mayor densidad: " + planetaMayorDensidad.getNombre());
            System.out.println("Número de Satélites: " + planetaMayorDensidad.getNumeroSatelites());
            System.out.println("Radio: " + planetaMayorDensidad.getRadio());
            System.out.println("Masa: " + planetaMayorDensidad.getMasa());
            System.out.println("Densidad: " + mayorDensidad);
        } else {
            System.out.println("No se encontró ningún planeta con densidad.");
        }
    }
     
    public void agregarFoto(Scanner scanner, ControllerGalaxia controller) {
        System.out.println("Seleccione dónde desea agregar la foto:");
        System.out.println("1. Galaxia");
        System.out.println("2. Planeta");
        System.out.println("3. Agujero Negro");
        System.out.print("Ingrese el número correspondiente: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre de la Galaxia: ");
                String nombreGalaxia = scanner.nextLine();
                Foto fotoGalaxia = solicitarInformacionFoto(scanner);
                controller.agregarFotoAGalaxia(nombreGalaxia, fotoGalaxia);
                break;
            case 2:
                System.out.print("Ingrese el nombre de la Galaxia: ");
                String nombreGalaxiaPlaneta = scanner.nextLine();
                System.out.print("Ingrese el nombre del Planeta: ");
                String nombrePlaneta = scanner.nextLine();
                Foto fotoPlaneta = solicitarInformacionFoto(scanner);
                controller.agregarFotoAPlaneta(nombreGalaxiaPlaneta, nombrePlaneta, fotoPlaneta);
                break;
            case 3:
                System.out.print("Ingrese el nombre de la Galaxia: ");
                String nombreGalaxiaAgujeroNegro = scanner.nextLine();
                System.out.print("Ingrese el nombre del Agujero Negro: ");
                String nombreAgujeroNegro = scanner.nextLine();
                Foto fotoAgujeroNegro = solicitarInformacionFoto(scanner);
                controller.agregarFotoAAgujeroNegro(nombreGalaxiaAgujeroNegro, nombreAgujeroNegro, fotoAgujeroNegro);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    
    private static Foto solicitarInformacionFoto(Scanner scanner) {
        System.out.print("Ingrese la URL de la foto: ");
        String url = scanner.nextLine();
    
        System.out.print("Ingrese la fecha en que se tomó la foto (dd/MM/yyyy HH:mm:ss): ");
        String fechaStr = scanner.nextLine();
        
        Date fecha = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Utilice el formato dd/MM/yyyy HH:mm:ss.");
            return null;
        }
    
        System.out.print("Ingrese el nombre del telescopio que tomó la foto: ");
        String telescopio = scanner.nextLine();
    
        return new Foto(url, fecha, telescopio);
    }
    
}