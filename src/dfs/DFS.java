package dfs;

import java.util.ArrayList;
import java.util.List;

public class DFS {

  public static int islandPerimeter(int[][] grid) {

    int m = grid.length;
    int n = grid[0].length;

    boolean[][] visited = new boolean[m][n];

    List<Matrix> islandList = new ArrayList<>();

    for (int i = 0; i < m; i++) {

      for (int j = 0; j < n; j++) {
        if (!visited[i][j]) {
          islandPerimeterDfs(grid, i, j, islandList, visited);
        }
      }
    }

    int per = 0;

    for (Matrix mat : islandList) {

      int i = mat.row;
      int j = mat.col;
      per += 4;
      if (i - 1 >= 0 && grid[i - 1][j] == 1) {
        per -= 1;
      }

      if (j - 1 >= 0 && grid[i][j - 1] == 1) {
        per -= 1;
      }

      if (i + 1 < m && grid[i + 1][j] == 1) {
        per -= 1;
      }

      if (j + 1 < n && grid[i][j + 1] == 1) {
        per -= 1;
      }

    }

    return per;
  }

  static class Matrix{
      public int row;
      public int col;
  }

  private static void islandPerimeterDfs(int[][] grid, int i, int j, List<Matrix> islandList,
      boolean[][] visited) {

    int m = grid.length;
    int n = grid[0].length;

    if (i < 0 || j < 0 || i > m - 1 || j > n - 1 || grid[i][j] != 1 || visited[i][j]) {
      return;
    }

    visited[i][j] = true;

   /* if ((i == 0 && j == 0) || (i == m - 1 && j == n - 1)) {
      map.put(2, 1 + map.getOrDefault(2, 0));
    } else if ((i == 0 && j != 0) || (j == 0 && i != 0) || (i == m - 1 && j != n - 1) || (j == n - 1
        && i != m - 1)) {
      map.put(3, 1 + map.getOrDefault(3, 0));
    } else {
      map.put(4, 1 + map.getOrDefault(4, 0));
    }*/

    Matrix mat = new Matrix();
    mat.row = i;
    mat.col = j;
    islandList.add(mat);

    islandPerimeterDfs(grid, i - 1, j, islandList, visited);
    islandPerimeterDfs(grid, i + 1, j, islandList, visited);
    islandPerimeterDfs(grid, i, j - 1, islandList, visited);
    islandPerimeterDfs(grid, i, j + 1, islandList, visited);

  }


  public static void main(String[] args) {
    int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    System.out.println(islandPerimeter(grid));
  }

}
