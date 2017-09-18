/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA4;

/**
 *
 * @author andres
 */
public class main {
    
    
    
    public static void main(String args[]){
        
    double [] factores = {0.9}; 
    String[] clavesInsertar = ManejadorArchivosGenerico.leerArchivo("src/UT3/TA4/claves_insertar.txt");
    String[] clavesBuscar = ManejadorArchivosGenerico.leerArchivo("src/UT3/TA4/claves_buscar.txt");
    
          
    for(double factor: factores){
    
    TablaHash tabla = new TablaHash(200, factor);
    
    int contador = 0; 
        System.out.println("<<<<<<<<<<<<<<<<  factor "+ factor +"   >>>>>>>>>>>>>>>>>>>>>");
    for (int i = 0; i < clavesInsertar.length; i++) {            
            int insercion = tabla.insertarCuad(Integer.parseInt(clavesInsertar[i]));
            contador += insercion;
         
            if (insercion < 0)
            {
                System.out.println("No se pudo insertar la clave: " + clavesInsertar[i]);
            }          
        }
    double x = contador / clavesInsertar.length;
        System.out.println("Promedio comparaciones insercion: " + x);
        
        
   
    
    int contadorExitosas = 0;
    int contadorNoExitosas = 0;
    int contadorExitosasCantidad = 0;
    int contadorNoExitosasCantidad = 0;
        for (int i = 0; i < clavesBuscar.length; i++) {
            int busqueda = tabla.buscarCuad(Integer.parseInt(clavesBuscar[i]));

            if (busqueda < 0)
            {
                contadorNoExitosas -= busqueda;
                contadorNoExitosasCantidad ++;
                
                
            }
            else
            {
                contadorExitosas += busqueda;
                contadorExitosasCantidad ++;
            }
            
        }
        double c = contadorExitosas / contadorExitosasCantidad;
        System.out.println("Promedio busquedas exitosas: " + (c));
        System.out.println("Promedio busquedas no exitosas: " + (contadorNoExitosas / contadorNoExitosasCantidad));
        
        
        
      
        
    }
    
    }
}
