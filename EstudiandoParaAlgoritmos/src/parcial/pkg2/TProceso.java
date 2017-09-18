package parcial.pkg2;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public class TProceso extends TGrafoDirigido implements IProceso{

    public TProceso(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }
   
    /**
     *
     * @return
     */
    @Override
    public TCamino obtenerSecuenciaCritica() {
        TCaminos c = this.todosLosCaminos("Start", "End");
        String[] lectura = ManejadorArchivosGenerico.leerArchivo("src\\parcial\\pkg2\\tasks.txt", false);
        Map<Comparable,Comparable> map = new HashMap();
        for (String linea: lectura){
            String[] lineaSplit = linea.split(",");
            map.put(lineaSplit[0], lineaSplit[1]);
        }
        TCamino secuenciaCritica = new TCamino(new TVertice("a"));
        int costoMax = 0;
        for(TCamino camino : c.getCaminos()){
            int costo = 0;
            for (Comparable elemento : camino.imprimirEtiquetas().split(" -> ")){
                if (map.get(elemento) != null){
                    int a = Integer.parseInt((String) map.get(elemento));
                    costo+=a;
                }
            }
            if (costo>costoMax){
                costoMax = costo;
                secuenciaCritica = camino;
                secuenciaCritica.setCostoTotal(Double.parseDouble(costo+""));
            }
       }
        return secuenciaCritica;
    }
 
}
