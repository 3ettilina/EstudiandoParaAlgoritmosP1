package UT5.TA3;

import UT5.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    private TAdyacencia buscarAdyacencia(Comparable etiqueta) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiqueta) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    public String bpf() {
        StringBuilder tempStr = new StringBuilder();
        Collection<Comparable> listaBpf = new LinkedList<>();
        this.bpf(listaBpf);
        for (Comparable etiqueta : listaBpf) {
            tempStr.append(etiqueta + " ; ");
        }
        return tempStr.toString();
    }

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

    public boolean tieneCiclo(TCamino camino) {
        setVisitado(true);
        
        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            if (ciclo) {
                break;
            }
            TVertice w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                camino.agregarAdyacencia(adyacencia);
                ciclo = w.tieneCiclo(camino);
            } else {
                ciclo = true;
                System.out.println("hay ciclo : "+camino.imprimirDesdeClave(w.etiqueta));
            }
        }
        camino.getOtrosVertices().remove(this.getEtiqueta());
        return ciclo;

    }
    
      public String bea (){
        Queue<TVertice> c = new LinkedList<TVertice>();
        String resultado = "";
        TVertice x;
        TVertice i;
        this.setVisitado(true);
        c.add(this);
        resultado += this.getEtiqueta() + " - ";
        while(c.isEmpty()){
            x= c.remove();
            for (TAdyacencia y : x.getAdyacentes()){
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
}
