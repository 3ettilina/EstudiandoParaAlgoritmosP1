package parcial2.grafonodirigido;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TGrafoNoDirigido extends TGrafoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    public TGrafoNoDirigido Prim() {
        int costoPrim = 0;
        HashMap<Comparable, TVertice> U = new HashMap();
        HashMap<Comparable, TVertice> V = new HashMap<Comparable, TVertice>();
        TAristas AristasAAM = new TAristas();
        TGrafoNoDirigido nuevoGrafo = new TGrafoNoDirigido(super.copiaVertices().values(), new LinkedList<TArista>());
        if (this.getVertices().isEmpty()) {
            return null;
        }
        for (Map.Entry<Comparable, TVertice> vertice : this.getVertices().entrySet()) {
            V.put(vertice.getKey(), vertice.getValue());
        }
        TVertice tempV;
        tempV = (TVertice) V.values().toArray()[0];
        U.put(tempV.getEtiqueta(), tempV);
        V.remove(tempV.getEtiqueta());
        boolean vaciaV = V.isEmpty();

        while (!vaciaV) {
            TArista aristaMin = this.lasAristas.buscarMin(U.values(), V.values());
            AristasAAM.add(aristaMin.copia());
            TVertice vert = V.remove(aristaMin.getEtiquetaDestino());
            if (vert != null) {
                U.put(vert.getEtiqueta(), vert);
            }
            costoPrim += aristaMin.getCosto();
            vaciaV = V.isEmpty();
        }
        for (TArista arista_ : AristasAAM) {
            nuevoGrafo.getLasAristas().add(arista_);
        }
        System.out.println("costo AAM: " + costoPrim);
        return nuevoGrafo;
    }

    public String bea(Comparable etiquetaVertice) {
        TVertice vertice = buscarVertice(etiquetaVertice);
        if (vertice != null) {
            String resultado = vertice.bea();
            this.desvisitarVertices();
            return resultado;
        }
        return "";
    }

    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }
}
