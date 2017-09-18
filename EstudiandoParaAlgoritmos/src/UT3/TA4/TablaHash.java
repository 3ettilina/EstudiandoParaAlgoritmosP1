/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT3.TA4;

/**
 *
 * @author andres
 */
public class TablaHash {
    
    private double factorDeCarga;
    public double getFactorDeCarga() {
        return factorDeCarga;
    }

    private int[] tabla;

    public int[] getTabla() {
        return tabla;
    }

    public TablaHash(int noClavesAInsertar, double factorDeCargaDeLaTabla) {
        factorDeCarga = factorDeCargaDeLaTabla;
        int valor = (int)(noClavesAInsertar / factorDeCarga);
        tabla = new int[valor];      
    }

    public int funcionHashing(int unaClave) {
        if (unaClave < 0){
            return ((unaClave + (int) Math.pow(2, 31))%tabla.length);            
        }
        else{
            return unaClave%tabla.length;
        }
    }

    public boolean estaCompleto() {
        for (int i = 0; i < this.tabla.length; i++) {
            if (tabla[i] == -1) {
                return false;
            }
        }
        return true;

    }

    public boolean insertar(int unaClave) {

        boolean bandera = this.estaCompleto();
        int contador = 0;
        int unaPosicion = this.funcionHashing(unaClave);
        if (!bandera) {
            while (tabla[unaPosicion] != -1) {
                contador++;
                unaPosicion++;
                if (unaPosicion == tabla.length) {
                    unaPosicion = 0;
                }
            }
            tabla[unaPosicion] = unaClave;
            contador++;
        }
        System.out.println("Se hicieron " + contador + " comparaciones.");
        return !bandera;

    }

    public String imprimir() {
        String mensaje = "";
        for (int i = 0; i < tabla.length; i++) {
            mensaje += "La posicion " + (i + 1) + " contiene " + tabla[i] + "\n";
        }
        return mensaje;
    }

    public boolean buscar(int unaClave) {

        int contador = 0;
        int unaPosicion = this.funcionHashing(unaClave);
        int posicionFinal = tabla.length - 1;
        if (unaPosicion > 0) {
            posicionFinal = unaPosicion - 1;
        }
        while (unaPosicion != posicionFinal && tabla[unaPosicion] != unaClave) {
            contador++;
            unaPosicion++;
            if (unaPosicion == tabla.length) {
                unaPosicion = 0;
            }
        }
        System.out.println("Se hicieron " + contador + " comparaciones.");

        return tabla[unaPosicion] == unaClave;
    }
    
    public int insertarCuad(int unaClave){
        int i = 0;
        int j = this.funcionHashing(unaClave);
                
        while (i <= (tabla.length)-1){
            if (this.tabla[(j + (i^2))%this.tabla.length] == 0){
                this.tabla[(j + (i^2))%this.tabla.length] = unaClave;
                return i;
            }
            else{
                i++;
            }
        }
        System.out.println("NO SE INSERTO "+unaClave);
        return -i;
    }
    
    public int buscarCuad(int unaClave){
        int i = 0;
        int j = this.funcionHashing(unaClave);
                
        while (i <= (tabla.length)-1){
            if ((this.tabla[(j + (i^2))%this.tabla.length] != 0) && (this.tabla[(j + (i^2))%this.tabla.length] == unaClave)){
                return i;
            }
            else{
                i++;
            }
        }
        return -i;
    }
    
}
