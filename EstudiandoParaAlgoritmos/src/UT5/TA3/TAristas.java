package UT5.TA3;

import UT5.*;
import java.util.Collection;

public class TAristas {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    private Collection<TArista> aristas; // = new {IMPLEMENTACION DE COLLECTION DESEADA}
    
    
    /**
     * Busca dentro de la lista de aristas una arista que conecte a 
     * etOrigen con etDestino
     * @param etOrigen
     * @param etDestino
     * @return 
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        //TODO: Implementar busqueda de aristas sobre coleccion	
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de
     * VerticesU con cualquier otro de VerticesV y cuyo costo sea el minimo
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return 
     */
    public TArista buscarMin(Collection<TArista> VerticesU, Collection<TArista> VerticesV) {
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;

        //TODO: ---------COMPLETAR ALGORITMO--------
        // para todo u en Vertices U
        // para todo v en Vertices V
        // tA =buscar (u, v)
        // si tA <> null y tA.costo < costoMin entonces
        // tAMin = tA y costoMin = tA.costo
        // fin para todo v
        // fin para todo u
        // devolver tAMin
        return null;
    }

    public String imprimirEtiquetas() {
        if (aristas.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        return salida.toString();

    }

    /**
     * Inserta la arista al final de la coleccion de aristas
     * @param tArista 
     */
    public void insertarAlFinal(TArista tArista) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
