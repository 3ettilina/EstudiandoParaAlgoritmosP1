package parcial2.implementaciones;

public class PruebaGrafo {

    public static void main(String[] args) {

//        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/UT04/TA03/ejemploClaseAeropuertos.txt", "src/UT04/TA03/ejemploClaseConexiones.txt",
//                false, TGrafoDirigido.class);
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/UT04/TA03/aeropuertos_1.txt", "src/UT04/TA03/conexiones_1.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        System.out.println(gd.centroDelGrafo());
        UtilGrafos.imprimirMatrizMejorado(gd.getMatrizPredecesoresGrafo(), gd.getVertices(), "Matriz Predecesores");
        gd.warshall();
        UtilGrafos.imprimirMatrizMejoradoSoportaBoolean(gd.getMatrizWarshall(), gd.getVertices(), "Matriz Warshall");

        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println(gd.existeCamino("Montevideo", "Buenos_Aires"));
        System.out.println(gd.existeCamino("Montevideo", "Porto_Alegre"));
        String[] paises = {"Asuncion", "Buenos_Aires", "Curitiba", "Montevideo", "Porto_Alegre", "Rio_de_Janeiro", "San_Pablo", "Santos"};

        for (int i = 0; i < paises.length; i++) {
            for (int j = 0; j < paises.length; j++) {
                if (i != j) {
                    System.out.println("COMIENZO");
                    gd.obtenerMejorCamino(paises[i], paises[j]);
                    System.out.println("FIN");
                }
            }
        }
        System.out.println("Algo");
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
    }
}
