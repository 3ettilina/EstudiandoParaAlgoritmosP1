package parcial2.grafonodirigido;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCaminos {

    private LinkedList<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }

    public String imprimirCaminos() {
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos) {
            sb.append(camino.imprimirEtiquetas() + "\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola() {
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }

    public TCamino obtenerCaminoMasCaro() {
        Double max = -1.0;
        TCamino cami = null;
        for (TCamino camino : caminos) {
            if (max < camino.getCostoTotal()) {
                max = camino.getCostoTotal();
                cami = camino;
            }
        }
        return cami;
    }
}
