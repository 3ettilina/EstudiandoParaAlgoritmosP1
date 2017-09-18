package UT6.TA2;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
        public static final int METODO_CLASIFICACION_QUICK=4;

	/**
	 * Punto de entrada al clasificador
	 * 
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
		case METODO_CLASIFICACION_INSERCION:
			return ordenarPorInsercion(datosParaClasificar);
		case METODO_CLASIFICACION_SHELL:
			return ordenarPorShell(datosParaClasificar);
		case METODO_CLASIFICACION_BURBUJA:
			return ordenarPorBurbuja(datosParaClasificar);
                case METODO_CLASIFICACION_QUICK:
                        return ordenarPorQuickSort(datosParaClasificar,0,datosParaClasificar.length-1);
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


	/**
	 * @param datosParaClasificar
	 * @return
	 */
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
        
    public int encuentraPivote(int i, int j, int [] vector)
    {
        return vector[(i + j)/2];
    }
        
    int particion(int[] vector, int left, int right){
        int i = left, j = right;
        int aux;
        int pivote = encuentraPivote(i, j, vector);
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
 
    private int[] ordenarPorQuickSort(int[] arr, int left, int right) {
        int index = particion(arr, left, right);
        if (left < index-1) ordenarPorQuickSort(arr, left, index - 1);
        if (index < right ) ordenarPorQuickSort(arr, index, right);
        return arr;
    }

	
public static void main(String args[]) {
            
            
            //Ejercicio 2 Parte 1
            int [] vector = new int[5];
            vector[0] = 10;
            vector[1] = 1;
            vector[2] = 4;
            vector[3] = 3;
            vector[4] = 9;
            
            //Ejercicio 2 Parte 2
            TClasificador clasif = new TClasificador();
            int metodo=METODO_CLASIFICACION_QUICK;
            System.out.println("Vector 5 elementos:\n ");
            
           
            GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
            int[] vectorAleatorio = gdg.generarDatosAleatorios();
            int[] vectorAscendente = gdg.generarDatosAscendentes();
            int[] vectorDescendente = gdg.generarDatosDescendentes();
           
           System.out.println("QuickSort Aleatorio");
           
           long inicioAleatorio = System.nanoTime();
           int[] resInsercion = clasif.clasificar(vectorAleatorio, metodo);
           long finAleatorio = System.nanoTime();
           System.out.println("El tiempo es: " + (finAleatorio - inicioAleatorio));
           System.out.println("--------------------------------------------");
          
            
           System.out.println("QuickSort Ascendente");
           
           long inicioAscendente = System.nanoTime();
           int[] resInsercionn = clasif.clasificar(vectorAscendente, metodo);
           long finAscendente = System.nanoTime();
           System.out.println("El tiempo es: " + (finAscendente - inicioAscendente));
           System.out.println("--------------------------------------------");
      
            
           System.out.println("QuickSort Descendente");
    
           long inicioDescendente = System.nanoTime();
           int[] resInsercionDes = clasif.clasificar(vectorDescendente, metodo);
           long finDescendente = System.nanoTime();
           System.out.println("El tiempo es: " + (finDescendente - inicioDescendente));
           System.out.println("--------------------------------------------");
   
           
           
           /*
            
            for (int i = 0; i < resInsercion.length; i++) {
		System.out.print(resInsercion[i] + " ");
            }
            
            //Ejercicio 2 Parte 5
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(resInsercion)+"\n");
                    
            //Ejercicio 2 Parte 6
           
            System.out.println("\nVector Aleatorio:\n ");
            for (int i = 0; i < vectorAleatorio.length; i++) { System.out.print(vectorAleatorio[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(vectorAleatorio)+"\n");
            
            long inicioAleatorio = System.nanoTime();
            int[] resAleatorio = clasif.clasificar(vectorAleatorio, metodo);
            long finAleatorio = System.nanoTime();
            for (int i = 0; i < resAleatorio.length; i++) { System.out.print(resAleatorio[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(resAleatorio)+"\n");
            
            System.out.println("\nVector Ascendente:\n ");
            for (int i = 0; i < vectorAscendente.length; i++) { System.out.print(vectorAscendente[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(vectorAscendente)+"\n");
            long inicioAscendente = System.nanoTime();
            int[] resAscendente = clasif.clasificar(vectorAscendente, metodo);
            long finAscendente = System.nanoTime();
            for (int i = 0; i < resAscendente.length; i++) { System.out.print(resAscendente[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(resAscendente)+"\n");
            
            System.out.println("\nVector Descendente:\n ");
            for (int i = 0; i < vectorDescendente.length; i++) { System.out.print(vectorDescendente[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(vectorDescendente)+"\n");
            long inicioDescendente = System.nanoTime();
            int[] resDescendente = clasif.clasificar(vectorDescendente, metodo);
            long finDescendente = System.nanoTime();
            for (int i = 0; i < resDescendente.length; i++) { System.out.print(resDescendente[i] + " "); }
            System.out.println("\n-¿Esta ordenado? "+clasif.estaOrdenado(resDescendente)+"\n");
            
            System.out.println("Tiempo Aleatorio");
            System.out.println(finAleatorio - inicioAleatorio);
            System.out.println("Tiempo Ascendente");
            System.out.println(finAscendente - inicioAscendente);
            System.out.println("Tiempo Descendente");
            System.out.println(finDescendente - inicioDescendente);
            //Ejercicio 2 Parte 8
            //ShellSort*/
            
	}

}
