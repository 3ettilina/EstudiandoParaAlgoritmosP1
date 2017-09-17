/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.Rep5Ej1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Bettina
 */
public class TNodoTrieHashMap {

    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
    }

    public void insertar(String unaPalabra) {
        TNodoTrieHashMap unNodo = this;
        for (int i = 0; i < unaPalabra.length(); i++) {
            Character letra = unaPalabra.charAt(i);
            if (!unNodo.hijos.containsKey(letra)) {
                unNodo.hijos.put(letra, new TNodoTrieHashMap());
            }
            unNodo = unNodo.hijos.get(letra);
        }
        unNodo.esPalabra = true;
    }

    public int buscar(String unaPalabra, int[] contador) {
        TNodoTrieHashMap unNodo = this;
        for (int i = 0; i < unaPalabra.length(); i++) {
            Character letra = unaPalabra.charAt(i);
            if (unNodo.hijos.containsKey(letra)) {
                contador[0]++;
                unNodo = unNodo.hijos.get(letra);
            } else {
                return -1;
            }
        }
        if (unNodo.esPalabra){
            return contador[0];
        }
        else{
            return -1;
        }
    }

    private void imprimir(String unString, TNodoTrieHashMap unNodo) {
        if (unNodo != null) {
            if (unNodo.esPalabra) {
                System.out.println(unString);
            }
            for (Character caracter : unNodo.hijos.keySet()) {
                if (unNodo.hijos.get(caracter) != null) {
                    imprimir(unString + caracter, unNodo.hijos.get(caracter));
                }
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }

    public TNodoTrieHashMap buscarNodoTrie(String unString) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < unString.length(); c++) {
            Character character = unString.charAt(c);
            if (nodo.hijos.get(character) == null) {
                return null;
            }
            nodo = nodo.hijos.get(character);
        }
        return nodo;
    }

    private void predecir(String unString, String unPrefijo, LinkedList<String> listaPalabras, TNodoTrieHashMap unNodo) {
        if (unNodo != null) {
            if (unNodo.esPalabra) {
                listaPalabras.add(unPrefijo + unString);
            }
            for (Character caracter : unNodo.hijos.keySet()) {
                if (unNodo.hijos.get(caracter) != null) {
                    predecir(unString + caracter, unPrefijo, listaPalabras, unNodo.hijos.get(caracter));
                }
            }
        }
    }

    public void predecir(String unPrefijo, LinkedList<String> listaPalabras) {
        TNodoTrieHashMap nodo = buscarNodoTrie(unPrefijo);
        predecir("", unPrefijo, listaPalabras, nodo);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">  
    private int cantidasPalabrasArchivo() {
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("src/palabras.txt");
        return palabras.length;
    }

    /**
     * @return the hijos
     */
    public HashMap getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(HashMap hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the esPalabra
     */
    public boolean isEsPalabra() {
        return esPalabra;
    }

    /**
     * @param esPalabra the esPalabra to set
     */
    public void setEsPalabra(boolean esPalabra) {
        this.esPalabra = esPalabra;
    }
//</editor-fold>

}
