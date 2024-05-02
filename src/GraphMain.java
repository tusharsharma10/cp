import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphMain {

  public static void main(String[] args) {

  }

  class Node {

    int airport;
    int cost;
    int stops;

    public Node(int airport, int cost, int stops) {
      this.airport = airport;
      this.cost = cost;
      this.stops = stops;
    }
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    // Create adjacency list representation of the graph
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] flight : flights) {
      graph.computeIfAbsent(flight[0], k -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
    }

    // Initialize a priority queue (min heap) to store the cheapest prices
    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
    pq.offer(new Node(src, 0, 0));

    // Dijkstra's algorithm
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int airport = node.airport;
      int cost = node.cost;
      int stops = node.stops;

      if (airport == dst) {
        return cost; // Found the destination
      }

      if (stops > K) {
        continue; // Skip if number of stops exceeds K
      }

      // Relax all outgoing flights from the current airport
      List<int[]> neighbors = graph.getOrDefault(airport, new ArrayList<>());
      for (int[] neighbor : neighbors) {
        int nextAirport = neighbor[0];
        int nextCost = cost + neighbor[1];
        pq.offer(new Node(nextAirport, nextCost, stops + 1));
      }
    }

    return -1; // Destination not reachable within K stops
  }

  public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[src] = 0;
    for (int i = 0; i <= k; i++) {
      if (isRoutePossible(distance, flights)) {
        break;
      }
    }
    return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
  }

  private boolean isRoutePossible(int[] dist, int[][] flights) {
    int[] copy = Arrays.copyOf(dist, dist.length);
    boolean result = true;

    for (int[] flight : flights) {
      int src = flight[0];
      int dst = flight[1];
      int cost = flight[2];

      if (copy[src] < Integer.MAX_VALUE && dist[dst] > dist[src] + cost) {
        dist[dst] = cost + copy[src];
        result = false;
      }
    }
    return result;
  }

}
