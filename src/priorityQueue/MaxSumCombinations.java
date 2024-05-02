package priorityQueue;

import java.util.*;

public class MaxSumCombinations {

  public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int K) {

    int N = A.size();

    // Sort arrays A and B in non-increasing order
    Collections.sort(A, Collections.reverseOrder());
    Collections.sort(B, Collections.reverseOrder());

    // Priority queue to store pairs of sums and their corresponding indices from arrays A and B
    PriorityQueue<PairSum> pq = new PriorityQueue<>(Collections.reverseOrder());

    // Set to keep track of visited pairs of indices
    HashSet<PairMaxSum> visited = new HashSet<>();

    // Initialize priority queue with the maximum sum combinations A[i] + B[j] where A[i] is the largest element in A and B[j] is the largest element in B
    pq.offer(new PairSum(A.get(0) + B.get(0), 0, 0));
    visited.add(new PairMaxSum(0, 0));

    // Result list to store the maximum K sum combinations
    ArrayList<Integer> result = new ArrayList<>();

    // Iterate up to K and extract the maximum sum combinations from the priority queue
    while (K > 0 && !pq.isEmpty()) {
      PairSum max = pq.poll();
      int sum = max.sum;
      int idxA = max.idxA;
      int idxB = max.idxB;
      // Store the maximum sum in the result list
      result.add(sum);

      // Push the next possible sum combinations into the priority queue
      if (idxA + 1 < N && !visited.contains(new PairMaxSum(idxA + 1, idxB))) {
        pq.offer(new PairSum(A.get(idxA + 1) + B.get(idxB), idxA + 1, idxB));
        visited.add(new PairMaxSum(idxA + 1, idxB));
      }
      if (idxB + 1 < N && !visited.contains(new PairMaxSum(idxA, idxB + 1))) {
        pq.offer(new PairSum(A.get(idxA) + B.get(idxB + 1), idxA, idxB + 1));
        visited.add(new PairMaxSum(idxA, idxB + 1));
      }

      K--;
    }

    return result;

  }
}

class PairMaxSum {

  int idxA;
  int idxB;

  public PairMaxSum(int idxA, int idxB) {
    this.idxA = idxA;
    this.idxB = idxB;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PairMaxSum pair = (PairMaxSum) o;
    return idxA == pair.idxA && idxB == pair.idxB;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idxA, idxB);
  }
}

class PairSum implements Comparable<PairSum> {

  int sum;
  int idxA;
  int idxB;

  public PairSum(int sum, int idxA, int idxB) {
    this.sum = sum;
    this.idxA = idxA;
    this.idxB = idxB;
  }

  @Override
  public int compareTo(PairSum other) {
    return Integer.compare(this.sum, other.sum);
  }
}
