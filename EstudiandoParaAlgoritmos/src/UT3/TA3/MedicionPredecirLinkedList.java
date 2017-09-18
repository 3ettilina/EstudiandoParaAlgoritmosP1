/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA3;

import java.util.LinkedList;

/**
 *
 * @author andres
 */
public class MedicionPredecirLinkedList extends Medible {
    
     private LinkedList linkedList;

    public MedicionPredecirLinkedList(LinkedList linkedList) {
        this.linkedList = linkedList;
    }
    
    
    public LinkedList predecir(String prefijo)
    {
        LinkedList lista = new LinkedList();       
        if (linkedList.isEmpty()) {
            return null;
        } else {
            for (Object nodo:linkedList) {
                if (nodo.toString().contains(prefijo)) {
                    lista.add(nodo);                    
                }               
            }
            return lista;
                
        }      
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String palabra = (String) params[1];
        for(int i = 0; i < repeticion; i++){
            this.predecir(palabra);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.linkedList;
    }
    
}
