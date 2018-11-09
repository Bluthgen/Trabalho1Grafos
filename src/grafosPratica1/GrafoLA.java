package grafosPratica1;

//package grafospratica1;

import java.util.*;

public class GrafoLA extends Grafo{
    private Map<Integer, Map.Entry<Vertice, List<Aresta>>> vertices;

    public GrafoLA() {
        vertices = new HashMap();
    }

    public boolean existeVertice(Integer v){
        /*
        for(Vertice vertice: vertices.keySet())
            if(vertice.id.equals(v))
                return true;
        return false;
        */
        if(vertices.get(v) != null){
            return true;
        }
        return false;
    }
    
    public Vertice getVertice(Integer v){
        /*
        for(Vertice vertice: vertices.keySet())
            if(vertice.id.equals(v))
                return vertice;
        return null;
        */
        return vertices.get(v).getKey();
    }
    
    public void mostrarGrafo(){
        Vertice v;
        for(Map.Entry<Vertice, List<Aresta>> e : vertices.values()){
            v= e.getKey();
            System.out.print(v.id);
            for(Aresta adj : e.getValue()){
                System.out.print("->"+adj.vertice+"("+adj.peso+")");
            }
            //System.out.println(" Grau:"+calculaGrau(v));
            System.out.print("\n");
        }

    }

    public void mostrarCustos(){
        Vertice v;
        for(Map.Entry<Vertice, List<Aresta>> e : vertices.values()){
            v= e.getKey();
            System.out.println(v.id+" - "+v.d);
        }
    }
    
    public void insereVertice(Integer v){
        for(int i= 0; i<v; i++){
            vertices.put(i, new AbstractMap.SimpleEntry(new Vertice(i), new ArrayList<>()));
        }
        /*
        if(!existeVertice(v)){
            vertices.put(new Vertice(v), new ArrayList<Aresta>());
        }
        */
    }
    
    public boolean existeAresta(Integer u, Integer v){
        if(existeVertice(u)){
            for(Aresta adj : vertices.get(u).getValue()){
                if(adj.vertice.equals(v)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            vertices.get(u).getValue().add(new Aresta(v, peso));
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
            for(Map.Entry<Vertice, List<Aresta>> e : vertices.values()){
                for(Aresta a : e.getValue())
                removeAresta(a.vertice, v);
            }
            vertices.remove(v);
        }
    }

    public void removeAresta(Integer u, Integer v){
        if(existeAresta(u, v)){
            vertices.get(u).getValue().remove(v);
        }
    }
    
    public void initMinPath(Integer u){
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

    public void dijkstra(Integer s){
        Queue<Vertice> fila= new PriorityQueue<>();
        //Set<Vertice> verificado= new HashSet<>();
        Vertice atual;
        initMinPath(0);
        for(int i= 0; i<vertices.size(); i++){
            fila.add(getVertice(i));
        }
        while(!fila.isEmpty()){
            atual= fila.remove();
            //verificado.add(atual);
            if(vertices.get(atual.id) != null) {
                for (Aresta a : vertices.get(atual.id).getValue()) {
                    relax(atual.id, a.vertice, a.peso);
                }
            }
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
    
    private class Vertice implements Comparable<Vertice>{
        Integer id;
        Double d;
        Integer pred;

        public Vertice(Integer id) {
            this.id = id;
            this.d = Double.POSITIVE_INFINITY;
            this.pred = null;
        }

        @Override
        public int compareTo(Vertice o) {
            if(this.id < o.id)
                return -1;
            else if(this.id > o.id){
                return 1;
            }
            return 0;
        }
    }
}


