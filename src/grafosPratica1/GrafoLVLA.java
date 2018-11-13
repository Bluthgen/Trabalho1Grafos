package grafosPratica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GrafoLVLA extends Grafo{
    //private List<Vertice> vertices;
    private Vertice[] vertices;
    private int numVerices;
    private List<Aresta> arestas;

    public GrafoLVLA() {
        //vertices= new ArrayList<>();
        numVerices= 0;
        arestas= new ArrayList<>();
    }
    
    public boolean existeVertice(Integer v){
        /*
        for(Vertice iterator: vertices){
            if(iterator.id.equals(v))
                return true;
        }
        return false;
        */
        return v < numVerices;
    }

    public Vertice getVertice(Integer v){
        /*
        for(Vertice iterator: vertices){
            if(iterator.id.equals(v))
                return iterator;
        }
        return null;
        */
        if(existeVertice(v)){
            return vertices[v];
        }
        return null;
    }

    public void mostrarGrafo(){
        /*for(Vertice v : vertices){
            System.out.print(v.id);
            for(Aresta adj : arestas){
                if(adj.u.equals(v.id))
                    System.out.print("->"+adj.v+"("+adj.peso+")");
            }
            //System.out.println(" Grau:"+calculaGrau(v));
            System.out.print("\n");
        }
        */
        for(int i=0; i<numVerices; i++){
            for(Aresta a: arestas){
                if(a.u.equals(vertices[i].id))
                    System.out.println("->"+a.v+"("+a.peso+")");
            }
        }
    }
    
    public void insereVertice(Integer v){
        vertices= new Vertice[v];
        numVerices= v;
        for(int i= 0; i<numVerices; i++){
            vertices[i]= new Vertice(i);
        }
        /*
        if(!existeVertice(v)){
            //vertices.add(new Vertice(v));
            vertices[v]= new Vertice(v);
            numVerices++;
        }
        */
    }
    
    public boolean existeAresta(Integer u, Integer v){
        return arestas.stream().anyMatch((a) -> (Objects.equals(a.u, u) && Objects.equals(a.v, v)));
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            arestas.add(new Aresta(u, v, peso));
            //System.out.println("Inserido "+ u + ", " + v);
        }
    }

    public void removeVertice(Integer v){
        if(existeVertice(v)){
            arestas.stream().filter((adj) -> (Objects.equals(adj.u, v) || Objects.equals(adj.v, v))).forEachOrdered((adj) -> {
                removeAresta(adj.u, adj.v);
            });
            //vertices.remove(v.intValue());
        }
    }

    public void removeAresta(Integer u, Integer v){
        for(Aresta a: arestas){
            if(a.u.equals(u) && a.v.equals(v)){
                arestas.remove(a);
                return;
            }
        }
    }

    private void printMinimo(Vertice u, Vertice v){
        //Vertice v= getVertice(adj.vertice);
        if(v.d == Double.POSITIVE_INFINITY){
            printNaoCaminho(u, v);
        }
        System.out.println(u.id + " " + v.id + " " + v.d);
        printCaminho(u, v);
        System.out.println(" ");
    }

    private void printNaoCaminho(Vertice u, Vertice v){
        System.out.println(u.id + " " + v.id + " inf");
        System.out.println("Não existe caminho");
    }

    private void printCaminho(Vertice u, Vertice v){
        if(u.equals(v) || v.pred < 0) {
            System.out.print(v.id);
            return;
        }
        printCaminho(u, getVertice(v.pred));
        System.out.print("->"+v.id);
    }

    public void mostrarCustos(int s){
        for(Vertice v : vertices){
            printMinimo(getVertice(s), v);
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

    public boolean bellmanFord(Integer s){
        initMinPath(s);
        for(int i= 0; i<vertices.length; i++){
            for(Aresta a : arestas){
                relax(a.u, a.v, a.peso);
            }
        }
        for(Aresta a : arestas){
            if(getVertice(a.v).d > (getVertice(a.u).d + a.peso)){
                return false;
            }
        }
        return true;
    }
    
    private class Aresta {
        Integer u, v;
        Double peso;
        
        Aresta(Integer u, Integer v, Double peso){
            this.u= u;
            this.v= v;
            this.peso= peso;
        }
    }

    private class Vertice{
        Integer id;
        Double d;
        Integer pred;

        Vertice(Integer id) {
            this.id = id;
            this.d = Double.POSITIVE_INFINITY;
            this.pred = null;
        }


    }
}
