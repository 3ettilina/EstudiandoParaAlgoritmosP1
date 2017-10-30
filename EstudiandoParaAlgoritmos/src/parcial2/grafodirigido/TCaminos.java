package parcial2.grafodirigido;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import UT4.TA5.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCaminos {
    
    private Collection<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public String imprimirCaminos(){
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos){
            sb.append(camino.imprimirEtiquetas()+ " " + camino.getCostoTotal() + "\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }
    
    public TCamino obtenerCaminoMasBarato(){
        Double min = Double.MAX_VALUE;
        TCamino cami = null;
        for (TCamino camino : caminos){
            if (min>camino.getCostoTotal()){
                min = camino.getCostoTotal();
                cami = camino;
            }
        }
        return cami;
    }
    
    public TCamino obtenerCaminoMasCaro(){
        Double max = -1.0;
        TCamino cami = null;
        for (TCamino camino : caminos){
            if (max<camino.getCostoTotal()){
                max = camino.getCostoTotal();
                cami = camino;
            }
        }
        return cami;
    }
    
}
