/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolesGenericos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bettina
 */
public class TNodoAGTest {
    
    TArbolGen aGenerico;
    
    
    public TNodoAGTest() {
        aGenerico  = new TArbolGen();
        
    }
    
    

    @Test
    public void testInsertar() {
        boolean insertado = aGenerico.insertar("UCU", null);
        assertEquals(insertado, true);
        
        boolean reInsertado = aGenerico.insertar("UCU", null);
        assertEquals(reInsertado, false);
        
    }
    
    @Test
    public void testBuscar() {
        TArbolGen arbG = new TArbolGen();
        arbG.insertar("Feria", null);
        arbG.insertar("Frutas", "Feria");
        arbG.insertar("Verduras", "Feria");
        arbG.insertar("Tomate", "Fruta");
        
        
        TNodoAG encontrado = arbG.buscar("Feria");
        assertNotNull(encontrado);
        
    }
    
}
