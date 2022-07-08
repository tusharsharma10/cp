package graphs.adjList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Undirected Graph
 * if a,b edge exists then b,a edge exists
 */
public class UndirectedGraph {


  Map<Integer, List<Integer>> adjList = new HashMap<>();
  int v;
  int e;


  public UndirectedGraph(int v) {
    this.v = v;

    for (int i = 0; i < v; i++) {
      adjList.put(i, new ArrayList<>());
    }
  }


  public void addEdge(int a, int b) {

    adjList.get(a).add(b);
    adjList.get(b).add(a);

  }

  public void convertToDirectedGraph(int mat[][]) {

    for (int i = 0; i < mat.length; i++) {
      adjList.get(mat[i][0]).add(mat[i][1]);
      adjList.get(mat[i][1]).add(mat[i][0]);
    }

  }


  public void bfs(int startVertex) {

    boolean visited[] = new boolean[v];
    Queue<Integer> q = new LinkedList<>();
    q.add(startVertex);
      visited[startVertex]=true;
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
   * prints dfs from start vertex
   */
  public void dfs(int startVertex) {

    boolean visited[] = new boolean[v];

    dfs(startVertex, visited);
    System.out.println();
  }

  private void dfs(int start, boolean visited[]) {

    visited[start] = true;
    System.out.print(start + "->");

    for (int i = 0; i < adjList.get(start).size(); i++) {

      if (!visited[adjList.get(start).get(i)]) {
        dfs(adjList.get(start).get(i), visited);
      }
    }
  }

  public void printAdjList() {
    System.out.println(adjList);
  }


}
