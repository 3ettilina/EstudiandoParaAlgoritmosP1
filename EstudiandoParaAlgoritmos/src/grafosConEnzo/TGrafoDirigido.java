package grafosConEnzo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-
    private Double[][] matrizPredecesoresGrafo;
    private Boolean[][] matrizWarshall;

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
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
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
     * @param nombreVertice
     * @return
     */
    @Override
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
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
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
    @Override
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
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
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

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Double[][] matrizDeFloyd = floyd();
        Double[] vectorExcentricidades = inicializarVectorExcentricidades(matrizDeFloyd.length);

        for (int i = 0; i < matrizDeFloyd.length; i++) {
            for (int j = 0; j < matrizDeFloyd.length; j++) {
                if (matrizDeFloyd[i][j] > vectorExcentricidades[j]) {
                    vectorExcentricidades[j] = matrizDeFloyd[i][j];
                }
            }
        }
        return (Comparable) vertices.keySet().toArray()[obtenerMinimoValor(vectorExcentricidades)];
    }

    @Override
    public Double[][] floyd() {
        Double[][] matrizFloyd = UtilGrafos.obtenerMatrizCostos(vertices);
        matrizPredecesoresGrafo = inicializarMatriz(matrizFloyd.length);
        for (int i = 0; i < matrizFloyd.length; i++) {
            matrizFloyd[i][i] = 0.0;
        }
        for (int k = 0; k < matrizFloyd.length; k++) {
            for (int i = 0; i < matrizFloyd.length; i++) {
                for (int j = 0; j < matrizFloyd.length; j++) {
                    if ((matrizFloyd[i][k] + matrizFloyd[k][j]) < matrizFloyd[i][j]) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                        matrizPredecesoresGrafo[i][j] = k + 0d;
                    }
                }
            }
        }
        return matrizFloyd;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        //Calculamos la matriz de Floyd
        Double[][] c = this.floyd();
        int columnaVertice = 0;
        int contador = 0;
        for (Comparable key : vertices.keySet()) {
            if (key.equals(etiquetaVertice)) {
                columnaVertice = contador;
                break;
            }
            contador += 1;
        }
        double excentricidad = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i][columnaVertice] > excentricidad) {
                excentricidad = c[i][columnaVertice];
            }
        }
        return excentricidad;
    }

    @Override
    public Boolean[][] warshall() {
        Double[][] costos = UtilGrafos.obtenerMatrizCostos(vertices);
        Boolean[][] w = new Boolean[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (costos[i][j] == Double.MAX_VALUE || costos[i][j] <= 0) {
                    w[i][j] = false;
                } else {
                    w[i][j] = true;
                }
            }
        }
        for (int k = 0; k < w.length; k++) {
            for (int i = 0; i < w.length; i++) {
                for (int j = 0; j < w.length; j++) {
                    if (i == j) {
                        w[i][j] = false;
                    } else if (w[i][j] == false) {
                        w[i][j] = w[i][k] && w[k][j];
                    }
                }
            }
        }
        matrizWarshall = w;
        return w;
    }

    /**
     * Metodo encargado de crear e inicializar un array de double.
     *
     * @param largoMatriz el largo del array.
     * @return el array inicializado.
     */
    private Double[] inicializarVectorExcentricidades(int largoMatriz) {
        //Declaramos e inicializamos el array de excentricidades.
        Double[] vectorExcentricidades = new Double[largoMatriz];
        //Lo llenamos con -1
        for (int i = 0; i < vectorExcentricidades.length; i++) {
            vectorExcentricidades[i] = -1d;
        }
        //Y por ultimo lo devolvemos
        return vectorExcentricidades;
    }

    /**
     * Metodo encargado de obtener el minimo valor de un array.
     *
     * @param vectorExcentricidades el vector sobre el cual se desea obtener el
     * minimo.
     * @return la posicion en la cual se encuentra el minimo valor.
     */
    private int obtenerMinimoValor(Double[] vectorExcentricidades) {
        int posicion = 0;
        Double minimo = vectorExcentricidades[posicion];
        for (int i = 1; i < vectorExcentricidades.length; i++) {
            if (vectorExcentricidades[i] < minimo) {
                minimo = vectorExcentricidades[i];
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Metodo encargado de declarar e inicializar una matriz de un largo
     * determinado y devolverla con un conjunto de valores.
     *
     * @param largoMatriz El largo y el ancho de la matriz.
     * @return La matriz inicializada.
     */
    private Double[][] inicializarMatriz(int largoMatriz) {
        Double[][] matrizInicializada = new Double[largoMatriz][largoMatriz];
        for (int i = 0; i < matrizInicializada.length; i++) {
            for (int j = 0; j < matrizInicializada.length; j++) {
                matrizInicializada[i][j] = -1d;
            }
        }
        return matrizInicializada;
    }

    public boolean existeCamino(Comparable etiquetaVerticeOrigen, Comparable etiquetaVerticeDestino) {
        TVertice verticeOrigen = vertices.get(etiquetaVerticeOrigen);
        TVertice verticeDestino = vertices.get(etiquetaVerticeDestino);

        if (verticeOrigen != null && verticeDestino != null) {
            return matrizWarshall[posicionVerticeHashMap(etiquetaVerticeOrigen)][posicionVerticeHashMap(etiquetaVerticeDestino)];
        }
        return false;
    }

    public int posicionVerticeHashMap(Comparable unaEtiqueta) {
        Object[] conjuntoVertices = vertices.keySet().toArray();
        for (int i = 0; i < conjuntoVertices.length; i++) {
            if (conjuntoVertices[i].equals(unaEtiqueta)) {
                return i;
            }
        }
        return -1;
    }

    public Comparable verticeEnPosicion(Double unaPosicion) {
        int j = unaPosicion.intValue();
        Object[] conjuntoVertices = vertices.keySet().toArray();
        return (Comparable) conjuntoVertices[j];
    }

    public void obtenerMejorCamino(Comparable etiquetaVerticeOrigen, Comparable etiquetaVerticeDestino) {
        int posicionVerticeOrigen = posicionVerticeHashMap(etiquetaVerticeOrigen);
        int posicionVerticeDestino = posicionVerticeHashMap(etiquetaVerticeDestino);
        if (posicionVerticeOrigen != -1 && posicionVerticeDestino != -1 && existeCamino(etiquetaVerticeOrigen, etiquetaVerticeDestino)) {
            if (matrizPredecesoresGrafo[posicionVerticeOrigen][posicionVerticeDestino] == -1d) {
                System.out.println(etiquetaVerticeOrigen + " - " + etiquetaVerticeDestino);
            } else {
                obtenerMejorCamino(etiquetaVerticeOrigen, verticeEnPosicion(matrizPredecesoresGrafo[posicionVerticeOrigen][posicionVerticeDestino]));
                obtenerMejorCamino(verticeEnPosicion(matrizPredecesoresGrafo[posicionVerticeOrigen][posicionVerticeDestino]), etiquetaVerticeDestino);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="GG & SS">
    /**
     * @return the matrizPredecesoresGrafo
     */
    public Double[][] getMatrizPredecesoresGrafo() {
        return matrizPredecesoresGrafo;
    }

    /**
     * @param matrizPredecesoresGrafo the matrizPredecesoresGrafo to set
     */
    public void setMatrizPredecesoresGrafo(Double[][] matrizPredecesoresGrafo) {
        this.matrizPredecesoresGrafo = matrizPredecesoresGrafo;
    }

    /**
     * @return the matrizWarshall
     */
    public Boolean[][] getMatrizWarshall() {
        return matrizWarshall;
    }

    /**
     * @param matrizWarshall the matrizWarshall to set
     */
    public void setMatrizWarshall(Boolean[][] matrizWarshall) {
        this.matrizWarshall = matrizWarshall;
    }
    //</editor-fold>
}
