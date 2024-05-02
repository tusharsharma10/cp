package graphs.matrix.questions;

import java.util.*;

public class NumDistinctIslands {


  public void dfs(int currRow, int currCol, int[][] vis, int[][] arr, List<String> list, int row0,
      int col0) {
    vis[currRow][currCol] = 1;
    list.add(toString(currRow - row0, currCol - col0));
    int n = arr.length;
    int m = arr[0].length;

    int[] delRow = {-1, 0, 1, 0};
    int[] delCol = {0, -1, 0, 1};

    for (int i = 0; i < 4; i++) {
      int nrow = currRow + delRow[i];
      int ncol = currCol + delCol[i];

      if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && arr[nrow][ncol] == 1
          && vis[nrow][ncol] == 0) {
        dfs(nrow, ncol, vis, arr, list, row0, col0);
      }

    }
  }

  private String toString(int r, int c) {
    return Integer.toString(r) + " " + Integer.toString(c);
  }

  public int distinctIsland(int[][] arr, int n, int m) {

    int rows = arr.length;
    int cols = arr[0].length;

    int vis[][] = new int[rows][cols];

    Set<List<String>> set = new HashSet<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (vis[i][j] == 0 && arr[i][j] == 1) {
          List<String> l = new ArrayList<>();
          dfs(i, j, vis, arr, l, i, j);
          set.add(l);
        }
      }
    }

    return set.size();
  }

}

class Pair {

  int first;
  int second;

  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}
