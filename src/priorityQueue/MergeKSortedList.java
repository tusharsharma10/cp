package priorityQueue;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import linklist.ListNode;

public class MergeKSortedList {

  public ListNode mergeKLists(ListNode[] lists) {

    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

    // Add the heads of all linked lists to the min heap
    for (ListNode node : lists) {
      if (node != null) {
        minHeap.offer(node);
      }
    }

    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    // Merge the lists using the min heap
    while (!minHeap.isEmpty()) {
      ListNode smallest = minHeap.poll();
      tail.next = smallest;
      tail = tail.next;
      if (smallest.next != null) {
        minHeap.offer(smallest.next);
      }
    }

    return dummy.next;
  }


  public static int countInversions(int[] nums) {
    return mergeSort(nums, 0, nums.length - 1);
  }

  public static int mergeSort(int[] nums, int start, int end) {
    int count = 0;

    if (start >= end) {
      return count;
    }

    int mid = (start + end) / 2;
    count += mergeSort(nums, start, mid);
    count += mergeSort(nums, mid + 1, end);
    count += merge(nums, start, mid, end);

    return count;
  }

  public static int merge(int[] nums, int start, int mid, int end) {
    int count = 0;
    List<Integer> ans = new ArrayList<>();
    int left = start;
    int right = mid + 1;

    while (left <= mid && right <= end) {
      if (nums[left] > nums[right]) {
        ans.add(nums[right]);
        count += (mid - left + 1);
        right++;
      } else {
        ans.add(nums[left]);
        left++;
      }
    }

    while (left <= mid) {
      ans.add(nums[left]);
      left++;
    }

    while (right <= end) {
      ans.add(nums[right]);
      right++;
    }

    for (int i = 0; i < ans.size(); i++) {
      nums[i + start] = ans.get(i);
    }
    return count;
  }


  public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {

    PriorityQueue<Integer> pq = new PriorityQueue<>((l1, l2) -> l2 - l1);
    pq.addAll(A);
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = 0; i < B; i++) {
      ans.add(pq.poll());
    }
    return ans;
  }


  public static void main(String[] args) {
    // System.out.println(countInversions(new int[]{2, 4, 1, 3, 5}));

    ArrayList<Integer> a1 = new ArrayList<>(
        Arrays.asList(59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93, 83));
    ArrayList<Integer> b1 = new ArrayList<>(
        Arrays.asList(59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93, 83));
  }

}


