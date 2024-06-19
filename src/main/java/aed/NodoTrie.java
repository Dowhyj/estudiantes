package aed;
import java.util.ArrayList;

public class NodoTrie {
    
    private char caracter;   //Cada nodo representa un caracter
    private ArrayList<NodoTrie> hijos;
    private boolean finPalabra;
    private TrieCarreras materias;  //Trie para las materias de la carrera
    private int cantMaterias;

    public NodoTrie(char caracter){
        this.caracter = caracter;
        this.hijos = new ArrayList<NodoTrie>();
        this.finPalabra = false;
        this.materias = null;
        this.cantMaterias = 0;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
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

    public int getCantMaterias() {
        return cantMaterias;
    }

    public void setCantMaterias(int cantMaterias) {
        this.cantMaterias = cantMaterias;
    }

    public void incrementarCantMaterias() {
        this.cantMaterias++;
    }

}
