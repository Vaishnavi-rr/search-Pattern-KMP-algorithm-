Given two strings, one is a text string txt and the other is a pattern string pat. The task is to print the indexes of all the occurrences of the pattern string in the text string. Use 0-based indexing while returning the indices. 
Note: Return an empty list in case of no occurrences of pattern.

Examples :

Input: txt = "abcab", pat = "ab"
Output: [0, 3]
Explanation: The string "ab" occurs twice in txt, one starts at index 0 and the other at index 3. 
Input: txt = "abesdu", pat = "edu"
Output: []
Explanation: There's no substring "edu" present in txt.
Input: txt = "aabaacaadaabaaba", pat = "aaba"
Output: [0, 9, 12]
Explanation:

Constraints:
1 ≤ txt.size() ≤ 106
1 ≤ pat.size() < txt.size()
Both the strings consist of lowercase English alphabets.

solution 1

  
  class Solution {

    ArrayList<Integer> search(String pat, String txt) {
       ArrayList<Integer> result = new ArrayList<>();
        
        int patLength = pat.length();
        int txtLength = txt.length();
        
       
        for (int i = 0; i <= txtLength - patLength; i++) {
           
            if (txt.substring(i, i + patLength).equals(pat)) {
                result.add(i);
            }
        }
        
        return result; 
    }
}
solution 2
import java.util.ArrayList;

class Solution {
    ArrayList<Integer> search(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = txt.length();
        int m = pat.length();
        
        // Step 1: Build the LPS (Longest Prefix Suffix) array for the pattern
        int[] lps = buildLPS(pat);
        
        int i = 0; // Pointer for txt
        int j = 0; // Pointer for pat
        
        // Step 2: Search for pattern in txt
        while (i < n) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                // Match found; add the starting index to the result
                result.add(i - j);
                j = lps[j - 1]; // Continue searching for further occurrences
            } else if (i < n && txt.charAt(i) != pat.charAt(j)) {
                // Mismatch after j matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return result;
    }
    
    // Helper function to build the LPS array
    private int[] buildLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        
        lps[0] = 0; // LPS of first character is always 0
        
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
}

    
