package graphs.adjList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Directed Graph
 */
public class DirectedGraph {


  Map<Integer, List<Integer>> adjList = new HashMap<>();
  int v;
  int e;


  public DirectedGraph(int v) {
    this.v = v;

    for (int i = 0; i < v; i++) {
      adjList.put(i, new ArrayList<>());
    }
  }


  public void addEdge(int a, int b) {

    adjList.get(a).add(b);

  }

  public Map<Integer, List<Integer>> getAdjList() {
    return this.adjList;
  }

  public void setAdjList(int mat[][]) {

    for (int i = 0; i < mat.length; i++) {
      adjList.get(mat[i][0]).add(mat[i][1]);
    }

  }

  public void printAdjList() {
    System.out.println(adjList);
  }


}
