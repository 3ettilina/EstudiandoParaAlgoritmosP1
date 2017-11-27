package Parcial3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION_DIRECTA = 0;
    public static final int METODO_CLASIFICACION_SHELL = 1;
    public static final int METODO_CLASIFICACION_BURBUJA = 2;
    public static final int METODO_CLASIFICACION_QUICKSORT = 3;
    public static final int METODO_CLASIFICACION_HEAPSORT = 4;
    public static final int METODO_CLASIFICACION_SELECCION_DIRECTA = 5;
    public static final int METODO_CLASIFICACION_ARRAYS_SORT = 6;
    public static final int METODO_CLASIFICACION_PARALLEL_SORT = 7;
    public static final int METODO_CLASIFICACION_CUENTAS_POR_DISTRIBUCION = 8;
    public static final int METODO_CLASIFICACION_BUCKETSORT = 9;

    /**
     * Punto de entrada al clasificador
     *
     * @param datosParaClasificar
     * @param metodoClasificacion
     * @param cascara
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION_DIRECTA:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorInsercion(datosParaClasificar);
                }
            case METODO_CLASIFICACION_SHELL:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorShell(datosParaClasificar);
                }
            case METODO_CLASIFICACION_BURBUJA:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorBurbuja(datosParaClasificar);
                }
            case METODO_CLASIFICACION_QUICKSORT:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorQuickSort(datosParaClasificar);
                }
            case METODO_CLASIFICACION_HEAPSORT:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorHeapsort(datosParaClasificar);
                }
            case METODO_CLASIFICACION_SELECCION_DIRECTA:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorSeleccionDirecta(datosParaClasificar);
                }
            case METODO_CLASIFICACION_ARRAYS_SORT:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorArraysSort(datosParaClasificar);
                }
            case METODO_CLASIFICACION_PARALLEL_SORT:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorParallelSort(datosParaClasificar);
                }
            case METODO_CLASIFICACION_CUENTAS_POR_DISTRIBUCION:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorCuentasPorDistribucion(datosParaClasificar);
                }
            case METODO_CLASIFICACION_BUCKETSORT:
                if (cascara) {
                    return usarCascara(datosParaClasificar);
                } else {
                    return ordenarPorBucketSort(datosParaClasificar);
                }
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    public int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j
                                + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                            j = j - inc;
                        } else {
                            j = -1;
                        }

                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    public int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    public int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        //  datosParaClasificar = null;
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    public boolean estaOrdenado(int[] vector) {
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] > vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    public int[] quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;
        int posicionPivote = encuentraPivote1(izquierda, derecha);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda < derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }
                while ((entrada[derecha] > pivote) && (derecha > i)) {
                    derecha--;
                }
                if (izquierda <= derecha) {
                    intercambiar(entrada, izquierda, derecha);
                    izquierda++;
                    derecha--;
                }
            }
            if (i < derecha) {
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
        return entrada;
    }

    public int encuentraPivote(int izq, int der) {
        if (izq == der) {
            return -1;
        }
        return ((izq + der) / 2);
    }

    public int encuentraPivote1(int izq, int der) {
        Random rand = new Random();
        int randomNum = rand.nextInt((der - izq)) + izq;
        return randomNum;
    }

    public int encuentraPivote2(int izq, int der) {
        if (izq == der) {
            return -1;
        }
        return ((izq + der) / 2);
    }

    public int[] usarCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    public int[] ordenarPorHeapsort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    public void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] > datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                    }
                    r = ultimo;
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] > datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r + 1;
                    } else {
                        posicionIntercambio = 2 * r;
                    }
                    if (datosParaClasificar[r] > datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    public int[] ordenarPorSeleccionDirecta(int[] datosParaClasificar) {
        for (int i = 0; i < datosParaClasificar.length - 1; i++) {
            int indiceDelMenor = i;
            int claveMenor = datosParaClasificar[i];
            for (int j = i + 1; j < datosParaClasificar.length; j++) {
                if (datosParaClasificar[j] < claveMenor) {
                    indiceDelMenor = j;
                    claveMenor = datosParaClasificar[j];
                }
            }
            intercambiar(datosParaClasificar, i, indiceDelMenor);
        }
        return datosParaClasificar;
    }

    public int[] ordenarPorArraysSort(int[] datosParaClasificar) {
        Arrays.sort(datosParaClasificar);
        return datosParaClasificar;
    }

    public int[] ordenarPorParallelSort(int[] datosParaClasificar) {
        Arrays.parallelSort(datosParaClasificar);
        return datosParaClasificar;
    }

    public int[] ordenarPorCuentasPorDistribucion(int[] array) {
        int[] aux = new int[array.length];

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        int[] counts = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }

        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            aux[counts[array[i] - min]--] = array[i];
        }

        return aux;
    }

    public int[] ordenarPorBucketSort(int[] array) {
        int cantidadUrnas = 10;

        if (array.length == 0) {
            return array;
        }
        // Determinamos el valor máximo y el valor mínimo
        Integer minValue = array[0];
        Integer maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // Inicializamos las urnas
        int bucketCount = (maxValue - minValue) / cantidadUrnas + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribuímos los valores de los arrays 
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / cantidadUrnas).add(array[i]);
        }

        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            Arrays.sort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                array[currentIndex++] = bucketArray[j];
            }
        }
        return array;
    }
}
