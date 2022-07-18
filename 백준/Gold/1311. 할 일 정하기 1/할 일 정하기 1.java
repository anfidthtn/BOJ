import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] costs;
	static int[][] mincosts;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costs = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		mincosts = new int[N][1 << N];
		System.out.println(getMin(0, 0));
	}
	public static int getMin(int idx, int visited) {
		if (idx == N) {
			return 0;
		}
		if (mincosts[idx][visited] > 0) {
			return mincosts[idx][visited];
		}
		int result = 100_000_000;
		for(int i = 0; i < N; i++) {
			if ((visited & (1 << i)) != 0) {
				continue;
			}
			result = Math.min(result, costs[idx][i] + getMin(idx + 1, visited ^ (1 << i)));
		}
		return mincosts[idx][visited] = result;
	}
}