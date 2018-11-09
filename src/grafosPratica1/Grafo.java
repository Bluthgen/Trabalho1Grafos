package grafosPratica1;

public abstract class Grafo {
    public Grafo() {
    }
    
    public abstract boolean existeVertice(Integer v);
    
    public abstract void insereVertice(Integer v);
    
    public abstract void mostrarGrafo();
    
    public abstract boolean existeAresta(Integer u, Integer v);
    
    public abstract void insereAresta(Integer u, Integer v, Double peso);
    
    //public abstract int calculaGrau(Integer v);
    
    //public abstract void removeVertice(Integer v);
    
    public abstract void removeAresta(Integer u, Integer v);
    
}