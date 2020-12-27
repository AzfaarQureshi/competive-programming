import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.HashMap;

public class cheermote {
  
  public static String[] tokenizer(int min_cheermote_amount, String[] valid_cheermotes, String messages) {
    String[] ERR = new String[]{"NO_CHEERS"};
    if (min_cheermote_amount <= 0 || messages.length() <= 1 || valid_cheermotes.length == 0)
      return ERR;
    
    StringBuilder regexDigit = new StringBuilder();
    StringBuilder regexNoDigit = new StringBuilder();
    HashMap<String, Integer> emoteIndexMap = new HashMap<String, Integer>();
    HashMap<String, Integer> tokenized = new HashMap<String, Integer>();

    // build regex of valid_cheermotes
    for (int i=0, n=valid_cheermotes.length; i<n; ++i){
      if (i == 0)
        regexDigit.append("("+valid_cheermotes[i]+"\\d+");
      else
        regexDigit.append("|"+valid_cheermotes[i]+"\\d+");
      if (i == n-1)
        regexDigit.append(")");
    }
    Pattern digitPat = Pattern.compile(regexDigit.toString());
    
    // populate emote Index Map
    for (int i = 0, n = valid_cheermotes.length; i < n; ++i){
      emoteIndexMap.put(valid_cheermotes[i], i);
    }

    // determine cheermote amounts
    for (String message : messages.split(",")){
      Matcher digitMatcher = digitPat.matcher(message);
      if(!digitMatcher.find()) continue;
      digitMatcher.reset();
      
      String cheermote = "";
      int totalCheerVal = 0;
      boolean valid = true;
      // now we know that this string contains a cheermote 
      // we cycle through all the cheermote matches
      while(digitMatcher.find()){
        String key = digitMatcher.group().replaceAll("[0-9]+", "");
        String valString = digitMatcher.group().replaceAll("[^0-9]+", "");
        Integer val = Integer.parseInt(valString);
        totalCheerVal += val;

        // update cheerval
        if(val < min_cheermote_amount || val >= 10000 || totalCheerVal >= 100000){
          valid = false;
          break;
        }
        
        // update smallest cheermote
        if(emoteIndexMap.get(key) < emoteIndexMap.getOrDefault(cheermote, Integer.MAX_VALUE))
          cheermote = key;
      }
      // update hashmap with data
      if(valid)
        tokenized.put(cheermote, tokenized.getOrDefault(cheermote, 0)+totalCheerVal);
    }

    // compute return value from hashmap
    String[] retVal = new String[tokenized.size()];
    int i = 0;
    for(Map.Entry<String, Integer> entry : tokenized.entrySet()){
      retVal[i++] = entry.getKey()+entry.getValue();
    }
    return retVal;
  }
  public static void main(String[] args){
    System.out.println(Arrays.toString(tokenizer(5, new String[]{"cheer", "party", "pogchamp"}, 
            "cheer1 cheer10 pogchamp1 wow!, cheer5 cheer10 this is amazing, party50 party50 lets have a party!, pogchamp100 cheer50 tough question :)"))); 
  }
}
