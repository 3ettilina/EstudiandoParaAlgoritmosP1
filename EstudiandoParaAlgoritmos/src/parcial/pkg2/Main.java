/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.pkg2;

/**
 *
 * @author Francisco
 */
public class Main {
     public static void main(String[] args) {
        TProceso proceso  = (TProceso) UtilGrafos.cargarGrafo(
                "src/parcial/parcial/pkg2/tasks.txt",
                "src/parcial/parcial/pkg2/links.txt",
                false, TProceso.class);
         System.out.println(proceso.tieneCiclo1());
        TCamino secC = proceso.obtenerSecuenciaCritica();
         System.out.println("La Secuencia Critica es: "+secC.imprimirEtiquetas()+" con un costo de "+secC.getCostoTotal());
    }
}
