import java.util.*;

public class Djikstra {
    private static WeightedGraph<String,Double> graph;
    private static HashMap<String, Location> locations;
    private static PriorityQueue<Location> queue = new PriorityQueue<>();

    public Djikstra(WeightedGraph<String,Double> graph, HashMap<String,Location> locations) {
        this.graph = graph;
        this.locations = locations;
    }

    public void getShortestPath(String start, String end) {
        for (String location:locations.keySet()) {
            Location l = locations.get(location);
            if (!location.equals(start)) {
                l.setDistance(Double.POSITIVE_INFINITY);
                queue.add(l);
            }
        }
        Location source = locations.get(start);
        source.setDistance(0);
        queue.add(source);

        while (!queue.isEmpty() || queue.contains(locations.get(end))) {
            Location current = queue.poll();
            HashMap<String,Double> neighbors = graph.getEdges(current.getName());
            for (String n:neighbors.keySet()) {
                Double distance = neighbors.get(n);
                Location neighbor = locations.get(n);
                if (current.getDistance() + distance < neighbor.getDistance() && queue.contains(neighbor)) {
                    neighbor.setDistance(current.getDistance()+distance);
                    neighbor.setPrevious(current);
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        Location current = locations.get(end);
        double total = current.getDistance();
        System.out.println("\nTotal Distance: " + String.format("%.2f",total) + " miles");
        Location previous = current.getPrevious();
        ArrayList<Location> path = new ArrayList<>();

        while (previous != null) {
            path.add(0,current);
            current = previous;
            previous = previous.getPrevious();
        }
        path.add(0,current);
        for (int i = 0; i < path.size()-1; i++) {
            System.out.println(path.get(i).getName() + " --> " + path.get(i+1).getName() + "(" + String.format("%.2f",path.get(i+1).getDistance()-path.get(i).getDistance()) + " miles)");
        }

    }

}