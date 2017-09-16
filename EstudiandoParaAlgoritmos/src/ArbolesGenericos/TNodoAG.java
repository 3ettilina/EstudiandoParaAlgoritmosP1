/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolesGenericos;

/**
 *
 * @author NicoPlaceres
 */
public class TNodoAG {
    
    private Comparable etiqueta;
    private Comparable etiquetaPadre;
    private TNodoAG primerHijo;
    private TNodoAG sigHermano;

    public TNodoAG(Comparable etiqueta, Comparable etiquetaPadre) {
        this.etiqueta = etiqueta;
        this.etiquetaPadre = etiquetaPadre;
        this.primerHijo = null;
        this.sigHermano = null;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public Comparable getEtiquetaPadre() {
        return etiquetaPadre;
    }

    public TNodoAG getPrimerHijo() {
        return primerHijo;
    }

    public TNodoAG getSigHermano() {
        return sigHermano;
    }
    
    
    
    public boolean insertar (Comparable unaEtiqueta, Comparable unaEtiquetaPadre){
        boolean resultado = false;
        
        if (unaEtiquetaPadre.compareTo(this.etiqueta)==0){
            if(this.primerHijo == null){
                TNodoAG hijo = new TNodoAG(unaEtiqueta, unaEtiquetaPadre);
                this.primerHijo = hijo;
                return true;
            }else{
                TNodoAG hijoTemp = this.primerHijo;
                TNodoAG hijoAnterior = null;
                while (hijoTemp != null){
                    if ( hijoTemp.etiqueta == unaEtiqueta){
                        return false; //El elemento había sido ingresado con anterioridad
                    }
                    hijoAnterior = hijoTemp; //Mantengo una referencia al ultimo hijo
                    hijoTemp = hijoTemp.sigHermano;
                }
                TNodoAG nuevoHijo = new TNodoAG(unaEtiqueta, unaEtiquetaPadre);
                hijoAnterior.sigHermano = nuevoHijo;
                return true;
            }
        }else{
            TNodoAG unHijo = this.primerHijo;
            while (unHijo != null){
                resultado = unHijo.insertar(unaEtiqueta, unaEtiquetaPadre);
                if (resultado == true){
                    return resultado;
                }else{
                    unHijo = unHijo.sigHermano;
                }
            }
        }
        return resultado;
    }
    
    public TNodoAG buscar(Comparable unaEtiqueta){
        TNodoAG resultado = null;
        if(this.etiqueta == unaEtiqueta){
            resultado = this;
            return resultado;
        }else{
            TNodoAG unHijo = this.primerHijo;
            while (unHijo != null){
                resultado = unHijo.buscar(unaEtiqueta);
                if (resultado == null){
                    unHijo = unHijo.sigHermano;
                }else{
                    return resultado;
                }
            }
            return resultado;
        }
    }
    
    public void listarIndentado(int profundidad, int[] maxProfundidad){
        if (profundidad > maxProfundidad[0]){
            maxProfundidad[0]= profundidad; //Solo se actualiza cuando aumenta la profundidad.
        }
        String indentacion = "";
        for (int i = 0; i < profundidad; i++) {
            indentacion += "\t";            
        }        
        System.out.println(indentacion + this.etiqueta );
        TNodoAG unHijo = this.primerHijo;
        while (unHijo != null){            
            unHijo.listarIndentado(profundidad+1, maxProfundidad);
            unHijo = unHijo.sigHermano;
        }
        
    }
    
    public void sangria(){
        int[] max = new int[1];
        int prof = 0;
        listarIndentado(prof,max);
    
    }
    
    public void imprimir() {
       TNodoAG aux = primerHijo;
        while (aux != null) {
            System.out.println(aux.getEtiqueta());
            aux.imprimir();
            aux = aux.getSigHermano();  
        }
        
    }
    
    
}
