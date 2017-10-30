package parcial2.grafonodirigido;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo("src/parcial2/grafonodirigido/verticesGND.txt", "src/parcial2/grafonodirigido/aristasGND.txt", false, TGrafoNoDirigido.class);

        TGrafoNoDirigido aacm = gnd.Prim();
    }

}
