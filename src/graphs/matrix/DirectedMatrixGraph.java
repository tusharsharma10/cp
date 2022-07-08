package graphs.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class DirectedMatrixGraph {

  int mat[][];
  int v;
  int e;


  public DirectedMatrixGraph(int v) {
    this.v = v;
    mat = new int[v][v];
    for (int i = 0; i < v; i++) {
      mat[i] = new int[v];
    }
  }


  public void setMatrixGraph(int edgeGraph[][]) {

    for (int i = 0; i < edgeGraph.length; i++) {
      int u = edgeGraph[i][0];
      int v = edgeGraph[i][1];
      mat[u][v] = 1;

    }

  }

  public int[][] getMatrixGraph(){
    return this.mat;
  }


  public void addEdge(int a, int b) {
    mat[a][b] = 1;
  }


}
