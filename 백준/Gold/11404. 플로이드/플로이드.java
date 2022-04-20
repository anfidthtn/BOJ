import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] cost = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				cost[i][j] = 1_000_000_000;
				if (i == j)
					cost[i][j] = 0;
			}
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (cost[s][e] > v) {
				cost[s][e] = v;
			}
		}
		
		for (int k = 1; k <= N; k++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {
					if (cost[s][k] + cost[k][e] < cost[s][e])
						cost[s][e] = cost[s][k] + cost[k][e];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cost[i][j] == 1_000_000_000) {
					sb.append("0 ");
				}
				else {
					sb.append(cost[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}