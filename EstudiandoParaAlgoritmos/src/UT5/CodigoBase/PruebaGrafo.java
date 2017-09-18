package UT5.CodigoBase;


import java.util.Collection;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/UT5/CodigoBase/ar.txt",
                "src/UT5/CodigoBase/co.txt",
                false, TGrafoNoDirigido.class);
 

//Prueba que se armó bien la matriz
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, gnd.getVertices(),"Matriz Costos");
            
        System.out.println("Es conexo: "+gnd.esConexo("a"));
//Prueba Warshall
        System.out.println("WARSHALL:");
        boolean[][]matrizCaminos=gnd.warshall();
        UtilGrafos.imprimirMatriz(matrizCaminos, gnd.getVertices());
        System.out.println("");
       
        TGrafoNoDirigido grafo1 = gnd.Prim();
        matrizCostos = UtilGrafos.obtenerMatrizCostos(grafo1.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafo1.getVertices(), "Prim");
       
        
    }
}
