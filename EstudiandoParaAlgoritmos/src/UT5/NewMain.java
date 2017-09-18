/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT5;

/**
 *
 * @author Nicolas
 */
public class NewMain {

   
    public static void main(String[] args) {
       TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo("src/UT5/vertices.txt","src/UT5/aristas.txt",
                false, TGrafoNoDirigido.class);
       
       //gnd.bea();
    }
    
}
