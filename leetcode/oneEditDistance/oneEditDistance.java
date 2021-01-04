
public class oneEditDistance {

  public static boolean isOneEditDistance(String s, String t){
    int slen = s.length();
    int tlen = t.length();
    int lendiff = Math.abs(slen-tlen);
    if (lendiff > 1)
      return false;

    if (tlen > slen) return isOneEditDistance(t, s);
    if (tlen == 0 && slen != 0) return true;
    else if (tlen == 0 && slen == 0) return false;
    int diff = 0;
    for(int i = 0; i < slen; ++i){
        if (i == tlen) diff++;
        else if (s.charAt(i) != t.charAt(i)) {
            diff++;
        }
        if (diff > 1) return false;
        else if (s.substring(i+1).equals(t.substring(i))) return true;
        else if (s.substring(i).equals(t.substring(i+1))) return true;
    }
    if (diff == 1) return true;
    else return false;
  }
  public static void main(String args[]){
    // abc abcd
    // "" ""
    // "a" ""
    // "abc" "bc"
    // "abcabcabc" "abcbcabc"
    System.out.println(isOneEditDistance("abcabcabc", "abcbcabc"));
  }
}
