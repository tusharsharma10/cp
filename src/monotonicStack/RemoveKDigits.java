package monotonicStack;

import java.util.Stack;

public class RemoveKDigits {

  public static String removeKDigits(String num, int k) {

    // Create a stack to store the digits
    Stack<Character> stack = new Stack<>();

    // Iterate through each digit in the number
    for (char digit : num.toCharArray()) {
      // While there are still digits to remove and the current digit is less than the top digit of the stack
      while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
        stack.pop(); // Remove the top digit from the stack
        k--; // Decrease the number of digits to remove
      }
      // Push the current digit onto the stack
      stack.push(digit);
    }

    // Remove the remaining k digits from the end of the stack
    while (k > 0 && !stack.isEmpty()) {
      stack.pop();
      k--;
    }

    // Construct the result string from the stack
    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
      // Insert each digit at the beginning of the string to maintain the correct order
      result.insert(0, stack.pop());
    }

    // Remove leading zeros
    while (result.length() > 1 && result.charAt(0) == '0') {
      result.deleteCharAt(0);
    }

    // If the result is empty, return "0", otherwise return the result string
    return result.length() == 0 ? "0" : result.toString();
  }

  public static void main(String[] args) {
    removeKDigits("1432219", 3);
  }

}
