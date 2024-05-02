package graphs.matrix.questions;

public class NumIslands {

  public static int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    // visited matrix
    boolean[][] visited = new boolean[m][n];

    int ans = 0;

    for (int i = 0; i < m; i++) {

      for (int j = 0; j < n; j++) {

        if (grid[i][j] == '1' && !visited[i][j]) {
          ans += exploreNumIslands(i, j, grid, visited);
        }

      }
    }

    return ans;
  }

  private static int exploreNumIslands(int i, int j, char[][] grid, boolean[][] visited) {
    int m = grid.length;
    int n = grid[0].length;

    if (grid[i][j] == '0' || visited[i][j] == true) {
      return 0;
    }

    visited[i][j] = true;


    if (i + 1 < m) {
      exploreNumIslands(i + 1, j, grid, visited);
    }
    if (i - 1 >= 0) {
      exploreNumIslands(i - 1, j, grid, visited);
    }
    if (j + 1 < n) {
      exploreNumIslands(i, j + 1, grid, visited);
    }
    if (j - 1 >= 0) {
      exploreNumIslands(i, j - 1, grid, visited);
    }
    return 1;
  }


  public static void main(String[] args) {

  }

}
