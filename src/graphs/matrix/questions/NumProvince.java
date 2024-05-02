package graphs.matrix.questions;

import java.util.Arrays;

public class NumProvince {


  public int findCircleNum(int[][] mat) {
    boolean[] visited = new boolean[mat.length];
    Arrays.fill(visited, false);
    int count = 0;
    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        count++;
        dfsHelper(i, mat, visited);
      }
    }
    return count;
  }

  private void dfsHelper(int curr, int[][] mat, boolean[] visited) {
    visited[curr] = true;
    for (int i = 0; i < mat.length; i++) {
      if (mat[curr][i] == 1 && !visited[i]) {
        dfsHelper(i, mat, visited);
      }
    }
  }


  public static void main(String[] args) {

  }

}
