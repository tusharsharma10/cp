package graphs.matrix.questions;

import java.util.*;

public class RottingOranges {

  static class Pair {

    int row;
    int col;
    int time;

    public Pair(int r, int c, int t) {
      this.row = r;
      this.col = c;
      this.time = t;
    }

  }

  public static int orangesRotting(int[][] grid) {
    Queue<Pair> q = new LinkedList<>();

    int m = grid.length;
    int n = grid[0].length;

    int[][] visited = new int[m][n];

    int countFresh = 0;

    // prepare queue
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          Pair p = new Pair(i, j, 0);
          q.add(p);
          visited[i][j] = 2;
        } else {
          visited[i][j] = 0;
        }
        if (grid[i][j] == 1) {
          countFresh++;
        }
      }
    }


    int time = 0;
    int count = 0;

    int[] drow = {-1, 0, 1, 0};
    int[] dcol = {0, 1, 0, -1};

    while (!q.isEmpty()) {
      Pair curr = q.poll();
      int r = curr.row;
      int c = curr.col;
      int t = curr.time;

      time = Math.max(t, time);

      for (int i = 0; i < 4; i++) {
        int nrow = r + drow[i];
        int ncol = c + dcol[i];

        if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && visited[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
          q.add(new Pair(nrow, ncol, t + 1));
          visited[nrow][ncol] = 2;
          count++;
        }
      }
    }

    if (count != countFresh) {
      return -1;
    }

    return time;
  }


  public static Map<Integer, List<Integer>> matrixToAdjList(int[][] mat) {
    Map<Integer, List<Integer>> adList = new HashMap<>();

    for (int i = 0; i < mat.length; i++) {
      List<Integer> l1 = new ArrayList<>();
      adList.put(i, l1);
      for (int j = 0; j < mat[0].length; j++) {
        if (mat[i][j] == 1) {
          l1.add(j);
        }
      }
    }

    return adList;
  }

  public static void main(String[] args) {

  }

}
