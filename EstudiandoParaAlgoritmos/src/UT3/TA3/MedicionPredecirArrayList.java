/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Nicolas
 */
public class MedicionPredecirArrayList extends Medible{
    
    private ArrayList arrayList;

    public MedicionPredecirArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String palabra = (String) params[1];
        for(int i = 0; i < repeticion; i++){
            predecir(palabra);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arrayList;
    }
    
    public LinkedList predecir(String prefijo){
        LinkedList resultado=new LinkedList();
        for(int i=0;i<arrayList.size();i++){
            String palabra= (String) arrayList.get(i);
            if(palabra.length()>=prefijo.length()){
                if(palabra.substring(0,prefijo.length()-1).equals(prefijo)){
                    resultado.add(palabra);
                }
            }
        }
        return resultado;
    }
}
