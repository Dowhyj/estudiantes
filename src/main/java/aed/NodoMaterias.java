package aed;

import java.util.ArrayList;
// Invariante de Representacion: para todo nodo, nodo cumple NodoMaterias y para todo hijo perteneciente a nodo.hijos, hijo cumple NodoMaterias.
// para todo nodo, nodo.valor es un char.
// para todo nodo, nodo.ultimaletra = false entonces nodo.Carreras, nodo.otrosnombres y nodo.nombres son vacias y nodo.inscriptos=0 y nodo.docentes=[0,0,0,0] y nodo.estudiantes= new Estudiante().
// para todo nodo, nodo.ultimaletra = true entnces nodo es hoja y nodo.carreras no es vacia y para toda carrera perteneciente a nodo.carreras, carreras cumple NodoCarreras y
// nodo.otrosnombres no es vacia  y para todo otronombre perteneciente a nodo.otrosnombres, otronombre cumple NodoMaterias y
// nodo.nombres no es vacia y para todo nombre perteneciente a nodo.nombres, nombre es un string y
// nodo.inscriptos es un entero y nodo.docentes es una lista de entero que para todo docente perteneciente a nodo.docentes, docente es un entero y
// nodo.estudiantes cumple q es de clase Estudiante.
public class NodoMaterias {
     
    char valor;
    boolean ultimaletra;//ultima, no una despues
    ArrayList<NodoMaterias> hijos;
    ArrayList<NodoCarreras> carreras;
    ArrayList<NodoMaterias> otrosnombres;
    ArrayList<String> nombres;
    int inscriptos;
    int[] docentes;

    Estudiante estudiantes;

    public NodoMaterias() {//raiz
        valor = ' ';
        ultimaletra = false;
        hijos = new ArrayList<>();
        carreras = null; 
        docentes = new int[]{0,0,0,0};
        estudiantes = new Estudiante();
    }
    public NodoMaterias(char v) {//otro
        valor = v;
        ultimaletra = false;
        hijos = new ArrayList<>();
        carreras = null; 
        docentes = new int[]{0,0,0,0};
        estudiantes = new Estudiante(); 
    }

    public void inscribir(String estudiante){
        for(NodoMaterias nombres : otrosnombres){
            nombres.inscriptos +=1;
            nombres.estudiantes.insert(estudiante);
        }
        
    }

    public void nuevo_docente(SistemaSIU.CargoDocente cargo){
        int docente = docente_int(cargo);
        if(docente > 3)return;
        for(NodoMaterias nombres : otrosnombres){
            nombres.docentes[docente] += 1;
        }
    }

    public int[] plantel(){
        return docentes;
    }
    public int inscriptos(){
        return inscriptos;
    }

    public void cerrar_materia(Estudiante todos){
        for(String estudiante : estudiantes.get_inscriptos()){ // Recorro todos los estudiantes incriptos en esa materia O(Em) donde Em: cantidad de estudiantes inscriptos en la materia m
            todos.desinscribir(estudiante);//-1    //Recorro el trie estudiantes con tamano de numeros acotado O(1) + resta 1 en su cant de materias incriptas O(1)
        }// Total = O(Em)*O(2) = O(Em)
        for(int i = 0; i < carreras.size();i++){ // Recorro la lista carreras que tiene un puntero a cada Carrera que tiene una materia igual con diferente nombre O(Σ n) con n perteneciente a Nm: conjunto de nombres de la materia m
            NodoCarreras n = carreras.get(i);  // Accedo a un NodoCarrera especifico de la lista O(1)
            n.sacar_materia(nombres.get(i)); // Recorro el trie materias para eliminar el nombre de la materia igual O(|n|)
        }// Total = O(Σ n) * O(1) * O(|n|) = O(Σ|n|)

    } //TOTAL = O(Em + Σ|n|)

    private int docente_int(SistemaSIU.CargoDocente cargo){
       switch (cargo) {
        case PROF:
            return 0;
        case JTP:
            return 1;
        case AY1:
            return 2;
        case AY2:
            return 3;
        default:
            return 45235827;
       }

    }

    public NodoMaterias getHijo(char c) {
        for (NodoMaterias child : hijos) {
            if (child.valor == c) {
                return child;
            }
        }
        return null;
    }
        
    public void addHijo(NodoMaterias hijo) {
        int pos = 0;
        while (pos < hijos.size() && hijos.get(pos).valor < hijo.valor) {
            pos++;
        }
        hijos.add(pos, hijo);
    }
    public void removeHijo(char ch) {
        hijos.removeIf(hijo -> hijo.valor == ch);
    }
}
