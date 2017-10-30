/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.grafodirigido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Map;

/**
 *
 * @author andres
 */
public class TGrafoNoDirigido extends TGrafoDirigido{
    
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


    // este procedimiento de PRIM usa la lista de aristas explícita para
    // resolver. Por claridad y seguridad, se arman listas de vertices para
    // trabajar,
    // "VerticesU" y "VerticesV", de forma de hacerlo lo mas parecido posible al
    // seudocodigo abstracto.
    // al final devuelve un nuevo grafo no dirigido que es el arbol abarcador de
    // costo minimo obtenido.
    public TGrafoNoDirigido Prim() {
        // devuelve un nuevo grafo, que es el Arbol Abarcador de Costo M�nimo
        Map<Comparable, TVertice> vertices1= this.getVertices();
        Stack<TVertice> U = new Stack();
        LinkedList<TVertice> V = new LinkedList();
        for (TVertice v : vertices1.values()){ V.add(v); }
        LinkedList<TArista> a = new LinkedList();
        int tamañoV = V.size();
        U.add(V.removeFirst());
        while (tamañoV!=U.size()) {
            ArrayList<TVertice> min = new ArrayList();
            min.add(U.peek());
            min.add(V.getFirst());
            Double costoMin = Double.MAX_VALUE;
            if (lasAristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta())!= null){
                costoMin = lasAristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta()).costo;
            }
            for(TVertice w : U){
                for (TVertice x : V){
                    if (lasAristas.buscar(w.getEtiqueta(), x.getEtiqueta())!=null){
                        if(lasAristas.buscar(w.getEtiqueta(), x.getEtiqueta()).costo<costoMin){
                            min.set(0, w);
                            min.set(1, x);
                            costoMin=lasAristas.buscar(w.getEtiqueta(), x.getEtiqueta()).costo;
                        }
                    }
                }
            }
            U.push(min.get(1));
            a.add(lasAristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta()));
            V.remove(V.indexOf(min.get(1)));
        }
        TGrafoNoDirigido gnd = new TGrafoNoDirigido(U,a);
        return gnd;
    }
    
        
    // No pude agarrar un item random de un Map, entonces lo resolvi haciendo una búsqueda desde un punto dado
    public boolean esConexo (Comparable unaEtiqueta){ 
        Collection<Comparable> visitados = new LinkedList <Comparable>();
        Collection<TVertice> vertices1 = this.getVertices().values();
        TVertice vertice = this.getVertices().get(unaEtiqueta);
        if (vertice != null && vertice.getVisitado() == false ){
            vertice.bpf(visitados);
        }
        if (visitados.size()==vertices1.size()){
            return true;
        }else{
            return false;
        }
    }
    public TGrafoNoDirigido Kruskal() {
        // devuelve un nuevo grafo, que es el Arbol Abarcador de Costo M�nimo
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String bea(Comparable unaEtiqueta){
        TVertice v = this.getVertices().get(unaEtiqueta);
        String bea = "";
        if(v != null){
            bea = v.bea();
        }
        return bea;
    }
    
}
