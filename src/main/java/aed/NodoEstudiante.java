package aed;

import java.util.ArrayList;

public class NodoEstudiante {
    private char caracter;
    private ArrayList<NodoEstudiante> hijos;
    private boolean finLibreta;
    private int cantMaterias;

    public NodoEstudiante(char caracter){
        this.caracter = caracter;
        this.hijos = new ArrayList<NodoEstudiante>();
        this.finLibreta = false;
        this.cantMaterias = 0;
    }

    public char getCaracter(){
        return caracter;
    }

    public ArrayList<NodoEstudiante> getHijos(){
        return hijos;
    }

    public boolean esFinLibreta(){
        return finLibreta;
    }

    public void setFinLibreta(boolean finLibreta){
        this.finLibreta = finLibreta;
    }

    public int getCantMaterias(){
        return cantMaterias;
    }

    public void setCantMaterias(int cantMaterias){
        this.cantMaterias = cantMaterias;
    }

    public void incrementarMaterias(int cant){
        this.cantMaterias += cant;
    }
}
