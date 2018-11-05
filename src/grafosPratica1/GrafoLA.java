package grafosPratica1;

//package grafospratica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoLA extends Grafo{
    private Map<Vertice, List<Aresta>> vertices;

    public GrafoLA() {
        vertices = new HashMap();
    }

    public boolean existeVertice(Integer v){
        for(Vertice vertice: vertices.keySet())
            if(vertice.id == v)
                return true;
        return false;
    }
    
    public Vertice getVertice(Integer v){
        for(Vertice vertice: vertices.keySet())
            if(vertice.id == v)
                return vertice;
        return null;
    }
    
    public void mostrarGrafo(){
        for(Vertice v : vertices.keySet()){
            System.out.print(v.id);
            for(Aresta adj : vertices.get(v)){
                System.out.print("->"+adj.vertice+"("+adj.peso+")");
            }
            //System.out.println(" Grau:"+calculaGrau(v));
            System.out.print("\n");
        }
    }
    
    public void insereVertice(Integer v){
        if(!existeVertice(v)){
            vertices.put(new Vertice(v), new ArrayList<Aresta>());
        }
    }
    
    public boolean existeAresta(Integer u, Integer v){
        if(existeVertice(u)){
            for(Aresta adj : vertices.get(getVertice(u))){
                if(adj.vertice == v){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            vertices.get(getVertice(u)).add(new Aresta(v, peso));
        }
    }
    
    /*
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
    
    */
    
    public void removeVertice(Integer v){
        if(existeVertice(v)){
            for(Vertice adj : vertices.keySet()){
                removeAresta(adj.id, v);
            }
            vertices.remove(getVertice(v));
        }
    }

    public void removeAresta(Integer u, Integer v){
        if(existeAresta(u, v)){
            vertices.get(getVertice(u)).remove(v);
        }
    }
    
    public void initGraph(Integer u){
        Vertice vert= getVertice(u);
        vert.d = 0.0;
    }

    
    public void relax(Integer u, Integer v, Double w){
        Vertice vertU= getVertice(u);
        Vertice vertV= getVertice(v);
        if(vertV.d > vertU.d + w){
            vertV.d = vertU.d + w;
            vertV.pred = u;
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
    
    private class Vertice{
        Integer id;
        Double d;
        Integer pred;

        public Vertice(Integer id) {
            this.id = id;
            this.d = Double.POSITIVE_INFINITY;
            this.pred = null;
        }
        
        
    }
}


