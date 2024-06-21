package aed;

import java.util.ArrayList;
import aed.mio.Carreras;
import aed.mio.NodoCarreras;

public class SistemaSIU {
    Carreras carreras;
    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        carreras = new Carreras();   
        for(InfoMateria infomat : infoMaterias){
            ArrayList<NodoCarreras> dondeesta = new ArrayList<NodoCarreras>();
            for(ParCarreraMateria par : infomat.getParesCarreraMateria()){

                NodoCarreras nodocar = carreras.buscar(par.getCarrera());

                if(nodocar == null) nodocar = carreras.insert(par.getCarrera());
                
                dondeesta.add(nodocar);
                carreras.insertmat(nodocar, par.getNombreMateria(),dondeesta);
            }
        }

    }
    public String[] getcarr(){
        return carreras.getAllWords();
    }
    public String[] getmats(String car){
        return carreras.getmats(car);
    }
    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
