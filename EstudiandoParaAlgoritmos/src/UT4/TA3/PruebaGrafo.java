package UT4.TA3;


import java.util.Collection;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/UT4/TA3/aeropuertos.txt","src/UT4/TA3/conexiones.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        Comparable[] etiquetas = gd.getVertices().keySet().toArray(new Comparable[gd.getVertices().keySet().size()]);
        
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
        
        String origen = "Montevideo";
        String destino = "Santos";
        
        //int i = gd.getVertices().get(origen);
        
        
        //System.out.println(mfloyd[]);
       
       
    }
}
