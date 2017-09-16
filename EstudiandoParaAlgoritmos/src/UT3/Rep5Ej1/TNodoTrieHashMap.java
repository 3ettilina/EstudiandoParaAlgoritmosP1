/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.Rep5Ej1;

import CodigoHash.HashPiola;
import java.util.LinkedList;

/**
 *
 * @author Bettina
 */
public class TNodoTrieHashMap {
    private HashPiola hijos;
    private 

    public TNodoTrieHashMap() {
        hijos = new HashPiola(cantidasPalabrasArchivo(), 0.5);
        
    }

    public void insertar(String unaPalabra) {
        
    }
    
    public int buscar(String palabra, int[] contador){
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        
    }

    public void imprimir() {
    }

    private TNodoTrieHashMap buscarNodoTrie(String s) {
        
    }

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrieHashMap nodo) {
        
    }

    public void predecir(String prefijo, LinkedList<String> palabras) {
        
    }
//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    

    /**
     * @return the hijos
     */
    public HashPiola[] getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(HashPiola[] hijos) {
        this.hijos = hijos;
    }
//</editor-fold>

    private int cantidasPalabrasArchivo() {
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("src/palabras.txt");
        return palabras.length;
    }
}
