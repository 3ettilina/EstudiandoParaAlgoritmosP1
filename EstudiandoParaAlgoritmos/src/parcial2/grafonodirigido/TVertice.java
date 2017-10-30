package parcial2.grafonodirigido;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice implements IVertice{

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private Object datos;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    @Override
    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    @Override
    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }


    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public Object getDatos() {
        return datos; 
    }

    @Override
    public void bpf(Collection<Comparable> visitados) {
        setVisitado(true);
        visitados.add(this.getEtiqueta());
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    @Override
   public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio,TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice destino = adyacente.getDestino();
            if(!destino.getVisitado()){
                if(destino.getEtiqueta().compareTo(etVertDest) == 0){
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacente);
                    todosLosCaminos.getCaminos().add(copia);
                }
                else{
                    caminoPrevio.agregarAdyacencia(adyacente);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);     
                    caminoPrevio.eliminarAdyacencia(adyacente);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
  } 
   
   public boolean tieneCiclo(TCamino camino){
      boolean x=false;
      this.setVisitado(true);
      for (TAdyacencia adyacente : adyacentes) {
          TVertice destino = adyacente.getDestino();
          if(destino.visitado){
              if(camino.getOtrosVertices().contains(destino.etiqueta)){
                  camino.agregarAdyacencia(adyacente);
                  return true;
              }
              else{
                  camino.agregarAdyacencia(adyacente);
                  x= destino.tieneCiclo(camino);
              }
              
          }
          else{
              camino.agregarAdyacencia(adyacente);
              x= destino.tieneCiclo(camino);
          }
          camino.eliminarAdyacencia(adyacente);
          
      }
      
      return x;
  }
  
 public String bea (){
        Queue<TVertice> c = new LinkedList<TVertice>();

        String resultado = "";
        TVertice x;
        TVertice i;
        this.setVisitado(true);
        c.add(this);
        resultado += this.getEtiqueta() + " - ";
        while(!c.isEmpty()){
            x= c.remove();
            for (TAdyacencia y : x.getAdyacentes()){
                i= y.getDestino();
                if (!i.getVisitado()){
                    i.setVisitado(true);
                    c.add(i);
                    resultado += i.getEtiqueta() + " - ";
                }
            }
        }
        return resultado;
    }
}

