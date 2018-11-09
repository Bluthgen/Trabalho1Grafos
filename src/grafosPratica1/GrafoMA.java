package grafosPratica1;

public class GrafoMA extends Grafo{
    private Double[][] matriz;
    private int numVertices;
    
    private Double[][] custos;
    
    public GrafoMA(){
        this.numVertices= 0;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    
    public boolean existeVertice(Integer v){
        return v <= numVertices;
    }
    
    public void insereVertice(Integer v){
        if(!existeVertice(v)){
            this.numVertices= v;
            matriz= new Double[v][v];
            custos= new Double[v][v];
            for(int i= 0; i<v; i++){
                for(int j= 0; j<v; j++){
                    if(i == j){
                        matriz[i][j]= 0.0;
                        custos[i][j]= 0.0;
                    }else{
                        matriz[i][j]= Double.POSITIVE_INFINITY;
                        custos[i][j]= Double.POSITIVE_INFINITY;
                    }
                }
            }
        }
    }
    
    public boolean existeAresta(Integer u, Integer v){
        if(existeVertice(u) && existeVertice(v)){
            if(matriz[u][v] < Double.POSITIVE_INFINITY)
                return true;
        }
        return false;
    }
    
    public void mostrarGrafo(){
        for(int i= 0; i < numVertices; i++){
            System.out.print((i));
            for(int j= 0; j < numVertices; j++){
                if(matriz[i][j] < Double.POSITIVE_INFINITY){
                    System.out.print("->" + (j));
                }
            }
            System.out.print("\n");
        }
    }
    
    public void mostrarCustos(){
        for(int i= 0; i < numVertices; i++){
            for(int j= 0; j < numVertices; j++){
                System.out.println((i) + "->" + (j)+": "+custos[i][j]);
            }
        }
    }
    
    public void insereAresta(Integer u, Integer v, Double peso){
        if(existeVertice(u) && existeVertice(v)){
            if(matriz[u][v] < Double.POSITIVE_INFINITY){
                return;
            }
            matriz[u][v]= peso;
            custos[u][v]= peso;
        }
    }
    
    public void removeAresta(Integer u, Integer v){
        if(existeVertice(u) && existeVertice(v)){
            matriz[u][v]= Double.POSITIVE_INFINITY;
            custos[u][v]= Double.POSITIVE_INFINITY;
        }
    }
    
    public int calculaGrauEntrada(Integer v){
        if(existeVertice(v)){
            int count= 0;
            for(int i= 0; i<=numVertices; i++){
                if(matriz[i][v] < Double.POSITIVE_INFINITY)
                    count++;
            }
            return count;
        }
        return 0;
    }
    
    public int calculaGrauSaida(Integer v){
        if(existeVertice(v)){
            int count= 0;
            for(int i= 0; i<=numVertices; i++){
                if(matriz[v][i] < Double.POSITIVE_INFINITY)
                    count++;
            }
            return count;
        }
        return 0;
    }
    
    public void floydWarshall(){
        for(int i= 0; i<numVertices; i++){
            for(int j= 0; j<numVertices; j++){
                for(int k= 0; k<numVertices; k++){
                    custos[i][j]= Math.min(custos[i][j], custos[i][k] + custos[k][j]);
                }
            }
        }
    }
}
