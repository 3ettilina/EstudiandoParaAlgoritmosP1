package CodigoHash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bettina
 */
public class HashPiola {

    String[] vectorHash;
    int tamanioTabla;
    double factorCarga;
    int contadorClaves = 0;

    public HashPiola(int cantClaves, double facCarga) {
        int cantPosiciones = (int) (cantClaves / facCarga);
        cantPosiciones = proximoPrimo(cantPosiciones);
        vectorHash = new String[cantPosiciones];
        tamanioTabla = cantPosiciones;
        factorCarga = facCarga;
    }

    /**
     *
     * @param clave
     * @return
     */
    public int insertarHashSimple(int clave) {
        int i = 0;
        int contador = 0;
        int j = this.funcionHash((clave));

        while (vectorHash[j] == null || i == tamanioTabla) {
            if (vectorHash[j] == null) {
                contadorClaves++;
                factorCarga = contadorClaves / this.tamanioTabla;
                return contador;
            } else {
                i++;
            }
            contador++;
        }

        return contador;
    }

    /**
     *
     * @param unChar
     * @return
     */
    public int insertarHash(char unChar) {
        return insertarHashSimple(unChar % tamanioTabla);
    }

    public int insertarHashCuadratico(int clave) {
        int contador = 0;
        int posicionOriginal = this.funcionHash(clave);
        int posicionEnHash = posicionOriginal;
        boolean insertado = false;

        if (vectorHash[posicionOriginal] == null) {
            vectorHash[posicionOriginal] = String.valueOf(clave);
            insertado = true;
            contadorClaves++;
            factorCarga = contadorClaves / this.tamanioTabla;
            contador++;
        } else {
            while (contador <= 5) {
                posicionEnHash = (int) ((posicionOriginal + Math.pow(contador, 2)) % this.tamanioTabla);
                contador++;

                if (vectorHash[posicionEnHash] == null) {
                    vectorHash[posicionEnHash] = String.valueOf(clave);
                    insertado = true;
                    contadorClaves++;
                    factorCarga = contadorClaves / this.tamanioTabla;
                    break;
                }
            }
        }

        if (insertado) {
            return contador;
        } else {
            return -(contador);
        }

    }

    /**
     * Funcion que devuelve el index en el cual se insertara una clave.
     *
     * @param clave
     * @return Modulo entre la clave que se le pasa por parametro y el tamanio
     * del vectorHash.
     */
    public int funcionHash(int clave) {
        return clave % tamanioTabla;
    }

    /**
     * La función consiste en tomar el último caracter de la palabra, asignarle
     * un valor numérico según su posición en el alfabeto y efectuar la división
     * entera entre diez para utilizar el resto de la misma como código.
     *
     * @param clave Palabra solicitada.
     * @return Devuelve el hashcode de la palabra solicitada.
     */
//    public int funcionHash2(int clave){
//        return ((clave.toLowerCase().codePointAt(clave.length()-1)-96)%10);
//        
//    }
    /**
     * Metodo que devuelve el proximo numero primo de un valor dado.
     *
     * @param aPartirDe
     * @return
     */
    public int proximoPrimo(int valorAct) {
        int proxPrim = valorAct++;
        for (int i = 2; i < proxPrim; i++) {
            if (proxPrim % i == 0) {
                proxPrim++;
                i = 2;
            } else {
                continue;
            }
        }

        return proxPrim;
    }

    public String buscarHashSimple(int clave) {
        String[] v = ManejadorArchivosGenerico.leerArchivo("src/claves_buscar.txt");
        int i = 0;
        boolean encontrado = false;
        int comparaciones = 0;
        int n = 10;
        while (i < n && encontrado == false) {
            if (v[i].equals(clave)) {
                encontrado = true;
                return v[i];
            } else {
                i += 1;
            }
            comparaciones += 1;
        }
        return "";
    }

    public int buscarHashSimpleComparaciones(int clave) {
        String[] v = ManejadorArchivosGenerico.leerArchivo("src/claves_buscar.txt");
        int i = 0;
        boolean encontrado = false;
        int comparaciones = 0;
        int n = 10;

        while (i < n && encontrado == false) {
            if (v[i].equals(clave)) {
                encontrado = true;
            } else {
                i += 1;
            }
            comparaciones += 1;
        }

        if (encontrado) {
            return comparaciones;
        } else {
            return -(comparaciones);
        }

    }

    public int buscarHashCuadratico(int clave) {
        int contador = 0;
        int posicionOriginal = this.funcionHash(clave);
        int posicionEnHash = posicionOriginal;
        boolean encontrado = false;

        if (vectorHash[posicionOriginal] == null) {
            vectorHash[posicionOriginal] = String.valueOf(clave);
            contador++;
        } else {
            while (contador <= 5) {
                posicionEnHash = (int) ((posicionOriginal + Math.pow(contador, 2)) % this.tamanioTabla);
                contador++;

                if (vectorHash[posicionEnHash] != null) {
                    if (Integer.parseInt(vectorHash[posicionEnHash]) == clave) {
                        encontrado = true;
                        break;
                    }
                }
            }
        }

        if (encontrado) {
            return contador;
        } else {
            return -(contador);
        }
    }

    public int buscarHashCuadraticoComparaciones(int clave) {
        String[] v = ManejadorArchivosGenerico.leerArchivo("src/claves_buscar.txt");
        int i = 0;
        boolean encontrado = false;
        int comparaciones = 0;
        int n = 10;

        while (i < n && encontrado == false) {
            if (v[i].equals(clave)) {
                encontrado = true;
            } else {
                i += 1;
            }
            comparaciones += 1;
        }

        if (encontrado) {
            return comparaciones;
        } else {
            return -(comparaciones);
        }
    }
}
