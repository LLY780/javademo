import java.util.*;

public class WeightedGraph<T, W> {

    private Map<T, HashMap<T, W>> map = new HashMap<>();
    private int numEdges = 0;

    public int getVertexCount() {
        return map.keySet().size();
    }

    public int getEdgesCount() {
        return numEdges;
    }

    public HashMap<T,W> getEdges(T vertex) {
        return map.get(vertex);
    }

    public Set<T> getVertices() {
        return map.keySet();
    }

    public boolean hasVertex(T vertex) {
        return map.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        return map.get(source).containsKey(destination);
    }

    public void addVertex(T vertex) {
        map.putIfAbsent(vertex,new HashMap<T, W>());
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, null);
    }

    public void addEdge(T source, T destination, W weight) {
        addVertex(source);
        addVertex(destination);

        if (!hasEdge(source,destination)) {
            map.get(source).put(destination, weight);
            numEdges++;
        }
    }

    public String toString() {
        String s = "Graph:\n";
        for (T key:map.keySet()) {
            s += key + ": " + map.get(key).toString() + "\n\n";
        }
        return s;
    }
} 