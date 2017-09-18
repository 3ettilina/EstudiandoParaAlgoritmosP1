package Parcial1Equipos;


import java.util.ArrayList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TTrieSufijosHashMap trie = new TTrieSufijosHashMap();


        String eltexto = "bananasenlasmananascuandolasranasnocomenlasanaspanamalozanas";
       // TODO: insertar todos los sufijos en el trie, "anotando" la posición del texto (base 0) en que comienza cada sufijo

        for(int i=(eltexto.length()-1);i>-1;i--){
            String sufijo = eltexto.substring(i, eltexto.length());
            trie.insertar(sufijo, i);
            
        }
        
                    
        //TODO: hallar las posiciones del texto en que comienza cada ocurrencia del patrón "ana" 
        //algun arrayList = trie.posicionesPatron("ana");
        String patron="ana";
        ArrayList<Integer> posiciones=new ArrayList<>();
        trie.ocurrenciasPosicionesPatron(patron, posiciones);
        
        
        // TODO: IMPRIMIR EN CONSOLA LAS POSICIONES DEL TEXTO EN QUE SE ENCUENTRA EL PATRÓN "ana"
        System.out.println("Patrón ("+patron+")");
        System.out.println("Ocurrencias: "+posiciones.get(0));
        System.out.println("En posiciones: ");
        for(int i=1;i<posiciones.size();i++){
            System.out.println(posiciones.get(i));
        }
        
        
    }
}
