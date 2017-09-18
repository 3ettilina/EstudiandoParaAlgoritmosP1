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
import UT2.TA3.*;
import java.util.LinkedList;
import java.io.Serializable;


public class TNodoTrie implements Serializable{
    
    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;

   
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

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
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }
    
    public boolean buscarComparaciones(String unaPalabra) {
        TNodoTrie nodo = this;
        int cantComp = 0;
        for (int c = 0; c < unaPalabra.length(); c++) {
            cantComp ++;
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                //System.out.println(cantComp + " comparaciones.");
                return false;
            }
            nodo = nodo.hijos[indice];
        }
        //System.out.println(cantComp + " comparaciones.");
        return true;
    }
    
    public int buscar(String s) {
        TNodoTrie nodo = this;
        int temp = 0;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            temp++;
            if (nodo.hijos[indice] == null) {
                return -temp;
            }
            nodo = nodo.hijos[indice];
        }
        if (nodo.esPalabra) {
            return temp;
        }
        return -temp;
    }
    
    public void predecir1(String palabra, String sufijo, LinkedList<String> lista)
    {
       
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            int indice = palabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return;   
            }
            nodo = nodo.hijos[indice];
        }
        

        if (nodo.esPalabra) {
            lista.add(sufijo);
        }
        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (nodo.hijos[c] != null) {
                nodo.hijos[c].predecir1("",sufijo +(char) (c + 'a'), lista);
            }
        }
    }
        
       

    
}
