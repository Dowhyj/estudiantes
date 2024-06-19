package aed;

public class TrieEstudiantes {
    
    private NodoTrie raiz;

    public TrieEstudiantes(){
        this.raiz = new NodoTrie('\0');
    }

    public void insertarEstudiante (String numLibreta){
        NodoTrie nodoActual = raiz;
        for(char c: numLibreta.toCharArray()){
            NodoTrie hijo = buscarHijo(nodoActual, c);
            if (hijo == null) {
                hijo = new NodoTrie(c);
                nodoActual.getHijos().add(hijo);
            }
            nodoActual = hijo;
        }
        nodoActual.setFinPalabra(true);
    }

    private NodoTrie buscarHijo(NodoTrie nodo, char c){
        for(NodoTrie hijo: nodo.getHijos()){
            if (hijo.getCaracter()==c) {
                return hijo;
            }
        }
        return null;
    }

    public void inscribirMateria(String numLibreta){
        NodoTrie nodoActual = raiz;
        for(char c: numLibreta.toCharArray()){
            NodoTrie hijo = buscarHijo(nodoActual, c);
            nodoActual = hijo;
        }
        if (nodoActual.esFinPalabra()) {
            nodoActual.incrementarCantMaterias();
        }
        else{
            nodoActual.setFinPalabra(true);
            nodoActual.setCantMaterias(1);
        }
    }

    public Integer obtenerCantMaterias(String numLibreata){
        NodoTrie nodoActual = raiz;
        for(char c: numLibreata.toCharArray()){
            NodoTrie hijo = buscarHijo(nodoActual, c);
            if (hijo == null) {
                return null;    //Num libreta no existe
            }
            nodoActual = hijo;
        }
        if (nodoActual.esFinPalabra()) {
            return nodoActual.getCantMaterias();
        }
        else{
            return null;
        }
    }

}
