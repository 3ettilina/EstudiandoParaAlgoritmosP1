/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.Rep5Ej1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Bettina
 */
public class TNodoTrieHashMap {

    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private int posicion;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        posicion = -1;

    }

    public void insertar(String unaPalabra, int unaPosicion) {
        TNodoTrieHashMap unNodo = this;
        for (int i = 0; i < unaPalabra.length(); i++) {
            Character letra = unaPalabra.charAt(i);
            if (!unNodo.hijos.containsKey(letra)) {
                unNodo.hijos.put(letra, new TNodoTrieHashMap());
            }
            unNodo = unNodo.hijos.get(letra);
        }
        unNodo.esPalabra = true;
        unNodo.setPosicion(unaPosicion);
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
        if (unNodo.esPalabra) {
            return contador[0];
        } else {
            return -1;
        }
    }

    private void imprimir(String unString, TNodoTrieHashMap unNodo) {
        if (unNodo != null) {
            if (unNodo.esPalabra) {
                System.out.println(unString + " " + unNodo.getPosicion());
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

    public void contarPalAux(TNodoTrieHashMap nodo, int[] contador) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                contador[0]++;

            }
            for (Character caracter : nodo.hijos.keySet()) {
                if (nodo.hijos.get(caracter) != null) {
                    contarPalAux(nodo.hijos.get(caracter), contador);
                }
            }
        }
    }

    public int contarPal() {
        int[] contador = new int[1];
        for (Character caracter : hijos.keySet()) {
            contarPalAux(hijos.get(caracter), contador);
        }
        return contador[0];
    }

    public void alturaTrieAux(TNodoTrieHashMap nodo, int contador, int mayor[]) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                if (mayor[0] <= contador) {
                    if (mayor[0] < contador) {
                        mayor[0] = contador;
                    }
                }
            }
            for (Character caracter : nodo.hijos.keySet()) {
                if (nodo.hijos.get(caracter) != null) {
                    alturaTrieAux(nodo.hijos.get(caracter), contador + 1, mayor);
                }
            }
        }
    }

    public int alturaTrie() {
        int contador = 1;
        int mayor[] = new int[1];
        for (Character caracter : hijos.keySet()) {
            alturaTrieAux(hijos.get(caracter), contador, mayor);
        }
        return mayor[0];
    }

    public void contarPrefijosAux(TNodoTrieHashMap nodo, int[] contador) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                contador[0]++;

            }
        }
        for (Character caracter : nodo.hijos.keySet()) {
            if (nodo.hijos.get(caracter) != null) {
                contador[0]++;
                contarPrefijosAux(nodo.hijos.get(caracter), contador);
            }
        }
    }

    public int contarPrefijos() {
        int[] contador2 = new int[1];
        for (Character caracter : hijos.keySet()) {
            contarPrefijosAux(hijos.get(caracter), contador2);
        }

        return contador2[0];
    }

    private void ocurrenciasPosicionesPatron(String patron, ArrayList<Integer> posiciones, TNodoTrieHashMap nodo) {
        // TODO completar
        if (nodo != null) {
            if (nodo.esPalabra) {

                posiciones.add(0, posiciones.get(0) + 1);
                posiciones.remove(1);
                posiciones.add(nodo.posicion);
            }
            for (Character caracter : nodo.hijos.keySet()) {
                if (nodo.hijos.get(caracter) != null) {
                    ocurrenciasPosicionesPatron(patron, posiciones, nodo.hijos.get(caracter));
                }
            }
        }
    }

    public ArrayList<Integer> ocurrenciasPosicionesPatron(String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        posiciones.add(0);
        TNodoTrieHashMap nodo = this.buscarNodoTrie(patron);
        ocurrenciasPosicionesPatron(patron, posiciones, nodo);
        return posiciones;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">  
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

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
//</editor-fold>

}
