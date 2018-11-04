package grafosPratica1;

//package grafospratica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoLA extends Grafo{
    private Map<Integer, List<Aresta>> vertices;

    public GrafoLA() {
        vertices = new HashMap();
    }

    public boolean existeVertice(Integer v){
        return vertices.containsKey(v);
    }
    
    public void mostrarGrafo(){
        for(Integer v : vertices.keySet()){
            System.out.print(v);
            for(Aresta adj : vertices.get(v)){
                System.out.print("->"+adj.vertice+"("+adj.peso+")");
            }
            //System.out.println(" Grau:"+calculaGrau(v));
            System.out.print("\n");
        }
    }
    
    public void insereVertice(Integer v){
        if(!existeVertice(v)){
            vertices.put(v, new ArrayList<Aresta>());
        }
    }
    
    public boolean existeAresta(Integer u, Integer v){
        if(existeVertice(u)){
            for(Aresta adj : vertices.get(u)){
                if(adj.vertice == v){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            vertices.get(u).add(new Aresta(v, peso));
        }
    }
    
    public int calculaGrau(Integer v){
        return calculaGrauSaida(v) + calculaGrauEntrada(v);
    }
    
    public int calculaGrauSaida(Integer v){
        int count = 0;
        if(existeVertice(v)){
            for(Aresta adj : vertices.get(v)){
                count++;
            }
        }
        return count;
    }

    public int calculaGrauEntrada(Integer v){
        int count = 0;
        for(int adj : vertices.keySet()){
            if(adj != v && existeAresta(adj, v)){
                count++;
            }
        }
        return count;
    }
    
    
    public void removeVertice(Integer v){
        if(existeVertice(v)){
            for(int adj : vertices.keySet()){
                removeAresta(adj, v);
            }
            vertices.remove(v);
        }
    }

    public void removeAresta(Integer u, Integer v){
        if(existeAresta(u, v)){
            vertices.get(u).remove(v);
        }
    }

    /*
    public GrafoLA calculaTransposto(){
        GrafoLA trans = new GrafoLA();
        for(int i : this.vertices.keySet()){
            trans.insereVertice(i);
        }
        for(int i : this.vertices.keySet()){
            for(Aresta j : this.vertices.get(i)){
                if(this.existeAresta(i, j.vertice)){
                    trans.insereAresta(j.vertice, i, j.peso);
                }
            }
        }
        return trans;
    }

    public GrafoNOLA converteGrafoNOLA(){
        GrafoNOLA grafo = new GrafoNOLA();
        for(int i : this.vertices.keySet()){
            grafo.insereVertice(i);
        }
        for(int i : this.vertices.keySet()){
            for(Aresta j : this.vertices.get(i)){
                if(this.existeAresta(i, j.vertice) && !grafo.existeAresta(i, j.vertice)){
                    grafo.insereAresta(i, j.vertice);
                }
            }
        }
        return grafo;
    }
    */
    
    private class Aresta {
        Integer vertice;
        Double peso;
        
        public Aresta(Integer vertice, Double peso){
            this.vertice= vertice;
            this.peso= peso;
        }
    }
}


