package TriesBetti;



import java.io.Serializable;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie, Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    @Override
    public int buscar(String palabra, int[] contador){
        TNodoTrie nodo = this;
        for(int c=0; c<palabra.length();c++){
            int indice = palabra.charAt(c) - 'a';
            if(nodo.hijos[indice]!=null){
                contador[0]++;
                //``¡TNodoTrie hijo = nodo.hijos['c'];
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
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo) {
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
    
      private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }
    
     private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
         if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
 
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                TNodoTrie hijo = nodo.hijos[c];
                if (hijo != null) {
                    hijo.predecir(s+(char)(c + 'a'), prefijo, palabras, hijo);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodo = this;
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
