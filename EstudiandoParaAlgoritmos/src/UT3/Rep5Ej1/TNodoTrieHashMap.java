/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.Rep5Ej1;

import java.util.LinkedList;

/**
 *
 * @author Bettina
 */
public class TNodoTrieHashMap {
    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrieHashMap[] hijos;
    private boolean esPalabra;
    
    public TNodoTrieHashMap() {
        hijos = new TNodoTrieHashMap[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }
    
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
        // implementar
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo+s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    predecir(s + (char) (c + 'a'),prefijo, palabras,nodo.hijos[c]);
                }
            }
        }
        
    }
    
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieHashMap nodo = buscarNodoTrie(prefijo);
        if (nodo != null){
            predecir("", prefijo, palabras, nodo);
        }
    }
}
