package aed.nuevo;

import java.util.ArrayList;
import java.util.List;
import aed.SistemaSIU.CargoDocente;
// Invariante de Representacion:  raiz es distinto de null y raiz cumple el invariante de NodoCarreras y
// los hijos de la raiz estan ordenados lexicograficamente y no puede tener sos hijos iguales
public class Carreras {
    
    NodoCarreras raiz;


    public Carreras(){
        raiz = new NodoCarreras();
    }

    public NodoCarreras insert(String pal) {
        NodoCarreras node = raiz;
        for (char ch : pal.toCharArray()) {
            NodoCarreras n = null;
            for (NodoCarreras hijo : node.hijos){
                if(hijo.valor == ch){
                    n = hijo;
                    break;
                }
            }
            if(n == null){
                NodoCarreras newhijo = new NodoCarreras(ch);
                node.addHijo(newhijo);
                node = newhijo;
            }else{
                node = n;
            }
         
        }
        node.ultimaletra = true;
        node.materiastrie = new Materia();
        return node;
    }  

    public NodoCarreras buscar(String pala){
        NodoCarreras nodo = raiz;
        for (char ch : pala.toCharArray()) 
        {
            NodoCarreras posible = null;
            for (NodoCarreras hijo : nodo.hijos)
            {
                if(hijo.valor == ch)posible = hijo;
            }
            if(posible == null){
                return null;
            }else{
                nodo = posible;
            }
        }


        return nodo;
    }

    public NodoMaterias insertmat(NodoCarreras ult, String materia,ArrayList<NodoCarreras> donde,ArrayList<NodoMaterias> otrosnombres,ArrayList<String> nombres){
       return ult.materiastrie.insert(materia,donde,otrosnombres,nombres);

    }
    
    public void inscribir(String estudiante,String carrera,String materia){
        Materia materiasdecarrera = buscar(carrera).getMaterias();
        NodoMaterias la_materia =  materiasdecarrera.buscar(materia);
        la_materia.inscribir(estudiante);
    }
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Materia materiasdecarrera = buscar(carrera).getMaterias();
        NodoMaterias la_materia =  materiasdecarrera.buscar(materia);
        la_materia.nuevo_docente(cargo);
    }   


    private void getcarreras(NodoCarreras node, String prefix, List<String> words) {
        if (node.ultimaletra) {
            words.add(prefix);
        }
        for (NodoCarreras child : node.hijos) {
            getcarreras(child, prefix + child.valor, words);
        }
    }  
    public String[] get_carreras() {
        List<String> words = new ArrayList<>();
        getcarreras(raiz, "", words);
        return words.toArray(new String[0]);
    }


  





}
