import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GraphTown{

    ArrayList<Node> graph;

    // Constructor 
    public  GraphTown(){
         graph = new ArrayList<Node>();
    }

    // checks if the graph is empty
    // and return true if empty 
    public boolean isEmpty(){
        return graph.isEmpty();
    }

    // inserts an edge between any two towns/cities 
    // passed as argument to this method
    public void insertEdge(Node townOne, Node townTwo, int weight){
        
        // adding edge to first town
        Edge one = new Edge();
        one.destination = townTwo;
        one.weight = weight;
        townOne.edges.add(one);

        // adding edge to second town
        Edge two = new Edge();
        two.destination = townTwo;
        two.weight = weight;
        townTwo.edges.add(two);

    }

    // deletes an edge between any two towns passed as 
    // argument to this method
    public void deleteEdge(){}

    // inserts a town into graph data structure. 
    // This call for assigning edges to at least one other 
    // town in order to make sure that the graph is connected
    public void insertTown(Node town){
        // Checks if there is a connection
        boolean conFound = false;

        if(isEmpty()){
            graph.add(town);
        }else{
            for (Node nd : graph) {
                for (Edge edge : nd.edges) {
                    if(edge.destination.label.equals(town.label)){
                      graph.add(town);
                      conFound = true;
                      return;
                    }
                }
            }
            System.out.println("There is no connection to "+town.label+" vertex");
        }
    }

    // Inserts nodes into graph from text file 
    // as input
    public void createGraph(String filename){
        try {

            File file = new File(filename);
            Scanner scnr = new Scanner(file);

            while(scnr.hasNext()){
                //insert(scnr.nextLine());
                System.out.println(scnr.nextLine());
            }

      }
      catch(Exception e) {
            System.out.println(e);
      }
    }

    // delete any town that is supplied to this method as an 
    // argument. Delete all edges to all other towns before deleting this town
    public void deleteTown(){}

    // pass a list of towns as arguments to this method which declares
    // the list as neighbors of this town.
    public void assignNeighbours(){}

    // create a minimum spanning tree and print such tree for us to see
    public void showMST(){}

    // defines a vertex in a graph
    public class Node{
        private String label;
        private List<Edge> edges;
    }

    // defines edge associated with a vertex
    public class Edge {
        private Node destination;
        private int weight;
    }

    // running the program
    public static void main(String[] args){

        GraphTown test = new GraphTown();
        test.createGraph("botswanaTownsAndCitiesData.txt");

    }

}