import java.util.*;

public class Solution {
    public int solution(int n) {
        String str = Integer.toBinaryString(n);
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
        	if (str.charAt(i) == '1') {
        		count++;
        	}
        }
        return count;
    }
}