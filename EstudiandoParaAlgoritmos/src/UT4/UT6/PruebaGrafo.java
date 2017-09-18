package UT4.UT6;

import UT4.TA5.*;


public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/UT4/UT6/aeropuertos_2.txt","src/UT4/UT6/conexiones_2.txt",
                false, TGrafoDirigido.class);
        
        
       //TCaminos caminos = gd.todosLosCaminos("Asuncion", "Curitiba");
       //TCaminos caminos = gd.todosLosCaminos("Montevideo", "Rio_de_Janeiro");
       //caminos.imprimirCaminosConsola();
        System.out.println(gd.tieneCiclo());
       
       
    }
}
