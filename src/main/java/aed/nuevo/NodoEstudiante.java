package aed.nuevo;

import java.util.ArrayList;

public class NodoEstudiante {

    char valor;
    boolean ultimaletra;//ultima, no una despues
    ArrayList<NodoEstudiante> hijos;
    int materias;

    public NodoEstudiante() {//raiz
        valor = ' ';
        ultimaletra = false;
        hijos = new ArrayList<>();
        materias = 0; 
    }
    public NodoEstudiante(char v) {//otro
        valor = v;
        ultimaletra = false;
        hijos = new ArrayList<>();
        materias = 0; 
    }
    public int get_mat()
    {
        return materias;
    }
    public NodoEstudiante getHijo(char ch) {
        for (NodoEstudiante hijo : hijos) {
            if (hijo.valor == ch) {
                return hijo;
            }
        }
        return null;
    }

    public void addHijo(NodoEstudiante hijo) {
        int pos = 0;
        while (pos < hijos.size() && hijos.get(pos).valor < hijo.valor) {
            pos++;
        }
        hijos.add(pos, hijo);
    }

}
