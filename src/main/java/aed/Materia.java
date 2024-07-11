package aed;

import java.util.ArrayList;
import java.util.List;
//Invariante de Representacion: raiz es distinto de null y raiz cumple el invariante de NodoMateria y 
//los hijos de la raiz estan ordenados lexicograficamente y no puede tener hijos iguales.
// Todo nodo que no tiene significado siempre tiene hijos
// Todo nodo que no tiene hijos siempre tiene significado
public class Materia {
    NodoMaterias raiz;

    public Materia(){
        raiz = new NodoMaterias();
    }

    public NodoMaterias insert(String pal,ArrayList<NodoCarreras> donde,ArrayList<NodoMaterias> otrosnombres,ArrayList<String> nombres) {
        NodoMaterias node = raiz;
        for (char ch : pal.toCharArray()) {
            NodoMaterias n = null;
            for (NodoMaterias hijo : node.hijos){
                if(hijo.valor == ch){
                    n = hijo;
                    break;
                }
            }
            if(n == null){
                NodoMaterias newhijo = new NodoMaterias(ch);
                node.addHijo(newhijo);
                node = newhijo;
            }else{
                node = n;
            }
         
        }
        node.ultimaletra = true;
        node.carreras = donde;
        node.otrosnombres = otrosnombres;
        node.inscriptos = 0;
        node.nombres = nombres;
        return node;
    }  

    public NodoMaterias buscar(String pala){
        NodoMaterias nodo = raiz;
        for (char ch : pala.toCharArray()) 
        {
            NodoMaterias posible = null;
            for (NodoMaterias hijo : nodo.hijos)
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
    public boolean sacar(String palabra) {
        return borrar(raiz, palabra, 0);
    }
    private boolean borrar(NodoMaterias actual, String palabra, int index) {

        if (index == palabra.length()) {
            if (!actual.ultimaletra) {
                return false;
            }
            actual.ultimaletra = false;
            return actual.hijos.isEmpty();
        }
        char ch = palabra.charAt(index);
        NodoMaterias hijo = actual.getHijo(ch);
        if (hijo == null) {
            return false;
        }
        boolean hayqueborrar = borrar(hijo, palabra, index + 1);
        if (hayqueborrar) {
            actual.removeHijo(ch);
            return actual.hijos.isEmpty() && !actual.ultimaletra;
        }
        return false;
    }
    
    private void getmaterias(NodoMaterias node, String prefix, List<String> words) {
        if (node.ultimaletra) {
            words.add(prefix);
        }
        for (NodoMaterias child : node.hijos) {
            getmaterias(child, prefix + child.valor, words);
        }
    }  
    public String[] get_materias() {
        List<String> words = new ArrayList<>();
        getmaterias(raiz, "", words);
        return words.toArray(new String[0]);
    }

}
