package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPath {


  public int[] shortestPathInDAG(int n, int m, int[][] edges) {
    Map<Integer, List<WtPair>> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      WtPair p = new WtPair(edges[i][1], edges[i][2]);
      int x = edges[i][0];
      map.get(x).add(p);
    }

    List<Integer> topoSort = topoSort(n, map);

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE / 2);
    dist[0] = 0;

    for (int source : topoSort) {
      for (WtPair p : map.get(source)) {

        if (dist[source] + p.weight < dist[p.node]) {
          dist[p.node] = p.weight + dist[source];
        }

      }
    }

    for (int i = 0; i < dist.length; i++) {
      if (dist[i] >= Integer.MAX_VALUE / 2) {
        dist[i] = -1;
      }
    }

    return dist;
  }

  public int[] shortestPathUndirectedGraph(int n, int[][] edges, int src) {
    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int start = edges[i][0];
      int end = edges[i][1];
      map.get(start).add(end);
      map.get(end).add(start);
    }

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE / 2);
    dist[src] = 0;

    Queue<Integer> q = new LinkedList<>();
    q.add(src);

    boolean[] vis = new boolean[n];
    vis[src] = true;

    while (!q.isEmpty()) {
      int node = q.poll();
      vis[node] = true;
      for (int s : map.get(node)) {
        if (!vis[s]) {
          q.add(s);
        }
        dist[s] = Math.min(dist[s], dist[node] + 1);

      }
    }

    for (int i = 0; i < dist.length; i++) {
      if (dist[i] >= Integer.MAX_VALUE / 2) {
        dist[i] = -1;
      }
    }

    return dist;
  }

  public static void main(String[] args) {
    ShortestPath s = new ShortestPath();
    // s.shortestPathInDAG(4, 4, new int[][]{{2, 1, 5}, {0, 2, 3}, {0, 1, 2}, {2, 3, 1}});
    // s.shortestPathInDAG(3, 3, new int[][]{{2, 0, 4}, {0, 1, 3}, {2, 1, 2}});

    s.shortestPathUndirectedGraph(4, new int[][]{{0, 1}, {0, 3}, {2, 3}}, 0);
  }

  public List<Integer> topoSort(int v, Map<Integer, List<WtPair>> map) {
    int[] indegree = new int[v];

    List<Integer> topoSort = new ArrayList<>();

    for (int i = 0; i < v; i++) {
      for (WtPair val : map.get(i)) {
        indegree[val.node]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < v; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    while (!q.isEmpty()) {

      int node = q.poll();
      topoSort.add(node);

      for (WtPair x : map.get(node)) {
        indegree[x.node]--;
        if (indegree[x.node] == 0) {
          q.add(x.node);
        }
      }

    }

    return topoSort;
  }

}

class WtPair {

  int node;
  int weight;

  public WtPair(int node, int w) {
    this.node = node;
    this.weight = w;
  }

}
