package aed;

import java.util.ArrayList;

public class Carreras{

    private Nodo raiz;
    private int tamanio;

    private class Nodo{
        char valor;
        String NombreCarrera;
        ArrayList<Nodo> siguientes;
        Nodo Padre;
        Materias Materias;


        Nodo(char valor){
        this.valor = valor;
        this.NombreCarrera = null;
        this.siguientes = new ArrayList<>();
        this.Materias = null;
        this.Padre = null;
        }

        // Metodo para obtener el nodo hijo segun un caracter dado 'c'
        Nodo getSiguientes(char c) {
            for (Nodo hijo : siguientes) {
                if (hijo.valor == c) {
                    return hijo;
                }else if(hijo.valor > c) {
                    return null;
                }
            }
            return null;
        }
        

        //Metodo para agregar un nodo hijo de manera ordenada
        void agregarHijo(Nodo nodoHijo) {
            int i = 0;
            while (i < siguientes.size() && siguientes.get(i).valor < nodoHijo.valor) {
                i++;
            }
            this.siguientes.add(i,nodoHijo);
        }


        void eliminarHijo(Nodo hijo){
            siguientes.remove(hijo);
        }
    }

    public Carreras() {
        this.raiz = new Nodo('\0');
        this.tamanio = 0;
    }


    public void Insertar(String Pal){
        Nodo nodo = raiz;
        for(char c : Pal.toCharArray()){
            Nodo hijo = nodo.getSiguientes(c);
            if (hijo == null) {
                hijo = new Nodo(c);
                nodo.agregarHijo(hijo);
                hijo.Padre = nodo;
            }
            nodo = hijo;
        }
        nodo.NombreCarrera = Pal;
        if (nodo.Materias == null) {
            nodo.Materias = new Materias();
            tamanio++;
        }
    }

    public boolean pertenece(String Pal){
        Nodo nodo = raiz;
        for(char c : Pal.toCharArray()){
            Nodo hijo = nodo.getSiguientes(c);
            if (hijo == null) {
                return false;
            }
            nodo = hijo;
        }
        return nodo.NombreCarrera != null;
    }
    
    public void eliminar(String Pal){
        Nodo nodo = raiz;
        Nodo hijo = null;
        for(char c : Pal.toCharArray()){
            hijo = nodo.getSiguientes(c);
            if (hijo == null) {
                return;
            }
            nodo = hijo;
        }
        if (hijo.siguientes.size() == 0) {
            hijo.NombreCarrera = null;
            eliminar1(hijo);
        }else{
            hijo.NombreCarrera = null;
        }
        tamanio--;
    }

    public void eliminar1(Nodo hoja){
        if (hoja == raiz) {
            return;
        }
        if (hoja.NombreCarrera != null) {
            return;
        }
        if (hoja.Padre.siguientes.size() > 1) {
            hoja.Padre.eliminarHijo(hoja);
            return;
        }else{
            hoja.Padre.eliminarHijo(hoja);
            eliminar1(hoja.Padre);
        }
    }

    public Materias buscarTrieMater(String Pal){
        Nodo nodo = raiz;
        for(char c : Pal.toCharArray()){
            Nodo hijo = nodo.getSiguientes(c);
            if (hijo == null) {
                return null;
            }
            nodo = hijo;
        }
        return nodo.Materias;
    }

    public int tamanio(){
        return tamanio;
    }

}
