import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int K;
	static int[] cans;
	static int[][][] RMs;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cans = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		RMs = new int[2][K][];
		for(int i = 0; i < K; i++) {
			RMs[0][i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for(int i = 0; i < K; i++) {
			RMs[1][i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		answer = 0;
		check(0, 0);
		System.out.println(answer);
	}
	public static void check(int idx, int sum) {
		if (idx == 2 * K) {
			answer = Math.max(answer, sum);
			return;
		}
		for(int i = 0; i < N; i++) {
			if (cans[i] == 0) {
				continue;
			}
			cans[i]--;
			check(idx + 1, sum + RMs[idx / K][idx % K][i]);
			cans[i]++;
		}
	}
}