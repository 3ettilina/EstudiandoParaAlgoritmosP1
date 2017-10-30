/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT5_TA2;

/**
 *
 * @author GamerX
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
               TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo("src/UT5_TA2/vertices.txt","src/UT5_TA2/aristas.txt",
                false, TGrafoNoDirigido.class);
               
               TGrafoNoDirigido aacm = gnd.Prim();
               //System.out.println(gnd.bea("e"));
    }
    
}
