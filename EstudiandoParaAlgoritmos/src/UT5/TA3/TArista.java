package UT5.TA3; 

import UT5.*;


public class TArista {

    protected Comparable etiquetaOrigen;
    protected Comparable etiquetaDestino;
    protected Double costo;

    public TArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, Double costo) {
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
    }

    
    
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    
}
