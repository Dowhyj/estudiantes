package aed.mio;

import java.util.ArrayList;
import java.util.List;

public class Carreras {
    
    NodoCarreras raiz;


    public Carreras(){
        raiz = new NodoCarreras();
    }

    public NodoCarreras insert(String pal) {
        NodoCarreras node = raiz;
        for (char ch : pal.toCharArray()) {
            NodoCarreras n = null;
            for (NodoCarreras hijo : node.hijos){
                if(hijo.valor == ch){
                    n = hijo;
                    break;
                }
            }
            if(n == null){
                NodoCarreras newhijo = new NodoCarreras(ch);
                node.hijos.add(newhijo);
                node = newhijo;
            }else{
                node = n;
            }
         
        }
        node.ultimaletra = true;
        node.materiastrie = new Materia();
        return node;
    }  

    public NodoCarreras buscar(String pala){
        NodoCarreras nodo = raiz;
        for (char ch : pala.toCharArray()) 
        {
            NodoCarreras posible = null;
            for (NodoCarreras hijo : nodo.hijos)
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

    public void insertmat(NodoCarreras ult, String materia,ArrayList<NodoCarreras> donde){
       ult.materiastrie.insert(materia,donde);

    }
    public String[] getmats(String car){
        NodoCarreras awn = buscar(car);
        return awn.materiastrie.getAllWords();
    }
    public String[] getAllWords() {
        List<String> words = new ArrayList<>();
        collectWords(raiz, "", words);
        return words.toArray(new String[0]);
    }

    private void collectWords(NodoCarreras node, String prefix, List<String> words) {
        if (node.ultimaletra) {
            words.add(prefix);
        }
        for (NodoCarreras child : node.hijos) {
            collectWords(child, prefix + child.valor, words);
        }
    }








}
