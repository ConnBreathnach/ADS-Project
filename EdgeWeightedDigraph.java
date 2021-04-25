import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
/**
 * Code for graphs, adapted from lecture slides
 * */
public class EdgeWeightedDigraph{
    ArrayList<DirectedEdge>[] adjacentEdges;
    ArrayList<DirectedEdge> edges;
    int vertexCount;
    int edgeCount;
    int counter;
    HashMap<Integer, Integer> vertexMap;

    /**
     * EdgeweightedDigraph constructor, creates vertices and edges and populates vertexMap
     */
    EdgeWeightedDigraph(){
        counter = 0;
        edges = new ArrayList<DirectedEdge>();
        vertexMap = new HashMap<Integer, Integer>(8800);
        createVertices("Inputs/stops.txt");
        adjacentEdges = new ArrayList[vertexCount];
        addEdgesFromTripId("Inputs/stop_times.txt");
        addEdgesFromTransfers("Inputs/transfers.txt");

    }

    public static void main(String[] args){
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph();
        int startVertex = graph.vertexMap.get(2742);
        Dijkstra dijkstra = new Dijkstra(graph, startVertex);
        int endVertex = graph.vertexMap.get(1709);
        double dist = dijkstra.getDistTo(endVertex);
        System.out.println("Distance from 646 to 378 is " + dist);
    }
    /**
     * Add edge to graph variables
     * @param edge: DirectedEdge being added to graph
     */
    void addEdge(DirectedEdge edge){
        int vertexPosition = vertexMap.get(edge.from);
        if(adjacentEdges[vertexPosition] == null){
            adjacentEdges[vertexPosition] = new ArrayList<DirectedEdge>();
        }
        adjacentEdges[vertexPosition].add(edge);
        edgeCount++;
        edges.add(edge);
    }

    int numberOfEdges(){
        return edgeCount;
    }

    int numberOfVertices(){
        return vertexCount;
    }

    public int findVertexById(int id){
        return vertexMap.get(id);
    }


    public ArrayList<DirectedEdge> getEdges(){
        return this.edges;
    }

    private Scanner readFileCreateScanner(String filename){
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            return scanner;
        } catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }

    private void createVertices(String filename){
        int stopId;
        double latitude;
        double longitude;

        Scanner stopsScanner = readFileCreateScanner(filename);
        stopsScanner.useDelimiter(",");
        stopsScanner.nextLine(); // Removes header
        while(stopsScanner.hasNextLine()){
            stopId = stopsScanner.nextInt();
            stopsScanner.next();
            stopsScanner.next();
            stopsScanner.next();
            latitude = stopsScanner.nextDouble();
            longitude = stopsScanner.nextDouble();
            vertexMap.put(stopId, vertexCount);
            vertexCount++;
            stopsScanner.nextLine();
        }
        stopsScanner.close();
    }

    private void addEdgesFromTripId(String filename){
        int firstTripId;
        int secondTripId = 0;
        int firstStopId;
        int secondStopId = 0;
        int firstVertex;
        int secondVertex;
        DirectedEdge edge = null;
        final double DEFAULT_WEIGHT = 1;

        Scanner edgesScanner = readFileCreateScanner(filename);
        edgesScanner.useDelimiter(",");
        edgesScanner.nextLine();
        firstTripId = edgesScanner.nextInt();
        edgesScanner.next();
        edgesScanner.next();
        firstStopId = edgesScanner.nextInt();
        edgesScanner.nextLine();
        while(edgesScanner.hasNextLine()){
            try{
                secondTripId = edgesScanner.nextInt();
                if(firstTripId == secondTripId){
                    edgesScanner.next();
                    edgesScanner.next();
                    secondStopId = edgesScanner.nextInt();
                    edge = new DirectedEdge(firstStopId, secondStopId, DEFAULT_WEIGHT);
                    addEdge(edge);
                }
                firstTripId = secondTripId;
                firstStopId = secondStopId;
                edgesScanner.nextLine();
            }
            catch(Exception e) {
//                System.out.println("Error in adding edges from trip id");
                e.printStackTrace();
            }
        }
        edgesScanner.close();
    }

    private void addEdgesFromTransfers(String filename){
        Scanner transfersScanner = readFileCreateScanner(filename);
        int fromId = 0;
        int toId;
        int transferType;
        int minTransferTime;
        int fromVertex;
        int toVertex;
        DirectedEdge edge = null;
        final double DEFAULT_WEIGHT = 2;
        transfersScanner.useDelimiter(",|\\n");
        transfersScanner.nextLine();
        while(transfersScanner.hasNextLine()){
            fromId = transfersScanner.nextInt();
            toId = transfersScanner.nextInt();
            transferType = transfersScanner.nextInt();
            if(transferType == 0) {
                edge = new DirectedEdge(fromId, toId, DEFAULT_WEIGHT);
            }
            else if(transferType==2){
                minTransferTime = transfersScanner.nextInt();
                double weight = ((double)minTransferTime)/100;
                edge = new DirectedEdge(fromId, toId, weight);
            }
            addEdge(edge);
            transfersScanner.nextLine();
        }
        transfersScanner.close();
    }

}