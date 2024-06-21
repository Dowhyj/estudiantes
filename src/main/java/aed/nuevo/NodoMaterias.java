package aed.nuevo;

import java.util.ArrayList;

import aed.SistemaSIU;

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
        for(String estudiante : estudiantes.get_inscriptos()){
            todos.desinscribir(estudiante);//-1
        }
        for(int i = 0; i < carreras.size();i++){
            NodoCarreras n = carreras.get(i);  
            n.sacar_materia(nombres.get(i));
        }

    }

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
