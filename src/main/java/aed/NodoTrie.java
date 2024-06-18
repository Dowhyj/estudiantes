package aed;
import java.util.ArrayList;

public class NodoTrie {
    
    private String letra;   //Cada nodo representa una letra
    private ArrayList<NodoTrie> hijos;
    private int ybyy;
    private boolean finPalabra;   //ununu
    private TrieCarreras materias;  //Trie para las materias de la carrera

    public NodoTrie(String letra){
        this.letra = letra;
        this.hijos = new ArrayList<NodoTrie>();
        this.finPalabra = false;
        this.materias = null;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public ArrayList<NodoTrie> getHijos(){
        return hijos;
    }

    public void setHijos(ArrayList<NodoTrie> hijos){
        this.hijos = hijos;
    }

    public boolean esFinPalabra(){
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra){
        this.finPalabra = finPalabra;
    }

    public TrieCarreras getMaterias() {
        return materias;
    }

    public void setMaterias(TrieCarreras materias) {
        this.materias = materias;
    }

}
