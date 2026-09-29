import java.io.*;
import java.util.*;

class Main {

    public static WeightedGraph<String, Double> graph = new WeightedGraph<>();
    public static HashMap<String, Location> locations = new HashMap<>();
    public static Djikstra d = new Djikstra(graph, locations);
    public static Scanner r = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Street Searcher!");
        System.out.print("Please enter a file name: ");

        File f;

        try {
            f = new File(r.nextLine());
            if (!f.exists()) {
                throw new FileNotFoundException();
            }
            Scanner v = new Scanner(f);
            while (v.hasNextLine()) {
                String[] info = v.nextLine().split(" ");
                String[] names = info[3].split("--");
                double[][] coords = {{Double.parseDouble(info[0].split(",")[1]),Double.parseDouble(info[0].split(",")[0])},
                        {Double.parseDouble(info[1].split(",")[1]),Double.parseDouble(info[0].split(",")[0])}}; // latitude, longitude
                double distance = Double.parseDouble(info[2]);
                Location s = new Location(names[0],coords[0][0],coords[0][1]);
                Location d = new Location(names[1],coords[1][0],coords[1][1]);

                locations.putIfAbsent(names[0],s);
                locations.putIfAbsent(names[1],d);

                graph.addEdge(names[0],names[1],distance);
            }
        }
        catch (FileNotFoundException e) {}



        // some other stuff

        System.out.println("\nNetwork Loaded!");
        System.out.println("Loaded " + graph.getEdgesCount() + " roads.");
        System.out.println("Loaded " + graph.getVertexCount() + " locations.\n");

        System.out.println("LOCATIONS:");
        for (String location:graph.getVertices()) {
            System.out.println(location);
        }

        // System.out.println(graph.getEdges("Bryant_Park"));
        // System.out.println(graph.getEdges("Bronx_Zoo"));

        // System.out.print("\nEnter a starting location: ");
        // String start = r.nextLine();
        String start = "Bryant_Park";
        // System.out.print("Enter an ending location: ");
        // String end = r.nextLine();
        String end = "Bronx_Zoo";

        d.getShortestPath(start,end);

    }

}