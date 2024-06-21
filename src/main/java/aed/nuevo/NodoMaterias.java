package aed.nuevo;

import java.util.ArrayList;

public class NodoMaterias {
     
    char valor;
    boolean ultimaletra;//ultima, no una despues
    ArrayList<NodoMaterias> hijos;
    ArrayList<NodoCarreras> carreras;

    public NodoMaterias() {//raiz
        valor = ' ';
        ultimaletra = false;
        hijos = new ArrayList<>();
        carreras = null; 
    }
    public NodoMaterias(char v) {//otro
        valor = v;
        ultimaletra = false;
        hijos = new ArrayList<>();
        carreras = null; 
    }

    public NodoMaterias getHijo(char c) {
        for (NodoMaterias child : hijos) {
            if (child.valor == c) {
                return child;
            }
        }
        return null;
    }
}
