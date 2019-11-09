public class GraphTown{

    // Constructor to that takes in data 
    public GraphTown(){}

    // inserts an edge between any two towns/cities 
    // passed as argument to this method
    public insertEdge(){}

    // deletes an edge between any two towns passed as 
    // argument to this method
    public deleteEdge(){}

    // inserts a town into your graph data structure. 
    // This call for assigning edges to at least one other 
    // town in order to make sure that the graph is connected
    public insertTown(){}

    // delete any town that is supplied to this method as an 
    // argument. Note that when you delete a town, you must 
    // delete all edges to all other towns before deleting this town
    public deleteTown(){}

    // pass a list of towns as arguments to this method which declares
    // the list as neighbors of this town.
    public assignNeighbours(){}

    // create a minimum spanning tree and print such tree for us to see
    public showMST(){}

    // defines a vertex in a graph
    public class Node {
        private String label;
        private List<Edge> edges;
    }

    // defines edge associated with a vertex
    public class Edge {
        private Node destination;
        private double weight;
    }

}