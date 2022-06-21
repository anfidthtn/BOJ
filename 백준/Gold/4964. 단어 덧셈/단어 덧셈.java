import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			boolean[] leading = new boolean[26];
			boolean[] used = new boolean[26];
			long[] count = new long[26];
			boolean[] usedNum = new boolean[10];
			for(int i = 0; i < N; i++) {
				String word = br.readLine();
				if (word.length() > 1) {
					leading[word.charAt(0) - 'A'] = true;
				}
				long value = 1;
				if (i == N - 1) {
					value = -1;
				}
				for(int idx = word.length() - 1; idx >= 0; idx--) {
					used[word.charAt(idx) - 'A'] = true;
					count[word.charAt(idx) - 'A'] += value;
					value *= 10;
				}
			}
			sb.append(getWays(leading, used, usedNum, count, 0, 0)).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int getWays(boolean[] leading, boolean[] used, boolean[] usedNum, long[] count, long result, int idx) {
		if (idx == 26) {
			if (result == 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (!used[idx]) {
			return getWays(leading, used, usedNum, count, result, idx + 1);
		}
		int res = 0;
		for(int i = 0; i < 10; i++) {
			if (!usedNum[i]) {
				if (i == 0 && leading[idx]) {
					continue;
				}
				usedNum[i] = true;
				res += getWays(leading, used, usedNum, count, result + count[idx] * i, idx + 1);
				usedNum[i] = false;
			}
		}
		return res;
	}
}