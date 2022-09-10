import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int MAXVALUE = 1 << 25;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] infos = new int[N + 1][];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}
		int[][] costs = new int[N + 1][C + 101];
		for (int i = 0; i <= N; i++) {
			for (int j = 1; j <= C + 100; j++) {
				costs[i][j] = MAXVALUE;
			}
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= C + 100; j++) {
				for(int set = 0;; set++) {
					if (j - set * infos[i][1] < 0) {
						break;
					}
					int cost = set * infos[i][0];
					costs[i][j] = Math.min(costs[i][j], Math.min(costs[i - 1][j], costs[i - 1][j - set * infos[i][1]] + cost));
				}
			}
		}
		int answer = costs[N][C + 100];
		for(int i = 1; i <= 100; i++) {
			answer = Math.min(costs[N][C + 100 - i], answer);
		}
		System.out.println(answer);
	}
}