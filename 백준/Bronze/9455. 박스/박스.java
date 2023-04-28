import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[][] boxes = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					boxes[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;
			for (int j = 0; j < C; j++) {
				for (int d = R - 1; d > 0; d--) {
					for (int s = d - 1; s >= 0; s--) {
						if (boxes[d][j] == 0 && boxes[s][j] == 1) {
							boxes[d][j] = 1;
							boxes[s][j] = 0;
							ans += d - s;
						}
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}