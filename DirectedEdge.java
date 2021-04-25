public class DirectedEdge{
    double weight;
    int from;
    int to;

    /**
     *
     * @param from: Vertex from which edge comes
     * @param to: Vertex to which edge goes
     * @param weight: weight of edge
     */
    DirectedEdge(int from, int to, double weight){
        this.weight = weight;
        this.from=from;
        this.to=to;
    }

    public double getWeight(){
        return this.weight;
    }

}