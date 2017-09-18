package UT5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TGrafoNoDirigido extends TGrafoDirigido {

    private TAristas aristas;

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristasC) {
        super(vertices, aristasC);  
  
    }




    // este procedimiento de PRIM usa la lista de aristas expl�cita para
    // resolver. Por claridad y seguridad, se arman listas de v�rtices para
    // trabajar,
    // "VerticesU" y "VerticesV", de forma de hacerlo lo m�s parecido posible al
    // seudoc�digo abstracto.
    // al final devuelve un nuevo grafo no dirigido que es el �rbol abarcador de
    // costo m�nimo obtenido.
    public TGrafoNoDirigido Prim() {
        // devuelve un nuevo grafo, que es el Arbol Abarcador de Costo M�nimo
        int costoPrim = 0;
        Map<Comparable, TVertice> vertices1= this.getVertices();
        Stack<TVertice> U = new Stack();
        LinkedList<TVertice> V = (LinkedList<TVertice>) vertices1.values();
        LinkedList<TArista> a = new LinkedList();
        TAristas AristasAAM = new TAristas();
        TArista tempArista;
        int tamañoV = V.size();
        while (tamañoV!=U.size()) {
            ArrayList<TVertice> min = new ArrayList();
            min.add(U.peek());
            min.add(V.getFirst());
            Double costoMin = aristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta()).costo;
            for(TVertice w : U){
                for (TVertice x : V){
                    if (aristas.buscar(w.getEtiqueta(), x.getEtiqueta())!=null){
                        if(aristas.buscar(w.getEtiqueta(), x.getEtiqueta()).costo<costoMin){
                            min.set(0, w);
                            min.set(1, x);
                            costoMin=aristas.buscar(w.getEtiqueta(), x.getEtiqueta()).costo;
                        }
                    }
                }
            }
            U.push(min.get(1));
            a.add(aristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta()));
            AristasAAM.insertarAlFinal(aristas.buscar(min.get(0).getEtiqueta(),min.get(1).getEtiqueta()));
            V.remove(V.indexOf(min.get(1)));
        }
        //TGrafoNoDirigido gnd=new TGrafoNoDirigido(AristasAAM,U,a);
        return this;
    }
    
    public String bea (UT5.TA3.TVertice v){
        Queue<UT5.TA3.TVertice> c = new LinkedList<UT5.TA3.TVertice>();
        String resultado = "";
        UT5.TA3.TVertice x;
        UT5.TA3.TVertice i;
        v.setVisitado(true);
        c.add(v);
        resultado += v.getEtiqueta() + " - ";
        while(c.isEmpty()){
            x= c.remove();
            for (UT5.TA3.TAdyacencia y : x.getAdyacentes()){
                i= y.getDestino();
                if (!i.getVisitado()){
                    i.setVisitado(true);
                    c.add(i);
                    resultado += i + " - ";
                }
            }
        }
        return resultado;
    }
    
    

    @Override
    public boolean insertarArista(TArista arista) {
        TArista invertida = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        return (super.insertarArista(arista) && super.insertarArista(invertida));
    }
    
}
