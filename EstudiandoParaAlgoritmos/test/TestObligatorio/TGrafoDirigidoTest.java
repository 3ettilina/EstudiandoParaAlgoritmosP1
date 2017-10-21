/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestObligatorio;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Enzo
 */
public class TGrafoDirigidoTest {
    
    private Aeropuerto aero1;
    private Aeropuerto aero2;
    private Aeropuerto aero3;
    private TVertice<Aeropuerto> vert1;
    private TVertice<Aeropuerto> vert2;
    private TVertice<Aeropuerto> vert3;
    private LinkedList<TVertice> vertices;
    private TArista ar1;
    private TArista ar2;
    private TArista ar3;
    private LinkedList<TArista> aristas;
    private TGrafoDirigido grafo;
    
    public TGrafoDirigidoTest() {
        aero1 = new Aeropuerto("JFK","JFK");
        vert1 = new TVertice<>("JFK");
        vert1.setDatos(aero1);
        aero2 = new Aeropuerto("MIA","MIA");
        vert2 = new TVertice<>("MIA");
        vert2.setDatos(aero1);
        aero3 = new Aeropuerto("LAX","LAX");
        vert3 = new TVertice<>("LAX");
        vert3.setDatos(aero3);
        
        vertices = new LinkedList<>();
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        
        ar1 = new TArista("JFK","LAX",150.0);
        ar2 = new TArista("JFK","MIA",100.0);
        ar3 = new TArista("MIA","LAX",40.0);
        
        aristas = new LinkedList<>();
        aristas.add(ar1);
        aristas.add(ar2);
        aristas.add(ar3);
        
        grafo = new TGrafoDirigido(vertices, aristas);
    }

    @Test
    public void testEliminarArista() {
        assertTrue(grafo.eliminarArista("JFK", "LAX"));
    }

    @Test
    public void testEliminarVertice() {
        grafo.eliminarVertice("MIA");
        assertTrue(grafo.getVertices().size()==2);
    }

    @Test
    public void testExisteArista() {
        assertTrue(grafo.existeArista("JFK", "MIA"));
    }

    @Test
    public void testExisteVertice() {
        assertTrue(grafo.existeVertice("MIA"));
    }

    @Test
    public void testInsertarArista() {
        TArista arista = new TArista("MIA","JFK",400.0);
        assertTrue(grafo.insertarArista(arista));
    }

    @Test
    public void testInsertarVertice_Comparable() {
        assertTrue(grafo.insertarVertice("STL"));
    }

    @Test
    public void testInsertarVertice_TVertice() {
        TVertice<Aeropuerto> vertice = new TVertice<>("STL");
        assertTrue(grafo.insertarVertice(vertice));
    }

    @Test
    public void testDesvisitarVertices() {
        for (TVertice<Aeropuerto> vertice:grafo.getVertices().values()){
            vertice.setVisitado(true);
            assertTrue(vertice.getVisitado());
        }
        grafo.desvisitarVertices();
        for (TVertice<Aeropuerto> vertice:grafo.getVertices().values()){
            assertFalse(vertice.getVisitado());
        }
    }

    @Test
    public void testTodosLosCaminos() {
        assertTrue(grafo.todosLosCaminos("JFK", "LAX", null, 0).getCaminos().size()==1);
        assertTrue(grafo.todosLosCaminos("JFK", "LAX", null, 1).getCaminos().size()==2);
        assertTrue(grafo.todosLosCaminos("JFK", "LAX", null, 10).getCaminos().size()==2);
        assertTrue(grafo.todosLosCaminos("LAX", "MIA", null, 10).getCaminos().isEmpty());
        
        
        //PARA CARGA DE DATOS
//        Aerolinea aerolinea1 = new Aerolinea("AA","American Airlines");
//        Aerolinea aerolinea2 = new Aerolinea("BJ","BlueJet");
//        
//        RutaAerea ruta1 = new RutaAerea("AA","JFK","LAX",150.0);
//        RutaAerea ruta2 = new RutaAerea("AA","JFK","MIA",100.0);
//        RutaAerea ruta3 = new RutaAerea("AA","MIA","LAX",40.0);
//        RutaAerea ruta4 = new RutaAerea("BJ","JFK","LAX",210.0);
//        RutaAerea ruta5 = new RutaAerea("BJ","MIA","LAX",70.0);
    }
    
}
