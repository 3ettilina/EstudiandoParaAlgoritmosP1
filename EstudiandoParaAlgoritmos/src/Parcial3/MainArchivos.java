/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial3;

/**
 *
 * @author GamerX
 */
public class MainArchivos {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] claves=ManejadorArchivosGenerico.leerArchivo("src\\Parcial3\\clavesBinsort.txt", true);
        int[] vector=new int[claves.length];
        int i=0;
        for(String clave:claves){
            vector[i]=Integer.parseInt(clave);
            i++;
        }
        TClasificador clasif=new TClasificador();
        int metodo=9; 
        int[] vectorOrdenado=clasif.clasificar(vector, metodo, false);
        String[] salida=new String[vectorOrdenado.length];
        for(int j=0;j<vectorOrdenado.length;j++){
            salida[j]=vectorOrdenado[j]+"";
        }
        
        ManejadorArchivosGenerico.escribirArchivo("src\\Parcial3\\salida.txt", salida);
        
    }
}
