/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juanca
 */
public class TClasificadorTest {

    GeneradorDatosGenericos gdg;
    TClasificador clasificador;
    int[] elementosOrdenadosAscendentemente;
    int[] elementosOrdenadosAleatoriamente;
    int[] elementosEspeciales;

    public TClasificadorTest() {
        gdg = new GeneradorDatosGenericos();
        clasificador = new TClasificador();
    }

    @Before
    public void setUp() {
        elementosOrdenadosAscendentemente = gdg.generarDatosAscendentes(1000);
        elementosOrdenadosAleatoriamente = gdg.generarDatosAleatorios(1000);
        elementosEspeciales = gdg.generarDatosEspeciales();
    }

    /**
     * Test of ordenarPorInsercion method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorInsercion() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorInsercion(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorShell method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorShell() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorShell(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorBurbuja method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorBurbuja() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorBurbuja(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorQuickSort method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorQuickSort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorQuickSort(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorHeapsort method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorHeapsort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorHeapsort(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorSeleccionDirecta method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorSeleccionDirecta() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorSeleccionDirecta(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorArraysSort method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorArraysSort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorArraysSort(elementosOrdenadosAleatoriamente));
    }

    /**
     * Test of ordenarPorCuentasPorDistribucion method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorCuentasPorDistribucion() {
        int[] arrayOrdenados = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        Assert.assertArrayEquals(arrayOrdenados, clasificador.ordenarPorCuentasPorDistribucion(elementosEspeciales));
    }

    /**
     * Test of ordenarPorBucketSort method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorBucketSort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorBucketSort(elementosOrdenadosAleatoriamente));
    }
    
    @Test
    public void testOrdenarPorBinSort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.ordenarPorBinSort(elementosOrdenadosAleatoriamente));
    }

    @Test
    public void testOrdenarPorRadixSort() {
        Assert.assertArrayEquals(elementosOrdenadosAscendentemente, clasificador.radixSort(elementosOrdenadosAleatoriamente));
    }
}
