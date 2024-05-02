package graphs.matrix.questions;

import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSort {


  public static String FindIntersection(String[] strArr) {

    String[] arr1 = strArr[0].split(",");
    String[] arr2 = strArr[1].split(",");

    boolean intersection = false;

    Set<Integer> l = new HashSet<>();

    for (String i : arr1) {
      i = i.trim();
      l.add(Integer.valueOf(i));
    }

    for (String i : arr2) {
      i = i.trim();
      l.add(Integer.valueOf(i));
    }

    if (l.size() == arr1.length + arr2.length) {
      return "false";
    }

    return l.stream().map(r -> String.valueOf(r)).collect(Collectors.joining(","));

  }

  public static void main(String[] args) {
    System.out.println(FindIntersection(new String[]{"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}));
  }


}


