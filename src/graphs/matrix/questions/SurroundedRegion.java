package graphs.matrix.questions;

import java.util.*;

/**
 * Start with boundary O's and mark them can't be converted
 */

public class SurroundedRegion {

  public void dfs(int row, int col, char[][] board, int[][] vis, int[] delRow, int[] delCol) {
    vis[row][col] = 1;
    int n = board.length;
    int m = board[0].length;

    // check for top, right, bottom, left
    for (int i = 0; i < 4; i++) {
      int nrow = row + delRow[i];
      int ncol = col + delCol[i];
      // check for valid coordinates and unvisited Os
      if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
          && vis[nrow][ncol] == 0 && board[nrow][ncol] == 'O') {
        dfs(nrow, ncol, board, vis, delRow, delCol);
      }
    }
  }

  public void solve(char[][] board) {
    int n = board.length;
    int m = board[0].length;
    int delrow[] = {-1, 0, +1, 0};
    int delcol[] = {0, 1, 0, -1};
    int vis[][] = new int[n][m];

    // traverse first row and last row
    for (int j = 0; j < m; j++) {
      // check for unvisited Os in the boundary rws
      // first row
      if (vis[0][j] == 0 && board[0][j] == 'O') {
        dfs(0, j, board, vis, delrow, delcol);
      }

      // last row
      if (vis[n - 1][j] == 0 && board[n - 1][j] == 'O') {
        dfs(n - 1, j, board, vis, delrow, delcol);
      }
    }

    // traverse first col and last col
    for (int i = 0; i < n; i++) {
      // check for unvisited Os in the boundary columns
      // first column
      if (vis[i][0] == 0 && board[i][0] == 'O') {
        dfs(i, 0, board, vis, delrow, delcol);
      }

      // last column
      if (vis[i][m - 1] == 0 && board[i][m - 1] == 'O') {
        dfs(i, m - 1, board, vis, delrow, delcol);
      }
    }

    // if unvisited O then convert to X
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (vis[i][j] == 0 && board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }


  }

  public static void solve2(char[][] board) {

    Queue<int[]> q = new LinkedList<>();
    int n = board.length;
    int m = board[0].length;
    boolean[][] vis = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O' && (i == n - 1 || i == 0 || j == m - 1 || j == 0)) {
          vis[i][j] = true;
          q.add(new int[]{i, j});
        }
      }
    }

    int[][] directions = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    while (!q.isEmpty()) {
      int[] posn = q.poll();
      int row = posn[0];
      int col = posn[1];

      for (int i = 0; i < 4; i++) {
        int nRow = row + directions[i][0];
        int nCol = col + directions[i][1];

        if (nRow < n && nRow >= 0 && nCol < m && nCol >= 0 && !vis[nRow][nCol]
            && board[nRow][nCol] == 'O') {
          q.add(new int[]{nRow, nCol});
          vis[nRow][nCol] = true;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!vis[i][j] && board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }

  }

}
