import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] apCounts;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		apCounts = new int[26];
		int maxLen = 0;
		String str = br.readLine();
		
		int left = 0;
		int right = -1;
		while(right < str.length()) {
			if (left > right) {
				if (++right < str.length()) {
					plus(str.charAt(right));
				}
			}
			else if (count <= N) {
				if (maxLen < right - left + 1) {
					maxLen = right - left + 1;
				}
				if (++right < str.length()) {
					plus(str.charAt(right));
				}
				
			}
			else {
				minus(str.charAt(left++));
			}
		}
		System.out.println(maxLen);
	}
	public static void plus(char c) {
		if (apCounts[c - 'a']++ == 0) {
			count++;
		}
	}
	public static void minus(char c) {
		if (--apCounts[c - 'a'] == 0) {
			count--;
		}
	}
}