package string;

public class CountAndSay {

  /**
   * Check video for this problem
   */
  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }

    String s = countAndSay(n - 1);

    StringBuilder str = new StringBuilder();

    if (n == 1) {
      return "1";
    }

    if (s.length() == 1) {
      return "1" + s;
    }

    int counter = 1;

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        counter++;
      } else {
        str.append(counter);
        str.append(s.charAt(i));
        counter = 1;
      }
    }

    str.append(counter);
    str.append(s.charAt(s.length() - 1));

    return str.toString();
  }


  public static void main(String[] args) {

  }
}
