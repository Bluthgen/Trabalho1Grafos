package grafosPratica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GrafoLVLA extends Grafo{
    private List<Integer> vertices;
    private List<Aresta> arestas;

    public GrafoLVLA() {
        vertices= new ArrayList<>();
        arestas= new ArrayList<>();
    }
    
    public boolean existeVertice(Integer v){
        int indice= vertices.indexOf(v);
        return indice>=0;
    }
    
    public void mostrarGrafo(){
        for(Integer v : vertices){
            System.out.print(v);
            for(Aresta adj : arestas){
                if(adj.u == v)
                    System.out.print("->"+adj.v+"("+adj.peso+")");
            }
            //System.out.println(" Grau:"+calculaGrau(v));
            System.out.print("\n");
        }
    }
    
    public void insereVertice(Integer v){
        if(!existeVertice(v)){
            vertices.add(v);
        }
    }
    
    public boolean existeAresta(Integer u, Integer v){
        return arestas.stream().anyMatch((a) -> (Objects.equals(a.u, u) && Objects.equals(a.v, v)));
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            arestas.add(new Aresta(u, v, peso));
        }
    }
    
    public void removeVertice(Integer v){
        if(existeVertice(v)){
            arestas.stream().filter((adj) -> (Objects.equals(adj.u, v) || Objects.equals(adj.v, v))).forEachOrdered((adj) -> {
                removeAresta(adj.u, adj.v);
            });
            vertices.remove(v);
        }
    }

    public void removeAresta(Integer u, Integer v){
        for(Aresta a: arestas){
            if(a.u == u && a.v == v){
                arestas.remove(a);
                return;
            }
        }
    }
    
    
    private class Aresta {
        Integer u, v;
        Double peso;
        
        public Aresta(Integer u, Integer v, Double peso){
            this.u= u;
            this.v= v;
            this.peso= peso;
        }
    }
}
