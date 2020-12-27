import java.util.HashMap;

public class longestSubstringWithAtMost2DistinctChars {

  public static int lengthOfLongestSubstringTwoDistinct(String s){
    int windowStart = 0, windowEnd = 0, n = s.length(), longestWindow = 1;
    if (n < 1) return 0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    System.out.println(s);
    map.put(s.charAt(0), 1);
    while(windowStart <= windowEnd &&  windowEnd < n-1){
      char incoming = s.charAt(windowEnd+1);
      System.out.print("incoming "+incoming +" ");
      // case 1: map contains key and only 2 unique chars found or map size is less than 2-> increase windowsize and add to map
      if(map.containsKey(incoming) && map.size() <= 2 || map.size() < 2) {
        map.put(incoming, map.getOrDefault(incoming, 0)+1);
        windowEnd+=1;
        longestWindow = Math.max(longestWindow, windowEnd-windowStart+1);
        System.out.println("Increasing Window! Start: " + windowStart + " End: "+ windowEnd + " longest: " + longestWindow);
      } else {
      // case 2: map is full -> move window over and update incoming and outgoing characters in map
        char outgoing = s.charAt(windowStart);
        windowStart += 1;
        windowEnd += 1;
        map.put(incoming, map.getOrDefault(incoming, 0)+1);
        int outgoing_val = map.get(outgoing)-1;
        if (outgoing_val == 0)
          map.remove(outgoing);
        else
          map.replace(outgoing, outgoing_val);

        System.out.println("Map full, moving window. Exiting: " + outgoing + "(" + outgoing_val + ") Start: " + windowStart + " End: " + windowEnd);
      }
    }
    return longestWindow;
  }
  public static void main(String[] args){
    String test = "abcabcabc";
    System.out.println(
      lengthOfLongestSubstringTwoDistinct(test)
    );
  }
}
