package Tries;

import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        
            raiz.insertar(filtrarPalabra(palabra));
        
    }
    
    	public static String filtrarPalabra(String unaPalabra) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < unaPalabra.length(); i++) {
			char caracter = unaPalabra.charAt(i);
			if ((caracter >= 'A' && caracter <= 'Z') ||
					(caracter >= 'a' && caracter <= 'z'))
				sb.append(caracter);
		}
		
		return sb.toString().toLowerCase();
        }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> palabras = null;
        if (raiz == null) {
            return palabras;
        } else {
            palabras = new LinkedList<>();
            TNodoTrie nodo = raiz.buscar_prefijo(filtrarPalabra(prefijo));
            raiz.predecir(filtrarPalabra(prefijo), palabras, nodo);
            return palabras;
        }
    }

}
