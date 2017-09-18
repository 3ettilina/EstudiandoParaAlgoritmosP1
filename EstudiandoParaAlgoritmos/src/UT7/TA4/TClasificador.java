package UT7.TA4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author nacho
 */

    public class TClasificador {
        public static final int METODO_CLASIFICACION_INSERCION = 1;
        public static final int METODO_CLASIFICACION_SHELL = 2;
        public static final int METODO_CLASIFICACION_BURBUJA = 3;
        public static final int METODO_CLASIFICACION_QUICKSORT=4;
        public static final int METODO_CLASIFICACION_HEAPSORT=5;
        public static final int METODO_CLASIFICACION_SELECCION=6;
        


        public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion,boolean var) {
                if (var){
                switch (metodoClasificacion) {
                case METODO_CLASIFICACION_INSERCION:
                        return ordenarPorInsercion(datosParaClasificar);
                case METODO_CLASIFICACION_SHELL:
                        return ordenarPorShell(datosParaClasificar);
                case METODO_CLASIFICACION_BURBUJA:
                        return ordenarPorBurbuja(datosParaClasificar);
                case METODO_CLASIFICACION_QUICKSORT:
                        return ordenarPorQuickSort(datosParaClasificar);
                case METODO_CLASIFICACION_HEAPSORT:
                        return ordenarPorHeapSort(datosParaClasificar);
                case METODO_CLASIFICACION_SELECCION:
                        return ordenarPorSeleccion(datosParaClasificar);
                default:
                        System.err.println("Este codigo no deberia haberse ejecutado");
                        break;
                    }
                }
                return datosParaClasificar;
                
        }
        private int[] ordenarPorShell(int[] datosParaClasificar) {
                int j;
                int[] incrementos = new int[] { 5, 3, 1 };

                for (int inc : incrementos) {
                        if (inc < (datosParaClasificar.length / 2)) {
                                for (int i = inc; i < datosParaClasificar.length; i++) {
                                        int aux = datosParaClasificar[i];
                                        j = i;
                                        while (j >= inc && aux < datosParaClasificar[j - inc]) {
                                            //intercambiar(datosParaClasificar, j, j + inc);
                                            datosParaClasificar[j] = datosParaClasificar[j - inc];
                                            j -= inc;                
                                        }
                                    datosParaClasificar[j] = aux;
                                }
                        }
                }
                return datosParaClasificar;
        }
        protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
                if (datosParaClasificar != null) {
                        //i = 0 lo modificamos por i = 1 ya que este algoritmo inicia en el segundo elemento
                        for (int i = 1; i < datosParaClasificar.length; i++) {
                                int j = i - 1;
                                // esta al reves datosParaClasificar[j + 1] > datosParaClasificar[j] por datosParaClasificar[j] > datosParaClasificar[j + 1]
                                while ((j >= 0) && (datosParaClasificar[j] > datosParaClasificar[j + 1])) {
                                        intercambiar(datosParaClasificar, j, j + 1);
                                        j--;
                                }
                        }
                        return datosParaClasificar;
                }
                return null;
        }

        private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
            // deshabilito la linea 88 no deberia estar
                //datosParaClasificar = null;
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

        protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
                OtroOrdenarPorQuickSort(datosParaClasificar, 0, datosParaClasificar.length - 1);
                return datosParaClasificar;
        }

        private void quicksort(int[] entrada, int i, int j) {
                int izquierda = i;
                int derecha = j;

                int posicionPivote = encuentraPivoteMediana(izquierda,derecha,entrada); 
                if (posicionPivote >= 0){
                        int pivote = entrada[posicionPivote];  
                        while (izquierda <= derecha) {
                                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                                        izquierda++; 
                                }

                                while ((pivote <= entrada[derecha]) && (derecha > i)) {
                                        derecha--; 
                                }

                                if (izquierda <= derecha) {
                                        intercambiar(entrada, derecha, izquierda); 
                                        izquierda++;
                                        derecha--;
                                }
                        }

                        if (i < izquierda-1)
                                quicksort(entrada, i, izquierda-1);
                        if (izquierda < j)
                            quicksort(entrada, izquierda, j); 

                }
        }



        
        
        
        private void intercambiar(int[] entrada, int derecha, int izquierda) {
             int aux = entrada[izquierda];
             entrada[izquierda] = entrada[derecha];
             entrada[derecha] = aux;             
        }


         
        public int encuentraPivoteMediana(int i, int j, int [] vector)
        {
            return vector[(i + j)/2];
        }
        
        private int particion(int[] vector, int left, int right){
        int i = left, j = right;
        int aux;
        int pivote = encuentraPivoteMediana(i, j, vector);
        //int pivote = vector[(left + right) / 2];
        while (i <= j) {
            while (vector[i] < pivote) i++;
            while (vector[j] > pivote) j--;
            if (i <= j) {
                aux = vector[i];
                vector[i] = vector[j];
                vector[j] = aux;
                i++; j--;
            }
      }
      return i;
    }
 
        private int[] OtroOrdenarPorQuickSort(int[] arr, int left, int right) {
            int index = particion(arr, left, right);
            if (left < index-1) OtroOrdenarPorQuickSort(arr, left, index - 1);
            if (index < right ) OtroOrdenarPorQuickSort(arr, index, right);
            return arr;
        }
         

        private int encuentraPivote(int izquierda, int derecha, int[] entrada) {
           for (int i = izquierda; i< derecha; i++){
               if (entrada[i] > entrada[i+1] ){
                   return i;
               }
               if (entrada[i] < entrada[i+1]){
                   return i+1;
               }
           }
           return -1;         
          
    }

        
    
    
    public int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] > datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                        //r = 2 ;
                        //} else {
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
    
    	protected int[] ordenarPorSeleccion(int[] datosParaClasificar) {
		for (int i = 0; i < datosParaClasificar.length - 1; i++) {
			int indiceMenor = i;
			int claveMenor = datosParaClasificar[i];
			for (int j = i + 1; j < datosParaClasificar.length; j++) {
				if (datosParaClasificar[j] < claveMenor) {
					indiceMenor = j;
					claveMenor = datosParaClasificar[j];
				}
			}
			intercambiar(datosParaClasificar, i, indiceMenor);
		}
		return datosParaClasificar;
	}


        
    public boolean estaOrdenado(int[] vector){
        boolean res=true;
        //recorro el vector
        for(int i=0;i<vector.length-1;i++){
            //si algun elemento es mayor al siguiente entonces no esta ordenada, seteo res en false y break.
            if(vector[i]>vector[i+1]){
                res=false;
                break;
            }
        }
        return res;
    }
}