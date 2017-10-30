package UT5_TA2;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-

    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

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
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
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
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
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
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
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

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Double[][] floyd() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        for (int k = 0; k < matrizCostos.length; k++) {
            for (int i = 0; i < matrizCostos.length; i++) {
                for (int j = 0; j < matrizCostos.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        double costoIK = matrizCostos[i][k];
                        double costoKJ = matrizCostos[k][j];
                        double costoIJ = matrizCostos[i][j];
                        if (!((costoIK == Double.MAX_VALUE) || (costoKJ == Double.MAX_VALUE))) {
                            matrizCostos[i][j] = Math.min(costoIJ, (costoIK + costoKJ));
                        }
                    }
                }
            }
        }
        return matrizCostos;
    }

    public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        boolean[][] matrizWarshall = new boolean[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                matrizWarshall[i][j] = false;

                if (i != j && matrizCostos[i][j] != Integer.MAX_VALUE) {
                    matrizWarshall[i][j] = true;
                }
            }
        }
        for (int k = 0; k < matrizWarshall.length; k++) {
            for (int i = 0; i < matrizWarshall.length; i++) {
                for (int j = 0; j < matrizWarshall.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        if (!matrizWarshall[i][j]) {
                            matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                        }
                    }
                }
            }
        }
        return matrizWarshall;
    }

    public Double obtenerExcentricidad(Comparable etiquetaVertice) {
        Double resultado = 0d;
        Double matrizCaminosMinimos[][] = floyd();
        TVertice origen = getVertices().get(etiquetaVertice);
        //para conocer el �ndice en la matriz del vertice seleccionado como origen
        int indiceOrigen = 0;
        for (Comparable auxiliarContar : getVertices().keySet()) {
            if (auxiliarContar == origen.getEtiqueta()) {
                break;
            }
            indiceOrigen++;
        }

        for (int x = 0; x < matrizCaminosMinimos.length; x++) {
            Double camino = matrizCaminosMinimos[x][indiceOrigen];
            if (camino == null) {
                camino = Double.MAX_VALUE;
            }
            if (x != indiceOrigen && resultado.compareTo(camino) < 0) {
                resultado = camino;
            }
        }
        return resultado;
    }

    /**
     * @return Etiqueta del centro del grafo
     */
    public Comparable centroDelGrafo() {
        Double menorExcentricidad = Double.MAX_VALUE;
        Comparable etiquetaMenorEx = null;
        for (Comparable etiqueta : getVertices().keySet()) {
            Double excentricidadVert = obtenerExcentricidad(etiqueta);
            if (menorExcentricidad.compareTo(excentricidadVert) > 0) {
                menorExcentricidad = excentricidadVert;
                etiquetaMenorEx = etiqueta;
            }
        }
        return etiquetaMenorEx;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    
 

    public Collection<Comparable> bpf(TVertice vertice) {
        Collection<Comparable> visitados = new LinkedList<Comparable>();
        if(vertice != null)
        {
           vertice.bpf(visitados);
          this.desvisitarVertices();
        }        
        return visitados;
    }

    public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        TVertice vertice = this.buscarVertice(etiquetaOrigen);
        Collection<Comparable> visitados = new LinkedList<Comparable>();
        if(vertice != null)
        {
           vertice.bpf(visitados);
          this.desvisitarVertices();
        }
        return visitados;
    }


    public Collection<Comparable> bpf() {
        Collection<Comparable> visitados = new LinkedList<Comparable>();
        for (Comparable etiqueta: this.vertices.keySet())
        {
            for (Comparable unaEtiqueta: this.bpf(etiqueta))
            {
                visitados.add(unaEtiqueta);

            }
            visitados.add("*");            
        }
        this.desvisitarVertices();
        return visitados;
    }
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

      @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice v = this.buscarVertice(etiquetaOrigen);
        TCaminos todosLosCaminos = new TCaminos();
        if(v!=null){
                    TCamino caminoPrevio = new TCamino(v);
                    v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
                    return todosLosCaminos;
        }
        return null;
    }    
        public boolean tieneCiclo(){
            LinkedList <TCamino> listaCiclos=new LinkedList();
        if(vertices.isEmpty()){
            return false;
        }
        else{
            for (TVertice vertice : vertices.values()) {
                TCamino camino = new TCamino(vertice);
                if (!vertice.getVisitado() == false){
                    if(vertice.tieneCiclo(camino)){
                        listaCiclos.add(camino);
                        return true;
                    }
                }
            }
            return false;  
        }
    }
            public Map<Comparable,TVertice> copiaVertices(){
        Map<Comparable,TVertice> resultado = new HashMap();
        for(Map.Entry<Comparable,TVertice> vertice : this.getVertices().entrySet()){
            resultado.put(vertice.getKey(), vertice.getValue());
        }
        return resultado;
    }
}
     
     

