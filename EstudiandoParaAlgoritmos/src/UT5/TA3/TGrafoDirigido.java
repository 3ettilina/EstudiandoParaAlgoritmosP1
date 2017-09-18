package UT5.TA3;

import UT5.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Ernesto
 */
public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino.
     * En caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean inv�lidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     */
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las
     * etiquetas pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea
     * valida, se deben cumplir los siguientes casos: 1) Las etiquetas pasadas
     * por parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen()!= null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }
 
    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }
 public boolean insertarVertice(TVertice vertice) {
     Comparable unaEtiqueta = vertice.getEtiqueta();
     if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

      
    
    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }
    
 

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Double[][] A = this.floyd();
        Double excentricidad = new Double(0);
        Set<Comparable> etiquetasVertices = vertices.keySet();
        Object[] vertice =  etiquetasVertices.toArray();
        Comparable etiquetaCentro = (Comparable) vertice[0];
        Double menor = (Double) this.obtenerExcentricidad((Comparable) vertice[0]);
        for (int columna = 1; columna < A.length; columna++) {
            excentricidad = (Double) this.obtenerExcentricidad((Comparable) vertice[columna]);
            if (menor > excentricidad) {
                menor = excentricidad;
                etiquetaCentro = (Comparable) vertice[columna];
            }
        }

        return etiquetaCentro;
        }

    @Override
    public Double[][] floyd() {
       Double[][] matrizA = UtilGrafos.obtenerMatrizCostos(vertices);
       for (int k=0; k<vertices.size();k++){
           for (int i=0;i<vertices.size();i++){
               for (int j=0;j<vertices.size();j++){
                   if  ((matrizA[i][k] + matrizA[k][j] < matrizA[i][j]) && (matrizA[i][j] != -1d)){
                       matrizA[i][j] = matrizA[i][k] + matrizA[k][j];
                   }
               }
           }
       }
       return matrizA;
    }

   @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] A = this.floyd();
        Double mayor = new Double(0);
        int columna = UtilGrafos.posicionVertice(vertices, etiquetaVertice);
        for (int fila = 0; fila < A.length; fila++) {
            if (fila != columna && A[fila][columna] != Double.MAX_VALUE) {

                if (A[fila][columna].compareTo(mayor) > 0) {
                    mayor = A[fila][columna];
                }

            }
        }

        if (mayor == 0.0) {
            return "INF";
        } else {
            return mayor;
        }
    }

    @Override
    public boolean[][] warshall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public Collection<Comparable> bpf() {
        Collection<Comparable> visitados = new LinkedList <Comparable>();
        Collection<TVertice> vertices1 = this.vertices.values();
        for (TVertice vertice : vertices1 ) {
            if (vertice != null && vertice.getVisitado() == false ){
                vertice.bpf(visitados);
            }
        }
        return visitados;
    }
   
   @Override
   public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        TVertice verticeOrigen = this.buscarVertice(etiquetaOrigen);
        Collection<Comparable> visitados = new LinkedList <Comparable>();
        if (verticeOrigen != null){
            verticeOrigen.bpf(visitados);
        }
        for (Comparable etiqueta : visitados){
            System.out.println(etiqueta+" - ");
        }
        return visitados;
    }

    @Override
    public boolean contieneCiclos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean insertarArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, Comparable costo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Collection<Comparable> bpf(TVertice vertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void desvisitarVertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice v = this.buscarVertice(etiquetaOrigen);
        TCamino caminoPrevio = new TCamino(v);
        TCaminos todosLosCaminos = new TCaminos();
        return v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
    }
    
    
    @Override
    public boolean tieneCiclo(){
        
        if(vertices.isEmpty()){
            return false;
        }
        else{
            for (TVertice vertice : vertices.values()) {
                TCamino camino = new TCamino(vertice);
                if (!vertice.getVisitado() == false){
                    if(vertice.tieneCiclo(camino)){
                        return true;
                        
                    }
                }
                            
    
            }
            return false;  
        }
    }

    
}
