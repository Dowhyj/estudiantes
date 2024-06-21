package aed.mio;

import java.util.ArrayList;

public class NodoCarreras {
    
    char valor;
    boolean ultimaletra;//ultima, no una despues
    ArrayList<NodoCarreras> hijos;
    Materia materiastrie;

    public NodoCarreras() {//raiz
        valor = ' ';
        ultimaletra = false;
        hijos = new ArrayList<>();
        materiastrie = null; 
    }
    public NodoCarreras(char v) {//otro
        valor = v;
        ultimaletra = false;
        hijos = new ArrayList<>();
        materiastrie = null; 
    }

    public NodoCarreras getHijo(char c) {
        for (NodoCarreras child : hijos) {
            if (child.valor == c) {
                return child;
            }
        }
        return null;
    }
}
