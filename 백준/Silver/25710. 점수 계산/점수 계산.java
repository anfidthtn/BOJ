import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] ansTable;
	static int[] counts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ansTable = new int[1000][1000];
		counts = new int[1000];
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			counts[Integer.parseInt(st.nextToken())]++;
		}
		int ans = 0;
		for (int i = 1; i < 1000; i++) {
			if (counts[i] == 0) {
				continue;
			}
			for (int j = i; j < 1000; j++) {
				if (counts[j] == 0) {
					continue;
				}
				if (i == j && counts[i] < 2) {
					continue;
				}
				ans = Math.max(ans, check(i, j));
			}
		}
		System.out.println(ans);
	}

	public static int check(int x, int y) {
		int mul = x * y;
		int res = 0;
		while (mul > 0) {
			res += mul % 10;
			mul /= 10;
		}
		return res;
	}
}