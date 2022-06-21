import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int N = 3;
		long[] count = new long[26];
		boolean[] usedNum = new boolean[10];
		for(int i = 0; i < N; i++) {
			String word = strs[i];
			long value = 1;
			if (i == N - 1) {
				value = -1;
			}
			for(int idx = word.length() - 1; idx >= 0; idx--) {
				count[word.charAt(idx) - 'A'] += value;
				value *= 10;
			}
		}
		System.out.println(getWays(usedNum, count, 0, 0) ? "YES" : "NO");
	}
	public static boolean getWays(boolean[] usedNum, long[] count, long result, int idx) {
		if (idx == 26) {
			if (result == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		if (count[idx] == 0) {
			return getWays(usedNum, count, result, idx + 1);
		}
		for(int i = 0; i < 10; i++) {
			if (!usedNum[i]) {
				usedNum[i] = true;
				if(getWays(usedNum, count, result + count[idx] * i, idx + 1)) {
					return true;
				}
				usedNum[i] = false;
			}
		}
		return false;
	}
}