/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA3;

/**
 *
 * @author andres
 */
public class MedicionPredecirTrie extends Medible{

    private TArbolTrie tArbolTrie;

    public MedicionPredecirTrie(TArbolTrie tArbolTrie) {
        this.tArbolTrie = tArbolTrie;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String palabra = (String) params[1];
        for(int i = 0; i < repeticion; i++){
            tArbolTrie.predecir1(palabra);
            
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.tArbolTrie;
    }
    

    
    
}
