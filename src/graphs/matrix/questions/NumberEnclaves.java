package graphs.matrix.questions;

import java.util.LinkedList;
import java.util.Queue;

public class NumberEnclaves {

  int numberOfEnclaves(int[][] grid) {
    Queue<Pair> q = new LinkedList<Pair>();
    int n = grid.length;
    int m = grid[0].length;
    int vis[][] = new int[n][m];

    // traverse boundary elements and if it is a land then store it in queue
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // first row, first col, last row, last col
        if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
          // if it is a land then store it in queue
          if (grid[i][j] == 1) {
            q.add(new Pair(i, j));
            vis[i][j] = 1;
          }
        }
      }
    }

    int delrow[] = {-1, 0, +1, 0};
    int delcol[] = {0, +1, +0, -1};

    while (!q.isEmpty()) {
      int row = q.peek().first;
      int col = q.peek().second;
      q.remove();

      // traverses all 4 directions
      for (int i = 0; i < 4; i++) {
        int nrow = row + delrow[i];
        int ncol = col + delcol[i];
        // check for valid coordinates and for land cell visit is possible only for grid[nrow][ncol] == 1
        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
            && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
          q.add(new Pair(nrow, ncol));
          vis[nrow][ncol] = 1;
        }
      }

    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // check for unvisited land cell
        if (grid[i][j] == 1 & vis[i][j] == 0) {
          cnt++;
        }
      }
    }


    return cnt;

  }

  class Pair {

    int first;
    int second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }

}