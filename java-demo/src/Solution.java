import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * <p>
 * ListNode(int x) { val = x; }
 * <p>
 * }
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().lengthOfLongestSubstring("nfpdmpi");
    }
    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }

        // Map<Byte,Integer> windows = new HashMap<>(bytes.length / 2);
        Map<Character, Integer> windows = new HashMap<>();
        int start = 0, end, tempL = 0;
        for (int i = 0; i < s.length(); i++) {
            end = i;
            if (windows.containsKey(s.charAt(i))) {
                // 先get再put
                start = Math.max(start, windows.get(s.charAt(i)) + 1);
                windows.put(s.charAt(i), i);
                // tempL = Math.max(tempL, end - start + 1);
            } else {
                windows.put(s.charAt(i), i);
                // tempL = Math.max(tempL, end - start + 1);
            }
            tempL = Math.max(tempL, end - start + 1);
        }
        return tempL;
    }

}
