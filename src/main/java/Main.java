public class Main {
  public static void main(String[] args) {
    //
    boolean anagram = isPalindrome("race a car");
    System.out.println(anagram);
  }

  public static boolean isPalindrome(String s) {
    StringBuffer sb = new StringBuffer();
    for (char c : s.toLowerCase().toCharArray()) {
      if (97<=c && c<=122) {
        sb.append(c);
      }
    }
    return sb.equals(sb.reverse());
  }
}
