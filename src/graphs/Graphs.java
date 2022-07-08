package graphs;

import graphs.adjList.DirectedGraph;
import graphs.adjList.GraphAlgos;
import graphs.matrix.DirectedMatrixGraph;

public class Graphs {

  public static void main(String[] args) {
    int[][] graph = {{1, 2}, {1, 3}, {2, 5}, {3, 5}, {2, 4}};

    DirectedGraph d = createAdjGraph(graph, 6);
    System.out.println(GraphAlgos.hasPath(1, 4, d.getAdjList().size(), d.getAdjList()));;
  }

  private static DirectedGraph createAdjGraph(int[][] edgeGraph, int v) {
    DirectedGraph d = new DirectedGraph(v);
    d.setAdjList(edgeGraph);
    return d;
  }


  private static void matrixDGOperations() {

    int[][] edgeGraph = {{1, 2}, {1, 3}, {2, 5}, {3, 5}, {1, 4}, {2, 4}};

    DirectedMatrixGraph d = new DirectedMatrixGraph(6);
    d.setMatrixGraph(edgeGraph);
  }

}
