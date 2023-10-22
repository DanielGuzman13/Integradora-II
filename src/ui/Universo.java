package ui;


import java.util.Scanner;




import model.ControllerGalaxia;


public class Universo{
  

      public static void main(String[] args) {
        ControllerGalaxia controller = new ControllerGalaxia();
        controller.start();
        menu(controller);
    }


    private static void menu(ControllerGalaxia controller){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir){
            System.out.println("\n Bienvenid@ al Menú");
            System.out.println("1. Registrar una galaxia. \n");
            System.out.println("2. Registrar un agujero negro. \n");
            System.out.println("3. Registrar un planeta. \n");
            System.out.println("4. Eliminar un planeta. \n");
            System.out.println("5. Modificar datos de un planeta. \n");
            System.out.println("6. Agregar una foto a un planeta. \n");
            System.out.println("7. Consultar la informacion de una Galaxia. \n");
            System.out.println("8. Consultar la informacion de un planeta. \n");
            System.out.println("9. Consultar nombre de Galaxia mas alejada de planeta tierra. \n");
            System.out.println("10. Consultar nombre del planeta con mayor densidad. \n");
            System.out.println("11. Consultar agujeros negros por tipo. \n");
            System.out.println("12. Nombre del telescopio con mas fotos registradas. \n");
            System.out.println("13. Creacion de casos de prueba. \n");
            System.out.println("14. Salir. \n");

            System.out.println("Digite el numero de la opcion que desea seleccionar:  \n");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    controller.agregarGalaxia(scanner, controller);
                break;
                case 2:
                    controller.agregarAgujeroNegro(scanner, controller);
                break;
                case 3:
                    controller.agregarPlaneta(scanner, controller);
                break;
                case 4:
                    controller.eliminarPlaneta(scanner, controller);
                break;
                case 5: 
                    controller.actualizarDatosPlaneta(scanner, controller);
                break;
                case 6:
                   controller.agregarFoto(scanner, controller);
                break;
                case 7:
                   controller.consultarGalaxia(scanner, controller);
                break;
                case 8:
                   controller.consultarPlaneta(scanner, controller);
                break;
                case 9:
                   controller.consultarGalaxiaMasLejana (scanner, controller);
                break;
                case 10:
                   controller.consultarPlanetaMayorDensidad(scanner, controller);
                break;
                case 11:
                   controller.consultarAgujerosNegrosPorTipo(scanner, controller);
                break;
                case 12:
                   controller.telescopioConMasFotosRegistradas();
                break;
                case 13:
                   controller.casosDePrueba(controller);
                break;
                case 14: 
                    salir = true;
                    System.out.println("Hasta luego");
                
                    
                break;
                default: 
                System.out.println("Opcion no valida, por favor ingrese nuevamente otra opcion");
                break;

            }
            
        }
        scanner.close();
        
    } 
  
    
    
    

    
    
}