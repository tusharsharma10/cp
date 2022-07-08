package graphs.adjList;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphAlgos {

  /**
   * prints dfs from start vertex
   */
  public static void dfs(int startVertex, int v, Map<Integer, List<Integer>> adjList) {

    boolean visited[] = new boolean[v];

    dfs(startVertex, visited, adjList);
    System.out.println();
  }

  private static void dfs(int start, boolean visited[], Map<Integer, List<Integer>> adjList) {

    visited[start] = true;
    System.out.print(start + "->");

    for (int i = 0; i < adjList.get(start).size(); i++) {

      if (!visited[adjList.get(start).get(i)]) {
        dfs(adjList.get(start).get(i), visited, adjList);
      }
    }
  }


  /**
   * BFS algorithm
   */
  public static void bfs(int startVertex, int v, Map<Integer, List<Integer>> adjList) {

    boolean visited[] = new boolean[v];
    Queue<Integer> q = new LinkedList<>();
    q.add(startVertex);
    visited[startVertex] = true;
    while (!q.isEmpty()) {

      int x = q.poll();
      System.out.print(x + "->");

      for (int i = 0; i < adjList.get(x).size(); i++) {
        if (!visited[adjList.get(x).get(i)]) {
          visited[adjList.get(x).get(i)] = true;
          q.add(adjList.get(x).get(i));
        }

      }


    }

    System.out.println();
  }

  /**
   * has path no cycle graph
   */

  public static boolean hasPath(int start, int end, int v, Map<Integer, List<Integer>> adjList) {

    boolean[] visited = new boolean[v];
    hasPathDfs(start, end, visited, adjList);
    if (visited[end]) {
      return true;
    }
    return false;
  }

  private static void hasPathDfs(int start, int end, boolean visited[],
      Map<Integer, List<Integer>> adjList) {

    if (start == end) {
      visited[end] = true;
      return;
    }

    visited[start] = true;

    for (int i = 0; i < adjList.get(start).size(); i++) {
      if (!visited[adjList.get(start).get(i)]) {
        hasPathDfs(adjList.get(start).get(i), end, visited, adjList);
      }
    }
  }

}
