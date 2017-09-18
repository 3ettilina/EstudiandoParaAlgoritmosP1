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
public class MedicionBuscarTArbolTrie extends Medible {
    
    private TArbolTrie tArbolTrie;

    public MedicionBuscarTArbolTrie(TArbolTrie tArbolTrie) {
        this.tArbolTrie = tArbolTrie;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                tArbolTrie.buscar(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.tArbolTrie;
    }
    
}
