package aed;

public class TrieCarreras{

    private NodoTrie raiz;

    public TrieCarreras(){
        raiz = new NodoTrie("");
    }

    public void insertarCarrera(String carrera) {
        NodoTrie nodoActual = raiz;
        for (char c : carrera.toCharArray()) {
            NodoTrie hijo = buscarHijo(nodoActual, c);
            if (hijo == null) {
                hijo = new NodoTrie(Character.toString(c));
                nodoActual.getHijos().add(hijo);
            }
            nodoActual = hijo;
        }
        nodoActual.setFinPalabra(true);
    }

    private NodoTrie buscarHijo(NodoTrie nodo, char c){
        for(NodoTrie hijo: nodo.getHijos()){
            if (hijo.getLetra().equals(Character.toString(c))) {
                return hijo;
            }
        }
        return null;
    }

    public boolean buscarCarrera(String carrera){
        NodoTrie nodoActual = raiz;
        for(char c: carrera.toCharArray()){
            NodoTrie hijo = buscarHijo(nodoActual, c);
            if (hijo == null) {
                return false;
            }
            nodoActual = hijo;
        }
        return nodoActual.esFinPalabra();
    }

    public void insertarMateria(String carrera, String materia){
        NodoTrie nodoCarrera = encontrarNodoCarrera(carrera);
        if (nodoCarrera != null && nodoCarrera.esFinPalabra()) {
            if (nodoCarrera.getMaterias() == null) {
                nodoCarrera.setMaterias(new TrieCarreras());
            }    
            nodoCarrera.getMaterias().insertarCarrera(materia);         
        }
    }

    private NodoTrie encontrarNodoCarrera(String carrera){
        NodoTrie nodoActual = raiz;
        for(char c: carrera.toCharArray()){
            NodoTrie hijo = buscarHijo(nodoActual, c);
            if (hijo == null) {
                return null;
            }
            nodoActual = hijo;
        }
        return nodoActual;
    }

    public boolean buscarMateria(String carrera, String materia) {
        NodoTrie nodoCarrera = encontrarNodoCarrera(carrera);
        if (nodoCarrera != null && nodoCarrera.esFinPalabra() && nodoCarrera.getMaterias() != null) {
            return nodoCarrera.getMaterias().buscarCarrera(materia);
        }
        return false;
    }
}
