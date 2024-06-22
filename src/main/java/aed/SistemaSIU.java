package aed;

import java.util.ArrayList;
//Invariante de Representacion: carreras cumple el invariante de Carreras y estudiantes cumple el invariante de Estudiante
//para todo nodo1 hoja y nodo2 hoja, nodo1 = nodo2 si solo si el nombre de la materia de nodo1 y nombre de la materia del nodo2 pertenecen a un mismo InfoMateria

public class SistemaSIU {
  

    public enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }
    Carreras carreras;
    Estudiante estudiantes;
    
    //1
    //En el bucle doble se recorren todas las 'infoMaterias' y para cada una se recorren sus 'ParCarreraMateria' (primera sumatoria). En esta se realizan operaciones de búsqueda e inserción que son direcatamente propocionales a la longitud de la cadena de caracteres
    //La segunda sumatoria se realiza en el for dentro del primer for que depende de la longitud de los nombres de la materia
    //La inserción de estudiante es directamente proporcional a la cantidad total de Estudiantes

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        carreras = new Carreras();   
        estudiantes = new Estudiante();
        for(InfoMateria infomat : infoMaterias){
            ArrayList<NodoCarreras> dondeesta = new ArrayList<NodoCarreras>();
            ArrayList<NodoMaterias> otrosnombres = new ArrayList<NodoMaterias>();
            ArrayList<String> nombres = new ArrayList<String>();
            for(ParCarreraMateria par : infomat.getParesCarreraMateria()){

                NodoCarreras nodocar = carreras.buscar(par.getCarrera());

                if(nodocar == null) nodocar = carreras.insert(par.getCarrera());
                
                dondeesta.add(nodocar);
                NodoMaterias materia = carreras.insertmat(nodocar, par.getNombreMateria(),dondeesta,otrosnombres,nombres);
                otrosnombres.add(materia);
                nombres.add(par.getNombreMateria());
                
            }
        }
        for(String estudiante : libretasUniversitarias ){
            estudiantes.insert(estudiante);
        }

    }
    
    //2
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera. 
    //Las operaciones de busqueda e inserción son directamente proporcionales a la longitud de las cadenas de caracteres
    public void inscribir(String estudiante, String carrera, String materia){
        carreras.inscribir(estudiante, carrera, materia);
        estudiantes.inscribir(estudiante);
    }
    
    //3
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operacion de busqueda es proporcional a la longitud de la cadena(Carreras y Materias)
    
    public int inscriptos(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos();
    }

  
    //4
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera. 
    //Las operaciones de busqueda e inserción son directamente proporcionales a la longitud de las cadenas de caracteres
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        carreras.agregarDocente(cargo, carrera, materia);
    }
    
    //5
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operación de busqueda es directamente proporcional a la longitud de la cadena de caracteres(Carreras y Materias)
  
    public int[] plantelDocente(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).plantel();
    }

    //6
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operación de búsqueda es directamente proporcional a la longitud de la cadena de caracteres(Carreras y Materias)
  
    public boolean excedeCupo(String materia, String carrera){
        int[] plantel = carreras.buscar(carrera).getMaterias().buscar(materia).plantel();
        int cupos = max(max(plantel[0] * 250, 100 * plantel[1]),max( 20 * plantel[2],30 * plantel[2]));
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos() > cupos; 
    }
  
    //7
    //Se recorren todas las cadenas de caracteres que representan a una carrera
  
    public String[] carreras(){
        return carreras.get_carreras();    
    }
  
    //8
    //El peor de los casos depende de la cantidad de materias que tiene que cada carrera
    //La operación de búsqueda es directamente proporcional a la longitud de la cadena de caracteres de la carrera y la longitud de la cadena de caracteres de las materias de esa carrera
  
    public String[] materias(String carrera){
        return carreras.buscar(carrera).getMaterias().get_materias();
    }

    //9
    //Cumple preguntando directamente el valor de un entero el cual siempre tiene la misma longitud, por lo tanto para cualquier entero es una longitud cosntante
  
    public int materiasInscriptas(String estudiante){
        return estudiantes.materias_inscriptas(estudiante);
    }
  
    //10
    //Las operaciones de búsqueda (carrera y materia) son directamente proporcionales a la longitud de la cadena de caracteres
    //La operación cerrar materia depende de la longitud de los nombres relacionados a esa materia y de la cantidad de estudiantes inscriptos en esa materia
    public void cerrarMateria(String materia, String carrera){
        NodoCarreras c = carreras.buscar(carrera);
        NodoMaterias m = c.getMaterias().buscar(materia);
        m.cerrar_materia(estudiantes);
    }

    private int max(int uno,int dos){
        return uno < dos ? uno:dos;
    }
}
