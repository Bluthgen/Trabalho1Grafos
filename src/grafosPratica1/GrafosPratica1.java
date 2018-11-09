package grafosPratica1;

//package grafospratica1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GrafosPratica1 {

    final static Charset ENCODING = StandardCharsets.UTF_8;
    
    private static void log(Object msg){
        System.out.println(String.valueOf(msg));
    }
    
    static void readLargerTextFile(String fileName, Grafo grafo) throws IOException {
        Path path = Paths.get(fileName);
        String linha= "", anterior;
        boolean arestas= false;
        String[] pedacos;
        int u,v;
        double peso;
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                anterior= new String(linha);
                linha= scanner.nextLine();
                if(linha.contains("#arestas")){
                    arestas= true;
                    grafo.insereVertice(Integer.parseInt(anterior) + 1);
                    continue;
                }
                if(!arestas)
                    continue;
                pedacos= linha.split(" ");
                u= Integer.parseInt(pedacos[0]);
                v= Integer.parseInt(pedacos[1]);
                peso= Double.parseDouble(pedacos[2]);
                grafo.insereAresta(u,v,peso);
            }      
        }
    }
    
    static void readLargerTextFileD(String fileName, GrafoLA grafo) throws IOException {
        Path path = Paths.get(fileName);
        String linha= "", anterior;
        boolean arestas= false;
        String[] pedacos;
        int u,v;
        double peso;
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                linha= scanner.nextLine();
                if(linha.contains("#vertices")){
                    //System.out.println("Vertices:");
                    continue;
                }
                if(linha.contains("#arestas")){
                    //System.out.println("Arestas:");
                    arestas= true;
                    continue;
                }
                if(!arestas){
                    grafo.insereVertice(Integer.parseInt(linha));
                    //System.out.println("V-"+linha);
                    continue;
                }
                    
                pedacos= linha.split(" ");
                u= Integer.parseInt(pedacos[0]);
                v= Integer.parseInt(pedacos[1]);
                peso= Double.parseDouble(pedacos[2]);
                //System.out.println("A-:"+u+", "+v+" - "+peso);
                grafo.insereAresta(u,v,peso);
            }      
        }
    }
    
    public static void FW() throws IOException{
        GrafoMA grafo= new GrafoMA();
        readLargerTextFile(Paths.get("").toAbsolutePath().toString() + "\\src\\grafosPratica1\\grafo_500.dat", grafo);
        //grafo.mostrarGrafo();
        grafo.floydWarshall();
        grafo.mostrarCustos();
    }
    
    public static void D() throws IOException{
        GrafoLA grafo= new GrafoLA();
        readLargerTextFile(Paths.get("").toAbsolutePath().toString() + "\\src\\grafosPratica1\\grafo_500.dat", grafo);
        grafo.mostrarGrafo();
        grafo.dijkstra(0);
        grafo.mostrarCustos();
    }
    
    public static void BF() throws IOException{
        GrafoLVLA grafo= new GrafoLVLA();
        readLargerTextFile(Paths.get("").toAbsolutePath().toString() + "\\src\\grafosPratica1\\grafo_500.dat", grafo);
        //grafo.mostrarGrafo();
        grafo.bellmanFord(0);
        grafo.mostrarCustos();
    }
    
    public static void main(String[] args) throws IOException{
        D();
        //FW();
        //BF();
    }
    
}
