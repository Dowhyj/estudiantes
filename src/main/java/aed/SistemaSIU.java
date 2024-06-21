package aed;

import java.util.ArrayList;
import aed.nuevo.*;

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
    public void inscribir(String estudiante, String carrera, String materia){
        carreras.inscribir(estudiante, carrera, materia);
        estudiantes.inscribir(estudiante);
    }
    //3
    public int inscriptos(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos();
    }
    //4
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        carreras.agregarDocente(cargo, carrera, materia);
    }
    //5
    public int[] plantelDocente(String materia, String carrera){
        return carreras.buscar(carrera).getMaterias().buscar(materia).plantel();
    }
    //6
    public boolean excedeCupo(String materia, String carrera){
        int[] plantel = carreras.buscar(carrera).getMaterias().buscar(materia).plantel();
        int cupos = max(max(plantel[0] * 250, 100 * plantel[1]),max( 20 * plantel[2],30 * plantel[2]));
        return carreras.buscar(carrera).getMaterias().buscar(materia).inscriptos() > cupos; 
    }
    //7
    public String[] carreras(){
        return carreras.get_carreras();    
    }
    //8
    public String[] materias(String carrera){
        return carreras.buscar(carrera).getMaterias().get_materias();
    }
    //9
    public int materiasInscriptas(String estudiante){
        return estudiantes.materias_inscriptas(estudiante);
    }
    //10
    public void cerrarMateria(String materia, String carrera){
        NodoCarreras c = carreras.buscar(carrera);
        NodoMaterias m = c.getMaterias().buscar(materia);
        m.cerrar_materia(estudiantes);
    }

    private int max(int uno,int dos){
        return uno < dos ? uno:dos;
    }
}
