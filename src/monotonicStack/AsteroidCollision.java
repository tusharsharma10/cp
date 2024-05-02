package monotonicStack;

import java.util.Stack;

public class AsteroidCollision {

  public static int[] asteroidCollision(int[] nums) {

    Stack<Integer> stack = new Stack<>();

    for (int asteroid : nums) {
      // If asteroid is moving to the right
      if (asteroid > 0) {
        stack.push(asteroid);
      }
      // If asteroid is moving to the left
      else {
        // Destroy smaller asteroids moving to the right
        while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
          stack.pop();
        }
        // If no more asteroids moving to the right or all collided, add current asteroid
        if (stack.isEmpty() || stack.peek() < 0) {
          stack.push(asteroid);
        }
        // If asteroids are of equal size, destroy both, Don't push the current asteroid onto the stack
        else if (stack.peek() == Math.abs(asteroid)) {
          stack.pop();
        }
      }
    }

    int[] result = new int[stack.size()];
    for (int i = result.length - 1; i >= 0; i--) {
      result[i] = stack.pop();
    }

    return result;

  }

  public static void main(String[] args) {
    asteroidCollision(new int[]{-2, -2, 1, -2});
  }

}
