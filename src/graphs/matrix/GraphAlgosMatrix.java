package graphs.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgosMatrix {

  public void dfs(int start, int mat[][], int v) {
    boolean visited[] = new boolean[mat.length];
    dfsRec(start, visited, mat, v);
    System.out.println();
  }

  private void dfsRec(int start, boolean visited[], int mat[][], int v) {

    visited[start] = true;
    System.out.print(start + "->");

    for (int i = 0; i < v; i++) {
      if (mat[start][i] == 1 && !visited[i]) {
        dfsRec(i, visited, mat, v);
      }
    }

  }

  public void bfs(int start, int v, int mat[][]) {

    boolean visited[] = new boolean[v];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {

      int x = q.poll();
      System.out.print(x + "->");

      for (int i = 0; i < v; i++) {
        if (mat[x][i] == 1 && !visited[i]) {

          q.add(i);
          visited[i] = true;

        }
      }

    }
    System.out.println();
  }

}
