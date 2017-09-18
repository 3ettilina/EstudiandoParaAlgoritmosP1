package UT3.TA3;

import UT3.TA3.Medible;
import UT3.TA3.MedicionBuscarLinkedList;
import java.util.*;

public class Main {

    private static final int REPETICIONES = 100;

    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();      
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src/UT3/TA3/listado-general_desordenado.txt");
        String[] palabrasBuscar = ManejadorArchivosGenerico.leerArchivo("src/UT3/TA3/listado-general_palabrasBuscar.txt");
        for (String p : palabrasclave) {
                trie.insertar(p);
                linkedList.add(p);
                arrayList.add(p);
                hashMap.put(p, p);
                treeMap.put(p,p);
        }
        
        Medible[] medibles = {new MedicionBuscarLinkedList(linkedList)};
        //TODO: Cargar el resto de los medibles
        
        Medible[] mediblesArrayList = {new MedicionBuscarArrayList(arrayList)};
        Medible[] mediblesTArbolTrie = {new MedicionBuscarTArbolTrie(trie)};
        Medible[] mediblesHashMap = {new MedicionBuscarHashMap(hashMap)};
        Medible[] mediblesTreeMap = {new MedicionBuscarTreeMap(treeMap)};
        
        
        
        System.out.println("BUSCAR:"); 
        Object[] params = {REPETICIONES, palabrasBuscar};
       
        System.out.println("LinkedList");
        for (Medible m: medibles){
            m.medir(params).print();
        }
        
        System.out.println("ArrayList");
        for (Medible m: mediblesArrayList){
            m.medir(params).print();
        }
        
        System.out.println("TArbolTrie");
        for (Medible m: mediblesTArbolTrie){
            m.medir(params).print();
        }
        
        System.out.println("HasMap");
        for (Medible m: mediblesHashMap){
            m.medir(params).print();
        }
        System.out.println("TreeMap");
        for (Medible m: mediblesTreeMap){
            m.medir(params).print();
        }
        
        System.out.println(); 
        
        System.out.println("PREDECIR:"); 
       
        String prefijoPredecir="cas";
        Object[] paramsPredecir = {REPETICIONES, prefijoPredecir};
       
        //Se cargan los medibles para Predecir
        Medible[] mediblePredictLL = {new MedicionPredecirLinkedList(linkedList)};
        Medible[] mediblePredictArrayList = {new MedicionPredecirArrayList(arrayList)};
        Medible[] mediblePredictTArbolTrie = {new MedicionPredecirTrie(trie)};
        Medible[] mediblePredictHashMap = {new MedicionPredecirHashMap(hashMap)};
        
        System.out.println("LinkedList");
        for (Medible m: mediblePredictLL){
            m.medir(paramsPredecir).print();
        }
        System.out.println("ArrayList");
        for (Medible m: mediblePredictArrayList){
            m.medir(paramsPredecir).print();
        }
        System.out.println("TArbolTrie");
        for (Medible m: mediblePredictTArbolTrie){
            m.medir(paramsPredecir).print();
        }
        System.out.println("HashMap");
        for (Medible m: mediblePredictHashMap){
            m.medir(paramsPredecir).print();
        }

    }
}