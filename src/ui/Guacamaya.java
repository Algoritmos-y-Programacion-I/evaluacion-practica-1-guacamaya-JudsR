package ui;

import java.util.Scanner;

public class Guacamaya {

    // Scanner global para todo el programa
    public static Scanner reader;
    // Arreglos de precios y unidades para todo el programa
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        int referencias = establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos(referencias, precios, unidades);
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas(unidades));
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio(precios));
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales(precios, unidades));
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite, precios, unidades)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static int establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

        return referencias;
    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el precio de cada producto y la cantidad que compraro del mismo 
     * pre: Los arreglos precios y unidades debe estar inicializados y declarados
     * pos: Los arreglos precios y unidades quedan actualizados
     */
    public static void solicitarDatos(int referencias, double[] precios, int[] unidades){
        int cantidad, numProducto = 0;
        double precio;

        for (int i = 0; i < referencias; i++) {
            numProducto ++;

            System.out.println("\nIngrese el precio del producto "+numProducto+":");
            precio = reader.nextInt();
            reader.nextLine();
            precios[i] = precio;

            System.out.println("\nAhora ingrese la cantidad del producto "+numProducto+":");
            cantidad = reader.nextInt();
            reader.nextLine();
            unidades[i] = cantidad;
        }
    }

    /**
     * Descripcion: Este metodo se encarga de sumar la cantidad de unidades ventidas para calcular un total
     * pre: El arreglo de unidades debe estar inicializado y declarado
     * pos: EL arreglo de unidades queda actualizado
     */
    public static int calcularTotalUnidadesVendidas(int[] unidades){
        int cantidadTotal = 0;
        
        for (int i = 0; i < unidades.length; i++) {
            cantidadTotal += unidades[i];
        }
        return cantidadTotal;

    }

    /**
     * Descripcion: Este metodo se encarga de sumar los precios de las unidades para calcular un promedio
     * pre: El arreglo de precios debe estar inicializado y declarado
     * pos: Retorna un double con el valor promedio de los precios
     */
    public static double calcularPrecioPromedio(double[] precios){
        double precioPromedio, sumaPrecios = 0;
        
        for (int i = 0; i < precios.length; i++) {
            sumaPrecios += precios[i];
        }

        precioPromedio = sumaPrecios/precios.length;
        return precioPromedio;

    }

    /**
     * Descripcion: Este metodo se encarga de calcular las ventas totales multiplicando las cantidades de los productos por sus respectivos precios 
     * pre: Los arreglos precios y unidades debe estar inicializados y declarados
     * pos: Retorna la suma de las ventas de cada producto
     */
    public static double calcularVentasTotales(double[] precios, int[] unidades){
        double precioRef = 0;

        for (int i = 0; i < unidades.length; i++) {
            precioRef += precios[i]*unidades[i];
        }

        return precioRef;

    }

    /**
     * Descripcion: Este metodo se encarga de evaluar cuantos productos se han superado el minimo de ventas propuesto 
     * por el usuario
     * pre: Los arreglos precios y unidades debe estar inicializados y declarados. Ademas, antes se debe haber inicializado
     * y delarado el int que impone el limite
     * pos: Retorna el contador de la cantidad de productos que superaron el minimo
     */
    public static int consultarReferenciasSobreLimite(double limite, double[] precios, int[] unidades){
        int contador = 0;
        double precioRef = 0;

        for (int i = 0; i < precios.length; i++) {

            precioRef += precios[i]*unidades[i];
            if (precioRef >= limite) {
                contador ++;
            }

        }

        return contador;

    }

}
