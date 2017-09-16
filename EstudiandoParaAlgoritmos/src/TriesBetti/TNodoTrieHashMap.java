package TriesBetti;



import java.io.Serializable;
import java.util.LinkedList;

public class TNodoTrieHashMap implements INodoTrie, Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrieHashMap[] hijos;
    private boolean esPalabra;

    public TNodoTrieHashMap() {
        hijos = new TNodoTrieHashMap[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    @Override
    public int buscar(String palabra, int[] contador){
        TNodoTrieHashMap nodo = this;
        for(int c=0; c<palabra.length();c++){
            int indice = palabra.charAt(c) - 'a';
            if(nodo.hijos[indice]!=null){
                contador[0]++;
                //``¡TNodoTrieHashMap hijo = nodo.hijos['c'];
                if(nodo.hijos[indice].esPalabra){
                    return contador[0];
                }else{
                    nodo = nodo.hijos[indice];
                }
            }
            
        }
        return contador[0];
    }
    
    @Override
    public void insertar(String unaPalabra) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrieHashMap();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
                
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s+(char)(c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        
        imprimir("", this);
    }
    
      private TNodoTrieHashMap buscarNodoTrie(String s) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }
    
     private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrieHashMap nodo) {
         if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
 
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                TNodoTrieHashMap hijo = nodo.hijos[c];
                if (hijo != null) {
                    hijo.predecir(s+(char)(c + 'a'), prefijo, palabras, hijo);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < prefijo.length(); c++) {
            int indice = prefijo.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return;
            }
            nodo = nodo.hijos[indice];
        }
        nodo.predecir(prefijo, prefijo, palabras, nodo);
        
    }
  
}
