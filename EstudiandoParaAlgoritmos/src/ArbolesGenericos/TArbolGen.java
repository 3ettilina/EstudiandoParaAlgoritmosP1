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
public class TArbolGen {
    
    

    
    private TNodoAG raiz;

    /**
     * Constructor por defecto.
     */
    public TArbolGen() {
    }

    /**
     * Sobrecarga del constructor.
     * @param raiz Recibe un TNodoAG, que se asigna la raiz.
     */
    public TArbolGen(TNodoAG raiz) {
        this.raiz = raiz;
    }

    /**
     * Getter del atributo raiz.
     * @return
     */
    public TNodoAG getRaiz() {
        return raiz;
    }

    /**
     *Setter del atributo raiz.
     * @param raiz
     */
    public void setRaiz(TNodoAG raiz) {
        this.raiz = raiz;
    }
    
    public boolean insertar (Comparable etiqueta, Comparable etiquetaPadre){
        if (raiz == null && etiquetaPadre == null){
            TNodoAG nodo = new TNodoAG(etiqueta, etiquetaPadre);
            this.raiz = nodo;
            return true;
        }else if (raiz == null && etiquetaPadre != null){
            return false; // La raiz no puede tener padre.
        }else if (raiz != null && etiquetaPadre == null){
            return false;// Ya existe raiz, y por tanto no se puede ingresar una nueva
        }else{
            return raiz.insertar(etiqueta, etiquetaPadre);
        }       
    }
    
    public TNodoAG buscar (Comparable unaEtiqueta){
        if(raiz == null){
            return null;
        }else{
            return raiz.buscar(unaEtiqueta);
        }
    }
    
    public int[] listarIndentado (){
        int profundidad = 0;
        int maxProfundidad [] = {-1};        
        if (raiz == null){
            System.out.println("El árbol está vacío");
        }else{ 
            maxProfundidad[0] = 0;
            raiz.listarIndentado(profundidad, maxProfundidad);
        }
        return maxProfundidad;
    }
    
    public void sangria(){
        if(raiz != null){
            raiz.sangria();
        }
    }
    
    
}
