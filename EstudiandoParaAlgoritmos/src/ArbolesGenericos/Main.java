/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolesGenericos;

/**
 *
 * @author Bettina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TArbolGen arbG = new TArbolGen();
        arbG.insertar("Feria", null);
        arbG.insertar("Frutas", "Feria");
        arbG.insertar("Verduras", "Feria");
        arbG.insertar("Tomate", "Fruta");
        
        arbG.listarIndentado();
        
        TNodoAG feria = arbG.buscar("Frutas");
        if (feria == null)
            System.out.println("Null");
        
    }
    
}
