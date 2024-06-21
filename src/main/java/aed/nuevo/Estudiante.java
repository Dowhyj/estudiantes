package aed.nuevo;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    NodoEstudiante raiz;

    public Estudiante(){
        raiz = new NodoEstudiante();
    }

    public NodoEstudiante insert(String pal) {
        NodoEstudiante node = raiz;
        for (char ch : pal.toCharArray()) {
            NodoEstudiante n = null;
            for (NodoEstudiante hijo : node.hijos){
                if(hijo.valor == ch){
                    n = hijo;
                    break;
                }
            }
            if(n == null){
                NodoEstudiante newhijo = new NodoEstudiante(ch);
                node.addHijo(newhijo);
                node = newhijo;
            }else{
                node = n;
            }
         
        }
        node.ultimaletra = true;
        node.materias = 0;
        return node;
    }  

    public NodoEstudiante buscar(String pala){
        NodoEstudiante nodo = raiz;
        for (char ch : pala.toCharArray()) 
        {
            NodoEstudiante posible = null;
            for (NodoEstudiante hijo : nodo.hijos)
            {
                if(hijo.valor == ch)posible = hijo;
            }
            if(posible == null){
                return null;
            }else{
                nodo = posible;
            }
        }
        return nodo;
    }

    public void inscribir(String estudiante){
        NodoEstudiante node = buscar(estudiante);
        node.materias += 1;
    }
    public void desinscribir(String estudiante){
        NodoEstudiante node = buscar(estudiante);
        node.materias -= 1;
    }
    public int materias_inscriptas(String estudiante){
        return buscar(estudiante).materias;
    }

      private void getinscriptos(NodoEstudiante node, String prefix, List<String> words) {
        if (node.ultimaletra) {
            words.add(prefix);
        }
        for (NodoEstudiante child : node.hijos) {
            getinscriptos(child, prefix + child.valor, words);
        }
    }  
    public String[] get_inscriptos() {
        List<String> words = new ArrayList<>();
        getinscriptos(raiz, "", words);
        return words.toArray(new String[0]);
    }
}
