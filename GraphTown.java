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
        
        for (Edge edg : townOne.edges) {
            if((edg.destination.label).equals(townTwo.label)){
                System.out.println("The Edge already exists");
                return;
            }
        }
        
        Edge tempEdge = new Edge();
        tempEdge.weight = weight;

        // adding edge to first town
        tempEdge.destination = townTwo;
        townOne.edges.add(tempEdge);

        // adding edge to second town
        tempEdge.destination = townTwo;
        townTwo.edges.add(tempEdge);

    }

    // deletes an edge between any two towns passed as 
    // argument to this method
    public void deleteEdge(Node townOne, Node townTwo){

        for (int i = 0; i < townOne.edges.size(); i++) {
            Edge edg = townOne.edges.get(i);
            if((edg.destination.label).equals(townTwo.label)){
               townOne.edges.remove(edg);
            }
        }

        for (int j = 0; j < townTwo.edges.size(); j++) {
            Edge edg = townTwo.edges.get(j);
            if((edg.destination.label).equals(townOne.label)){
               townTwo.edges.remove(edg);
            }
        }
    }

    // inserts a town into graph data structure. 
    // This call for assigning edges to at least one other 
    // town in order to make sure that the graph is connected
    public void insertTown(Node town){
        // Checks if there is a connection
        if(isEmpty()){
            graph.add(town);
        }else{
            for (Edge edg : town.edges) {
                for (Node nd : graph) {
                    if((nd.label).equals(edg.destination.label)){
                        graph.add(town);
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
            Boolean isFirstLine = true;
            String[] headerTowns = new String[9];
            String[] townTemp = new String[10];
            String place = null;

            while(scnr.hasNext()){

                if(isFirstLine){

                    headerTowns = scnr.nextLine().split(", ");
                    isFirstLine = false;

                }else{

                    townTemp = scnr.nextLine().split(", ");
                    place = townTemp[0];
                    townTemp[0] = "0";
                    Node temp = new Node();
                    temp.label = place;

                    for (int i = 1; i < townTemp.length; i++) {
                        if(Integer.parseInt(townTemp[i]) != 0){
                            Edge tempEdge = new Edge();
                            Node tempDest = new Node();
                            int j = i - 1;
                            tempDest.label = headerTowns[j];
                            tempEdge.destination = tempDest;
                            try { tempEdge.weight = Integer.parseInt(townTemp[i]); } catch (NumberFormatException nfe) { nfe.printStackTrace(); }
                            temp.edges.add(tempEdge);
                        }
                    }
                    insertTown(temp);
                }
            }
            scnr.close();
      }
      catch(Exception e) {
            System.out.println(e);
      }
    }

    // prints the graph in the form of a 
    // adjacency list
    public void printGraph(){

        if(isEmpty()){
            System.out.println("The Graph is empty");
        }else{
            for(Node nd : graph){
                System.out.println("\n"+nd.label);
                for (Edge edg : nd.edges) {
                    System.out.print(" --> "+edg.destination.label+"("+edg.weight+")");
                }
            }
        }
    }

    // delete any town that is supplied to this method as an 
    // argument. Delete all edges to all other towns before deleting this town
    public void deleteTown(String town){
        Node townNd = null;
        for (Node nd : graph) {
            if(nd.label.equals(town)) townNd = nd;
        }
        if(townNd == null){
            System.out.print("Town not found");
        }else{
            for (Node nd : graph) {
               deleteEdge(townNd, nd);
            }
        }

    }

    // pass a list of towns as arguments to this method which declares
    // the list as neighbors of this town.
    public void assignNeighbours(){}

    // create a minimum spanning tree and print such tree for us to see
    public void showMST(){}

    // defines a vertex in a graph
    public class Node{
        private String label;
        private List<Edge> edges = new ArrayList<Edge>();
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
        //test.printGraph();
        test.deleteTown("Gaborone");
        test.printGraph();

    }

}