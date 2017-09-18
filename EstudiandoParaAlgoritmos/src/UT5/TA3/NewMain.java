/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT5.TA3;

import UT5.*;

/**
 *
 * @author Nicolas
 */
public class NewMain {

   
    public static void main(String[] args) {
       TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("src/UT5/vertices_1.txt","src/UT5/aristas_1.txt",
                false, TGrafoNoDirigido.class);
       
       //gnd.Prim();
    }
    
}
