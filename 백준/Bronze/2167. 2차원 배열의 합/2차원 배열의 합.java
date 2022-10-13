import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R + 1][C + 1];
		for(int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			for(int i = a; i <= c; i++) {
				for(int j = b; j <= d; j++) {
					sum += map[i][j];
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb.toString());
	}
}