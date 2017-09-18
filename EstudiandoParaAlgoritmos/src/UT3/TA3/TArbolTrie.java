/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA3;

import UT2.TA3.*;
import java.util.LinkedList;
import java.io.Serializable;


/**
 *
 * @author andres
 */
public class TArbolTrie implements Serializable {
    
    private TNodoTrie raiz;

    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }
    
    public boolean buscarComparaciones(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        return raiz.buscarComparaciones(palabra);
    }
    
    public int buscar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        return raiz.buscar(palabra);
    }
    
    public LinkedList<String> predecir1(String palabra)
    {
        LinkedList<String> lista = new LinkedList<>();
        LinkedList<String> listaFinal = new LinkedList<>();
        if(raiz != null)
        {
            raiz.predecir1(palabra,"" ,lista);
            for(String word:lista){
                listaFinal.add(palabra+word);
            }
            return listaFinal;
            
            
        }
        return null;
        
    }
    
}
