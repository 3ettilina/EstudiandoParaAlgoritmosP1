/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT2.TA3;

import java.util.LinkedList;

/**
 *
 * @author andres
 */
public class Main {
    
     public static void main(String[] args) {
        
         
         
        TArbolTrie triee = new TArbolTrie();
        String[] claves = ManejadorArchivosGenerico.leerArchivo("src/UT2/TA3/palabras1.txt");
        
        for (int i = 0; i < claves.length; i++) {

          triee.insertar(claves[i].trim().toLowerCase());
         
        }
        
        triee.imprimir();
        
         
        TArbolTrie trie = new TArbolTrie();
        trie.insertar("casa");
        trie.insertar("casamiento");
        trie.insertar("casablanca");
        trie.insertar("arbol");
        trie.insertar("grito");
        //trie.imprimir();
        
       
        //System.out.println(trie.buscarComparaciones("casablanca"));
        
         LinkedList<String> lista = trie.predecir1("ca");
         for (String clave : lista) {
             System.out.println(clave);
             
         }
        
         
        
        
        
     
        
        
     }
    
}
