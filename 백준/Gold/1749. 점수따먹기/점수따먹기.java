import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] num = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				num[i][j] += num[i - 1][j] + num[i][j - 1] - num[i - 1][j - 1];
			}
		}
		int max = -2_000_000_000;
		for(int a = 0; a < N; a++) {
			for(int b = 0; b < M; b++) {
				for(int c = a + 1; c <= N; c++) {
					for(int d = b + 1; d <= M; d++) {
						int area = num[c][d] - num[a][d] - num[c][b] + num[a][b];
						if (max < area) {
							max = area;
						}
					}
				}
			}
		}
		System.out.println(max);
	}
}