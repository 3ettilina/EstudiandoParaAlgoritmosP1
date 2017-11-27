/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package parcial2.grafonodirigido;
//
///**
// *
// * @author Jotacé
// */
//public class MainCascara {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        int INSERCION_DIRECTA = 1;
//        int SHELLSORT = 2;
//        int BURBUJA = 3;
//        int QUICK_SORT = 4;
//
//        //Aleatorios300
////        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
////        TClasificador clasificador = new TClasificador();
//        int[] vectorOriginal = gdg.generarDatosAscendentes(300);
//
//        Long tiempoMedioAlgoritmoBase = calculoDeTiempos(vectorOriginal, clasificador, INSERCION_DIRECTA, false);
//        Long tiempoMedioCascara = calculoDeTiempos(vectorOriginal, clasificador, INSERCION_DIRECTA, true);
//        Long tiempoMedioAlgoritmo = tiempoMedioAlgoritmoBase - tiempoMedioCascara;
//
//        System.out.println("Tiempo medio algoritmo " + tiempoMedioAlgoritmo);
//    }
//
//    private static Long calculoDeTiempos(int[] vectorOriginal, TClasificador clasificador, int numeroAlgoritmoClasificador, boolean cascara) {
//        Long t1 = System.nanoTime();
//        Long total = 0L;
//        Long tiempoResolucion = 1000000000L;
//        int cantidadLlamadas = 0;
//
//        while (total < tiempoResolucion) {
//            cantidadLlamadas += 1;
//            int[] datosCopia = copiarVector(vectorOriginal);
//            clasificador.clasificar(datosCopia, numeroAlgoritmoClasificador, cascara);
//            Long t2 = System.nanoTime();
//            total = t2 - t1;
//        }
//
//        return total / cantidadLlamadas;
//    }
//
//    public static int[] copiarVector(int[] aOrigen) {
//        int[] aDestino = new int[aOrigen.length];
//        for (int x = 0; x < aOrigen.length; x++) {
//            aDestino[x] = aOrigen[x];
//        }
//        return aDestino;
//    }
//}
