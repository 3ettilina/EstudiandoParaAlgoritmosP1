package Parcial3;

import java.util.Random;

public class GeneradorDatosGenericos {

    public int[] generarDatosAleatorios(int TAMANIO_MAX) {
        Random rnd = new Random();
        int[] datosGenerados = new int[TAMANIO_MAX];
        boolean[] datosUtilizados = new boolean[TAMANIO_MAX];
        for (int i = 0; i < datosGenerados.length; i++) {
            int j = rnd.nextInt(TAMANIO_MAX);
            while (datosUtilizados[j]) {
                j = (j + 1) % TAMANIO_MAX;
            }
            datosGenerados[j] = i;
            datosUtilizados[j] = true;
        }
        return datosGenerados;
//        int[] unArray = {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,8,8,8,8,8,8,8,8,8,8,7,7,7,6,6,6,6,6,5,5,5,5,4,4,4,4,4,3,3,3,3,2,2,2,2,7,7,7,7,7,7,8,8,8,8,8,8,9,9,9,9,9,9,1,1,1,1,1,1,11,11,11,11,11,11,11};
//        int[] unArray = {256,458,365,298,43,648,778,621,655,19,124,847};
//        int[] unArray = {256, 458, 365, 298, 43, 648};
//        return unArray;
    }

    public int[] generarDatosAscendentes(int TAMANIO_MAX) {
        int[] copiaAscendente = new int[TAMANIO_MAX];
        for (int i = 0; i < TAMANIO_MAX; i++) {
            copiaAscendente[i] = i;
        }
        return copiaAscendente;
    }

    public int[] generarDatosDescendentes(int TAMANIO_MAX) {
        int[] copiaDescendente = new int[TAMANIO_MAX];
        for (int i = 0; i < TAMANIO_MAX; i++) {
            copiaDescendente[i] = TAMANIO_MAX - i;
        }
        return copiaDescendente;
    }

}
