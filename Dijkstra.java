public class Dijkstra{
    EdgeWeightedDigraph graph;
    double[] distTo;
    boolean[] hasPathTo;
    DirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;

    /**
     * @param graph: EdgeWeightedDigraph from which to calculate paths
     * @param startVertex: Vertex to calculate paths from
     */
    public Dijkstra(EdgeWeightedDigraph graph, int startVertex){
        this.graph = graph;
        int numVertices = graph.numberOfVertices();
        distTo = new double[numVertices];
        hasPathTo = new boolean[numVertices];
        edgeTo = new DirectedEdge[numVertices];
        //Initialise all edges to infinite length
        for(int i = 0; i < numVertices; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            hasPathTo[i] = false;
            edgeTo[i] = null;
        }

        //Initalise starting vertex to avoid loops
        distTo[startVertex] = 0;

        hasPathTo[startVertex] = true;
        //Priority queue implementation and code taken from princeton book website
        pq = new IndexMinPQ<Double>(numVertices);
        pq.insert(startVertex, distTo[startVertex]);
        while(!pq.isEmpty()){
            int currentVertex = pq.delMin();
            if(graph.adjacentEdges[currentVertex] != null) {
                for (DirectedEdge currentEdge : graph.adjacentEdges[currentVertex]) {
                    relax(currentEdge);
                }
            }
        }
    }

    /**
     * @param edge: edge to calculate whether shorter paths run through
     */
    private void relax(DirectedEdge edge){
        int fromVertex = graph.findVertexById(edge.from);
        int toVertex = graph.findVertexById(edge.to);
        //Calculate if edge contains shorter path than previously seen
        if(distTo[toVertex] > distTo[fromVertex] + edge.weight){
            distTo[toVertex] = distTo[fromVertex] + edge.weight;
            edgeTo[toVertex] = edge;
            hasPathTo[toVertex] = true;
            //Update priority queue with new edge distances
            if(pq.contains(toVertex)){
                pq.decreaseKey(toVertex, distTo[toVertex]);
            } else {
                pq.insert(toVertex, distTo[toVertex]);
            }
        }
    }

    public double getDistTo(int endVertex){
        return distTo[endVertex];
    }

}