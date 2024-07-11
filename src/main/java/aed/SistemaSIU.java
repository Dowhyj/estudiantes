epackage aed;

import java.util.ArrayList;
//Invariante de Representacion: carreras cumple el invariante de Carreras y estudiantes cumple el invariante de Estudiante
//para todo nodo1 hoja y nodo2 hoja pertenecientes a NodoMaterias, los observadores del nodo1 son iguales a los observadores del nodo2 si solo si el nombre de la materia de nodo1 y nombre de la materia del nodo2 pertenecen a un mismo InfoMateria
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
        carreras.inscribir(estudiante, carrera, materia); // Recorre el trie Carreras O(c) + Recorre el trie Marterias O(m) y inserta en su trie de estudiantes de la materia como el tamano del numero esta acotado O(1) 
        estudiantes.inscribir(estudiante); //Recorre al trie estudiantes y como el tamano del numero esta acotado O(1) 
    } // TOTAL = O(c + m)
    
    //3
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operacion de busqueda es proporcional a la longitud de la cadena(Carreras y Materias)
    
    public int inscriptos(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos(); //Recorro el trie Carreras O(c) + recorro el trie Materias O(m) + accedo al observador incriptos O(1)
    } //TOTAL = O(c + m)

  
    //4
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera. 
    //Las operaciones de busqueda e inserción son directamente proporcionales a la longitud de las cadenas de caracteres
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        carreras.agregarDocente(cargo, carrera, materia);  //Recorro el trie carreras O(|c|) + recorro el trie materias O(|m|) + agrego a la lista segun su cargo docente O(1)
    }// TOTAL = O(|c| + |m|)
    
    //5
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operación de busqueda es directamente proporcional a la longitud de la cadena de caracteres(Carreras y Materias)
  
    public int[] plantelDocente(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).plantel(); //Recorro el trie carreras O(|c|) + recorro el trie Materias O(|m|) + accedo al observador plantelDocente O(1)
    } //TOTAL = O(|c| + |m|)

    //6
    //En el peor de los casos, se recorre hasta la ultima carrera dentro trie carrera, y luego hasta la ultima la ultima de esa carrera.
    //La operación de búsqueda es directamente proporcional a la longitud de la cadena de caracteres(Carreras y Materias)
  
    public boolean excedeCupo(String materia, String carrera){
        int[] plantel = carreras.buscar(carrera).getMaterias().buscar(materia).plantel(); //Recorro el trie carreras O(|c|) + recorro el trie Materias O(|m|) + accedo al observador plantelDocente O(1)
        int cupos = max(max(plantel[0] * 250, 100 * plantel[1]),max( 20 * plantel[2],30 * plantel[3])); // Calculo el cupo total de la materia multiplicando cada elemento O(1) * 4 y sumando todos los resultados O(1) * 4 (lista plantelDocente esta acotada por la cantidad de cargos que es 4)
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos() > cupos; //Recorro el trie carreras O(|c|) + recorro el trie Materias O(|m|) + accedo al observador inscriptos O(1) + comparar O(1)
    } // TOTAL = O(|c| + |m|) + O(4) + O(4) + O(|c| + |m|) = 2 * O(|c| + |m|) = O(|c| + |m|)
  
    //7
    //Se recorren todas las cadenas de caracteres que representan a una carrera
  
    public String[] carreras(){
        return carreras.get_carreras(); // Recorro cada carrera O(Σ |c|)    
    }
  
    //8
    //El peor de los casos depende de la cantidad de materias que tiene que cada carrera
    //La operación de búsqueda es directamente proporcional a la longitud de la cadena de caracteres de la carrera y la longitud de la cadena de caracteres de las materias de esa carrera
  
    public String[] materias(String carrera){
        return carreras.buscar(carrera).getMaterias().get_materias();// Recorro el trie carreras O(|c|) + recorro cada materia O(Σ|m|)
    } //TOTAL = O(|c| + Σ|m|) con m perteneciente a la carrera c 

    //9
    //Cumple preguntando directamente el valor de un entero el cual siempre tiene la misma longitud, por lo tanto para cualquier entero es una longitud cosntante
  
    public int materiasInscriptas(String estudiante){
        return estudiantes.materias_inscriptas(estudiante); // Recorro el trie estudiante y como el tamano de los numeros esta acotado O(1) + acceder a cantidad O(1)
    } //TOTAL O(1)
  
    //10
    //Las operaciones de búsqueda (carrera y materia) son directamente proporcionales a la longitud de la cadena de caracteres
    //La operación cerrar materia depende de la longitud de los nombres relacionados a esa materia y de la cantidad de estudiantes inscriptos en esa materia
    public void cerrarMateria(String materia, String carrera){
        NodoCarreras c = carreras.buscar(carrera);  // Recorro el trie carreras O(|c|) + accedo al nombre de
        NodoMaterias m = c.getMaterias().buscar(materia);
        m.cerrar_materia(estudiantes);
    }

    private int max(int uno,int dos){
        return uno < dos ? uno:dos;
    }
}
