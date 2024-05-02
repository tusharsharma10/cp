package array;

public class MaxProductSubarray {

  public static int subarrayWithMaxProduct(int[] arr) {
    // Return 0 if array is empty
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int maxProduct = arr[0];
    int minProduct = arr[0];
    int result = arr[0];

    for (int i = 1; i < arr.length; i++) {
      int tempMax = maxProduct;
      int tempMin = minProduct;

      maxProduct = Math.max(arr[i], Math.max(tempMax * arr[i], tempMin * arr[i]));
      minProduct = Math.min(arr[i], Math.min(tempMax * arr[i], tempMin * arr[i]));

      result = Math.max(result, maxProduct);
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(subarrayWithMaxProduct(new int[]{-1, 3, 0, -4, 3}));
  }

}
