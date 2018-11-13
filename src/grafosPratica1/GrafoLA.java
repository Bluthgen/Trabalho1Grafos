package grafosPratica1;


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
        Vertice v;
        for(Map.Entry<Vertice, List<Aresta>> e : vertices.values()){
            v= e.getKey();
            printMinimo(getVertice(s), v);
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

    
    public void relax(Integer u, Integer v, Double w, MinHeap fila){
        Vertice vertU= getVertice(u);
        Vertice vertV= getVertice(v);

        double peso= (vertU.d + w);
        if(vertV.d > vertU.d + w){
            fila.decreaseKey(fila.getId(vertV), (int)peso);
            // vertV.d = vertU.d + w;
            vertV.pred = u;
        }
    }

    public void dijkstra(Integer s){
        //Queue<Vertice> fila= new PriorityQueue<>();
        MinHeap fila = new MinHeap();
        //Set<Vertice> verificado= new HashSet<>();
        Vertice atual;
        initMinPath(0);
        for(int i= 0; i<vertices.size(); i++){
            fila.insert(getVertice(i));
        }
        while(!fila.isEmpty()){
            atual= fila.extractMin();
            //verificado.add(atual);
            if(vertices.get(atual.id) != null) {
                for (Aresta a : vertices.get(atual.id).getValue()) {
                    relax(atual.id, a.vertice, a.peso, fila);
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
            this.pred = -1;
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

    private class MinHeap {

        private ArrayList<Vertice> list;

        public MinHeap() {

            this.list = new ArrayList<>();
        }

        public MinHeap(ArrayList<Vertice> items) {

            this.list = items;
            buildHeap();
        }

        public void insert(Vertice item) {

            list.add(item);
            int i = list.size() - 1;
            int parent = parent(i);

            while (parent != i && list.get(i).d < list.get(parent).d) {

                swap(i, parent);
                i = parent;
                parent = parent(i);
            }
        }

        public void buildHeap() {

            for (int i = list.size() / 2; i >= 0; i--) {
                minHeapify(i);
            }
        }

        public Vertice extractMin() {

            if (list.size() == 0) {

                throw new IllegalStateException("MinHeap is EMPTY");
            } else if (list.size() == 1) {

                Vertice min = list.remove(0);
                return min;
            }

            // remove the last item ,and set it as new root
            Vertice min = list.get(0);
            Vertice lastItem = list.remove(list.size() - 1);
            list.set(0, lastItem);

            // bubble-down until heap property is maintained
            minHeapify(0);

            // return min key
            return min;
        }

        public void decreaseKey(int i, int key) {

            if (list.get(i).d < key) {

                throw new IllegalArgumentException("Key is larger than the original key");
            }

            list.get(i).d= key + 0.0;
            int parent = parent(i);

            // bubble-up until heap property is maintained
            while (i > 0 && list.get(parent).d > list.get(i).d) {

                swap(i, parent);
                i = parent;
                parent = parent(parent);
            }
        }

        private void minHeapify(int i) {

            int left = left(i);
            int right = right(i);
            int smallest = -1;

            // find the smallest key between current node and its children.
            if (left <= list.size() - 1 && list.get(left).d < list.get(i).d) {
                smallest = left;
            } else {
                smallest = i;
            }

            if (right <= list.size() - 1 && list.get(right).d < list.get(smallest).d) {
                smallest = right;
            }

            // if the smallest key is not the current key then bubble-down it.
            if (smallest != i) {

                swap(i, smallest);
                minHeapify(smallest);
            }
        }

        public Vertice getMin() {

            return list.get(0);
        }

        public boolean isEmpty() {

            return list.size() == 0;
        }

        private int right(int i) {

            return 2 * i + 2;
        }

        private int left(int i) {

            return 2 * i + 1;
        }

        private int parent(int i) {

            if (i % 2 == 1) {
                return i / 2;
            }

            return (i - 1) / 2;
        }

        private void swap(int i, int parent) {

            Vertice temp = list.get(parent);
            list.set(parent, list.get(i));
            list.set(i, temp);
        }

        public int getId(Vertice v){
            for(int i= 0; i<list.size(); i++){
                if(v.equals(list.get(i))){
                    return i;
                }
            }
            return -1;
        }

    }
}


