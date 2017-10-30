package UT5_TA2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        TArista tempArista;
        for (TArista a : this) {
            if((a.etiquetaOrigen.equals(etOrigen) && a.etiquetaDestino.equals(etDestino))   || 
                    a.etiquetaOrigen.equals(etDestino) && a.etiquetaDestino.equals(etOrigen)){
                return new TArista(etOrigen,etDestino,a.costo);
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<TVertice> VerticesU, Collection<TVertice> VerticesV) {
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;
                
        for(TVertice u : VerticesU){
            for(TVertice v : VerticesV){
                    tempArista = buscar(u.getEtiqueta(), v.getEtiqueta());               
                    if(tempArista != null && tempArista.getCosto() < costoMin){
                        tAMin = tempArista;
                        costoMin = tAMin.getCosto();
                    }
            }
        }
        
        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for(TArista arista : this){
            salida.append("ORIGEN " + arista.getEtiquetaOrigen() + " - DESTINO - " + arista.getEtiquetaDestino() + " COSTO " + arista.getCosto() + "\n");
        }
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        return salida.toString();

    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

}
