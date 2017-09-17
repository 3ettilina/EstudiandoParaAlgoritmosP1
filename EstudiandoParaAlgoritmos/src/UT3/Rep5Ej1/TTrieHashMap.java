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
public class TTrieHashMap {

    private TNodoTrieHashMap raiz;

    public void insertar(String unaPalabra, int unaPosicion) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertar(unaPalabra, unaPosicion);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    public int buscar(String palabra) {
        int[] cont = new int[1];
        cont[0] = 0;

        if (this.raiz != null) {
            return raiz.buscar(palabra, cont);
        }
        return cont[0];
    }

    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> listaRetorno = new LinkedList<>();
        if (this.raiz != null) {
            raiz.predecir(prefijo, listaRetorno);
        }
        return listaRetorno;
    }

    public TNodoTrieHashMap buscarNodoTrie(String unaPalabra) {
        if (raiz != null) {
            return raiz.buscarNodoTrie(unaPalabra);
        }
        return null;
    }
}
