import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] bests;
	static int[][] infos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		infos = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}
		bests = new long[N + 1];
		Arrays.fill(bests, -1);
		bests[N] = 0;
		for(int i = N - 1; i >= 0; i--) {
			getBest(i);
		}
		System.out.print(getBest(0));
	}

	public static long getBest(int idx) {
		if (idx >= N + 1) {
			return -(1l << 50);
		}
		if (bests[idx] != -1) {
			return bests[idx];
		}
		return bests[idx] = Math.max(getBest(idx + 1), getBest(idx + infos[idx][0]) + infos[idx][1]);
	}
}