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
        TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo("src/parcial2/grafonodirigido/verticesBEA_nuevo.txt", "src/parcial2/grafonodirigido/aristasBEA_nuevo.txt", false, TGrafoNoDirigido.class);
        System.out.println(gnd.bea("a"));
//        System.out.println(gnd.esConexo("a"));
//        System.out.println(gnd.bpf());
        TCaminos losCaminos = gnd.todosLosCaminos("a", "d");
        System.out.println(losCaminos.obtenerCaminoMasCaro().getCostoTotal());

        TGrafoNoDirigido aacm = gnd.Prim();
    }
}
