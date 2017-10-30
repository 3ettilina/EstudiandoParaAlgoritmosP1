package parcial2.grafodirigido;


public class PruebaGrafo {

    public static void main(String[] args) {
        
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/UT5/TA2/vertices.txt",
                "src/UT5/TA2/aristas.txt",
                false, TGrafoNoDirigido.class);
        
        System.out.println(gnd.bea("a"));
        
        System.out.println(gnd.Prim().bea("f"));
        
    }
}
