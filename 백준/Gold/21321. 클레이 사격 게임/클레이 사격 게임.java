import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] a;
	static int[] b;
	static int[][] maxScores;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new int[N];
		b = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		maxScores = new int[N][1 << N];
		System.out.println(getMax(0, 0));
	}

	public static int getMax(int round, int visited) {
		if (round == N) {
			return 0;
		}
		if (maxScores[round][visited] != 0) {
			if (maxScores[round][visited] == -1) {
				return 0;
			} else {
				return maxScores[round][visited];
			}
		}
		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) != 0) {
				continue;
			}
			boolean able = true;
			for (int j = 0; j < i; j++) {
				if ((visited & (1 << j)) != 0) {
					continue;
				}
				if (a[i] % a[j] == 0) {
					able = false;
					break;
				}
			}
			if (!able) {
				continue;
			}
			maxScores[round][visited] = Math.max(maxScores[round][visited],
					(round + 1) * b[i] + getMax(round + 1, visited ^ (1 << i)));
		}
		// maxScores[round][visited] = Math.max(maxScores[round][visited], getMax(round + 1, visited));
		if (maxScores[round][visited] == 0) {
			maxScores[round][visited] = -1;
			return 0;
		} else {
			return maxScores[round][visited];
		}
	}
}