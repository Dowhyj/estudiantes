package aed.mio;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    NodoMaterias raiz;

    public Materia(){
        raiz = new NodoMaterias();
    }


    public NodoMaterias insert(String pal,ArrayList<NodoCarreras> donde) {
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
                node.hijos.add(newhijo);
                node = newhijo;
            }else{
                node = n;
            }
         
        }
        node.ultimaletra = true;
        node.carreras = donde;
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
    public String[] getAllWords() {
        List<String> words = new ArrayList<>();
        collectWords(raiz, "", words);
        return words.toArray(new String[0]);
    }

    private void collectWords(NodoMaterias node, String prefix, List<String> words) {
        if (node.ultimaletra) {
            words.add(prefix);
        }
        for (NodoMaterias child : node.hijos) {
            collectWords(child, prefix + child.valor, words);
        }
    }

}
