package graphs.matrix.questions;

public class FloodFill {

  public static void main(String[] args) {
    floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);
  }

  public static int[][] floodFill(int[][] image, int startRow, int startColumn, int color) {
    boolean[][] visited = new boolean[image.length][image[0].length];
    explore(image, startRow, startColumn, color, visited, image.length, image[0].length,
        image[startRow][startColumn]);
    return image;
  }


  public static void explore(int[][] image, int startRow, int startColumn, int color,
      boolean[][] visited, int rowLen,
      int colLen, int startColor) {

    if (image[startRow][startColumn] != startColor || visited[startRow][startColumn]) {
      return;
    }

    visited[startRow][startColumn] = true;

    if (startRow + 1 < rowLen) {
      if (image[startRow][startColumn] == startColor) {
        image[startRow][startColumn] = color;
      }

      explore(image, startRow + 1, startColumn, color, visited, rowLen, colLen, startColor);
    }

    if (startRow - 1 >= 0) {
      if (image[startRow][startColumn] == startColor) {
        image[startRow][startColumn] = color;
      }
      explore(image, startRow - 1, startColumn, color, visited, rowLen, colLen, startColor);
    }

    if (startColumn + 1 < colLen) {
      if (image[startRow][startColumn] == startColor) {
        image[startRow][startColumn] = color;
      }
      explore(image, startRow, startColumn + 1, color, visited, rowLen, colLen, startColor);
    }

    if (startColumn - 1 >= 0) {
      if (image[startRow][startColumn] == startColor) {
        image[startRow][startColumn] = color;
      }
      explore(image, startRow, startColumn - 1, color, visited, rowLen, colLen, startColor);
    }
  }


}
