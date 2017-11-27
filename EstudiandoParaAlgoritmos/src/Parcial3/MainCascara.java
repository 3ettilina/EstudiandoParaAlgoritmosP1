/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial3;

/**
 *
 * @author Jotacé
 */
public class MainCascara {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        INSERCION_DIRECTA = 0;
//        SHELLSORT = 1;
//        BURBUJA = 2;
//        QUICK_SORT = 3;
//        HEAPSORT = 4;
//        SELECCION_DIRECTA = 5;
//        ARRAYS_SORT = 6;
//        PARALLEL_SORT = 7;
//        CUENTAS_POR_DISTRIBUCION = 8;
//        BUCKETSORT = 9; //Solo funciona de 0 a 999, en caso de precisar ordenar valores de 4 cifras hay que modificar la variable cantidadUrnas
        int[] metodosDeClasificacion = {9};
        int[] tamañosArrays = {1000, 2000, 5000, 10000, 15000, 20000, 50000, 75000, 100000, 125000};
//        int[] tamañosArrays = {999};

        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        TClasificador clasificador = new TClasificador();

        for (int metodoDeClasificacion : metodosDeClasificacion) {
            for (int tamañoArray : tamañosArrays) {
                int[] vectorOriginalAscendente = gdg.generarDatosAscendentes(tamañoArray);
                int[] vectorOriginalDescendente = gdg.generarDatosDescendentes(tamañoArray);
                int[] vectorOriginalAleatorio = gdg.generarDatosAleatorios(tamañoArray);

                System.out.println("************************************************INICIO**************************************************************************");
                Long tiempoMedioAlgoritmoBaseAscendente = calculoDeTiempos(vectorOriginalAscendente, clasificador, metodoDeClasificacion, false);
                Long tiempoMedioCascaraAscendente = calculoDeTiempos(vectorOriginalAscendente, clasificador, metodoDeClasificacion, true);
                Long tiempoMedioAlgoritmoAscendente = tiempoMedioAlgoritmoBaseAscendente - tiempoMedioCascaraAscendente;
                System.out.println("Tiempo medio algoritmo: " + tiempoMedioAlgoritmoAscendente + " Tamaño array: " + tamañoArray + " Método de clasificación: " + metodoDeClasificacion + " Orden ASCENDENTE");

                Long tiempoMedioAlgoritmoBaseDescendente = calculoDeTiempos(vectorOriginalDescendente, clasificador, metodoDeClasificacion, false);
                Long tiempoMedioCascaraDescendente = calculoDeTiempos(vectorOriginalDescendente, clasificador, metodoDeClasificacion, true);
                Long tiempoMedioAlgoritmoDescendente = tiempoMedioAlgoritmoBaseDescendente - tiempoMedioCascaraDescendente;
                System.out.println("Tiempo medio algoritmo: " + tiempoMedioAlgoritmoDescendente + " Tamaño array: " + tamañoArray + " Método de clasificación: " + metodoDeClasificacion + " Orden DESCENDENTE");

                Long tiempoMedioAlgoritmoBaseAleatorio = calculoDeTiempos(vectorOriginalAleatorio, clasificador, metodoDeClasificacion, false);
                Long tiempoMedioCascaraAleatorio = calculoDeTiempos(vectorOriginalAleatorio, clasificador, metodoDeClasificacion, true);
                Long tiempoMedioAlgoritmoAleatorio = tiempoMedioAlgoritmoBaseAleatorio - tiempoMedioCascaraAleatorio;
                System.out.println("Tiempo medio algoritmo: " + tiempoMedioAlgoritmoAleatorio + " Tamaño array: " + tamañoArray + " Método de clasificación: " + metodoDeClasificacion + " Orden ALEATORIO");
                System.out.println("**********************************************FIN*********************************************************************************");
            }
        }
    }

    private static Long calculoDeTiempos(int[] vectorOriginal, TClasificador clasificador, int numeroAlgoritmoClasificador, boolean cascara) {
        Long t1 = System.nanoTime();
        Long total = 0L;
        Long tiempoResolucion = 1000000000L;
        int cantidadLlamadas = 0;

        while (total < tiempoResolucion) {
            cantidadLlamadas += 1;
            int[] datosCopia = copiarVector(vectorOriginal);
            clasificador.clasificar(datosCopia, numeroAlgoritmoClasificador, cascara);
            Long t2 = System.nanoTime();
            total = t2 - t1;
        }

        return total / cantidadLlamadas;
    }

    public static int[] copiarVector(int[] aOrigen) {
        int[] aDestino = new int[aOrigen.length];
        for (int x = 0; x < aOrigen.length; x++) {
            aDestino[x] = aOrigen[x];
        }
        return aDestino;
    }
}
