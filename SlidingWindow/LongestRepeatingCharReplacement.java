package SlidingWindow;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
    Intution: 
    find the largest repeating block
    Find the substring with most repeating character, remaining chars i.e windowSize - maxFreq of repeating character
    should be <=k, so that we can turn them to the same character, we maximize that
 */

public class LongestRepeatingCharReplacement {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> mp  = new HashMap<>();
        int n = s.length();
        int i=0;
        int j=0;
        int max = Integer.MIN_VALUE;
        while(j<n){
            char b = s.charAt(j);
            if(mp.containsKey(b)){
                mp.put(b, mp.get(b)+1);
            }else{
                mp.put(b, 1);
            }
            //possible answer - when windowLen - max(value) <=k
            while(((j-i+1)-(Collections.max(mp.values())))>k){
                 char a = s.charAt(i);
                 mp.put(a, mp.get(a)-1);
                 i++;
                
            }
            max = Math.max(max, j-i+1);
            j++;
            
        }
        return max;
    }
}
