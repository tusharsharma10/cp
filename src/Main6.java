import java.util.ArrayList;
import java.util.*;

public class Main6 {


  public static int solve(ArrayList<Integer> A, int B) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int x : A) {
      map.put(x, map.getOrDefault(x, 0) + 1);
    }

    for (int i = 0; i < A.size(); i++) {
      int x = A.get(i);
      int num = B + x;
      if (map.containsKey(num)) {
        if (B == 0) {
          if (map.get(num) >= 2) {
            return 1;
          } else {
            return 0;
          }
        }
        return 1;
      }
    }

    return 0;
  }

  public static int num(ArrayList<Integer> A) {

    int[] left = new int[A.size()];
    int max = A.get(0);
    left[0] = max;
    for (int i = 1; i < left.length; i++) {
      max = Math.max(max, A.get(i));
      left[i] = max;
    }

    int n = A.size();
    int count = 1;
    for (int i = 1; i < n; i++) {
      int num = A.get(i);
      if (num > left[i - 1]) {
        count++;
      }
    }

    return count;

  }

  public static int diffPossible(ArrayList<Integer> A, int B) {
    int left = 0;
    int right = 1;
    int n = A.size();

    while (right < n) {
      int diff = A.get(right) - A.get(left);
      if (diff == B && left != right) {
        return 1; // Found the required indices
      } else if (diff < B) {
        right++; // Increment right pointer to increase the difference
      } else {
        left++; // Increment left pointer to decrease the difference
        if (left == right) {
          right++; // Ensure left and right pointers are not equal
        }
      }
    }

    return 0; // No such pair found
  }


  public static void main(String[] args) {
    System.out.println(diffPossible(new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4)), 0));
  }


  public static void mergeSort(int[] arr, int low, int high) {

    if (low >= high) {
      return;
    }
    int mid = (low + high) / 2;
    mergeSort(arr, low, mid);
    mergeSort(arr, mid + 1, high);
    merge(arr, low, mid, high);

  }

  public static void merge(int[] arr, int low, int mid, int high) {

    List<Integer> tempList = new ArrayList<>();

    int left = low;
    int right = mid + 1;

    while (left <= mid && right <= high) {
      if (arr[left] > arr[right]) {
        tempList.add(arr[right]);
        right++;
      } else {
        tempList.add(arr[left]);
        left++;
      }
    }

    while (left <= mid) {
      tempList.add(arr[left]);
      left++;
    }

    while (right <= high) {
      tempList.add(arr[right]);
      right++;
    }

    for (int i = low; i <= high; i++) {
      arr[i] = tempList.get(i - low);
    }

  }

}
