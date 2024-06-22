package aed;

import java.util.ArrayList;

// Invariante de Representacion: para todo nodo, nodo cumple NodoCarreras y para todo hijo perteneciente a nodo.hijos, hijo cumple NodoCarreras.
// para todo nodo, nodo.valor es un char.
// para todo nodo, nodo.ultimaletra = false entonces nodo.materiastrie = null.
// para todo nodo, nodo.ultimaletra = true entonces nodo es hoja y nodo.materiastrie es didtinto de null y nodo.materiastrie cumple Materia y nodo.hijos es vacia.

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

    public void sacar_materia(String materia){//ddelete en materias trie
        materiastrie.sacar(materia);
    }

    public NodoCarreras getHijo(char c) {
        for (NodoCarreras child : hijos) {
            if (child.valor == c) {
                return child;
            }
        }
        return null;
    }

    public void addHijo(NodoCarreras hijo) {
        int pos = 0;
        while (pos < hijos.size() && hijos.get(pos).valor < hijo.valor) {
            pos++;
        }
        hijos.add(pos, hijo);
    }
    public Materia getMaterias(){
        return materiastrie;
    }
}
