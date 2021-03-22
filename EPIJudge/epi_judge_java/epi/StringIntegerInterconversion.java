package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.lang.Math;

public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    StringBuilder sb = new StringBuilder();
    boolean pos = true;
    if (x < 0) pos = false;
    else if (x == 0) return "0";
    while (x != 0) {
     int digit = Math.abs(x % 10);
     sb.append((char)('0'+digit));
     x /= 10;
    }
    if (!pos) sb.append("-");
    sb = sb.reverse();
    return sb.toString();
  }
  public static int stringToInt(String s) {
    // TODO - you fill in here.
    int sign = 1;
    Integer num = null;
    if (s.charAt(0)== '-') {
      sign*=-1;
    }
    for(int i = 0, n = s.length(); i < n; ++i){
      if (s.charAt(i)<'0' || s.charAt(i) > '9') continue;
      if(num != null) {
        num *= 10;
      } else num = 0;
      num += sign*(s.charAt(i)-'0');
    }
    return num;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
