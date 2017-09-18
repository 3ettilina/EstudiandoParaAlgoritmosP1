/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA3;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author andres
 */
public class MedicionPredecirHashMap extends Medible{
    private HashMap hashMap;
    
     public MedicionPredecirHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String palabra = (String) params[1];
        for(int i = 0; i < repeticion; i++){
            LinkedList resultado = predecir(palabra);
        }
                        
    }
    
    private LinkedList predecir(String prefijo){
        LinkedList resultado = new LinkedList();
        Collection<String> hashValues = hashMap.values();
        for(String value: hashValues){
            if(value.length()>=prefijo.length()){
                if (value.substring(0, (prefijo.length()-1)).equals(prefijo)){
                    resultado.add(value);                
                }            
            }
        }
        return resultado;
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }
    
}
