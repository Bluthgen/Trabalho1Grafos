package grafosPratica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoNOLA{
    private Map<Integer, List<Integer>> vertices;

    public GrafoNOLA() {
        vertices = new HashMap();
    }

    public boolean existeVertice(Integer v){
        return vertices.containsKey(v);
    }

    public void mostrarGrafo(){
        for(Integer v : vertices.keySet()){
            System.out.print(v);
            for(Integer adj : vertices.get(v)){
                System.out.print("->"+adj);
            }
            System.out.println(" Grau:"+calculaGrau(v));
        }
    }
    
    public void insereVertice(Integer v){
        if(!existeVertice(v)){
            vertices.put(v, new ArrayList<Integer>());
        }
    }
    
    public boolean existeAresta(Integer u, Integer v){
        if(existeVertice(u)){
            for(int adj : vertices.get(u)){
                if(adj == v){
                    return true;
                }
            }
        }
        return false;
    }

    public void insereAresta(Integer u, Integer v){
        if(existeVertice(u) && existeVertice(v) && u != v){
            vertices.get(u).add(v);
            vertices.get(v).add(u);
        }
    }
    
    public int calculaGrau(Integer v){
        int count = 0;
        if(existeVertice(v)){
            for(int adj : vertices.get(v)){
                count++;
            }
        }
        return count;
    }
    
    public void removeVertice(Integer v){
        if(existeVertice(v)){
            for(int adj : vertices.get(v)){
                if(adj != v){
                    removeAresta(adj, v);
                }
            }
            vertices.remove(v);
        }
    }

    public void removeAresta(Integer u, Integer v){
        if(existeAresta(u, v)){
            vertices.get(u).remove(v);
        }
    }

    public GrafoNOLA calculaComplento(){
        GrafoNOLA comp = new GrafoNOLA();
        for(int i : this.vertices.keySet()){
            comp.insereVertice(i);
        }
        for(int i : this.vertices.keySet()){
            for(int j : this.vertices.keySet()){
                if(!this.existeAresta(i, j) && !comp.existeAresta(i, j)){
                    comp.insereAresta(i, j);
                }
            }
        }
        return comp;
    }
     
    public GrafoLA converteGrafoLA(){
        GrafoLA grafo = new GrafoLA();
        for(int i : this.vertices.keySet()){
            grafo.insereVertice(i);
        }
        for(int i : this.vertices.keySet()){
            for(int j : this.vertices.get(i)){
                if(this.existeAresta(i, j) && !grafo.existeAresta(i, j)){
                    grafo.insereAresta(i, j, 1.0);
                }
            }
        }
        return grafo;
    }
}
