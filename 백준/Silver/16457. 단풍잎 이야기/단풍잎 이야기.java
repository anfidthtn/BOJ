import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] quests;
	static boolean[] selected;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		quests = new int[M][K];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				quests[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new boolean[2 * N + 1];
		answer = 0;
		check(0, 0);
		System.out.println(answer);
	}

	public static void check(int idx, int count) {
		if (count > N) {
			return;
		}
		if (idx - N > count) {
			return;
		}
		if (idx == 2 * N) {
			if (count == N) {
				int temp = 0;
				quest: for (int i = 0; i < M; i++) {
					for (int k = 0; k < K; k++) {
						if (!selected[quests[i][k]]) {
							continue quest;
						}
					}
					temp++;
				}
				answer = Math.max(answer, temp);
			}
			return;
		}
		selected[idx + 1] = true;
		check(idx + 1, count + 1);
		selected[idx + 1] = false;
		check(idx + 1, count);
	}
}