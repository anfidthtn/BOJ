import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		// 이악물고 main코딩
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] values = { 0, 1, 10, 100, 1000, 10000, 100000 };
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (N == 1_000_000) {
			System.out.println(1000000);
			return;
		}
		Set<Integer>[] nexts = new TreeSet[1_000_000];
		boolean[][] results = new boolean[1_000_000][K + 1];
		results[N][0] = true;
		for (int k = 1; k <= K; k++) {
			for (int num = 999_999; num >= 1; num--) {
				if (results[num][k - 1]) {
					if (nexts[num] == null) {
						nexts[num] = new TreeSet<>();
						String numString = String.valueOf(num);
						for (int a = 0; a < numString.length(); a++) {
							for (int b = a + 1; b < numString.length(); b++) {
								if (a == 0 && numString.charAt(b) == '0') {
									continue;
								}
								int nextNum = num - (numString.charAt(a) - '0') * values[numString.length() - a]
										- (numString.charAt(b) - '0') * values[numString.length() - b]
										+ (numString.charAt(a) - '0') * values[numString.length() - b]
										+ (numString.charAt(b) - '0') * values[numString.length() - a];
								nexts[num].add(nextNum);
							}
						}
					}
					for (int next : nexts[num]) {
						results[next][k] = true;
					}
				}
			}
		}
		for(int i = 999999; i >= 1; i--) {
			if (results[i][K]) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
}