import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		/**
		 * 0 : 가로
		 * 1 : 세로
		 * 2 : 대각선
		 */
		long[][][] cases = new long[N + 1][N + 1][3];
		boolean[][] map = new boolean[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j  = 1; j <= N; j++) {
				if(st.nextToken().charAt(0) == '1') {
					map[i][j] = true;
				}
			}
		}
		cases[1][2][0] = 1;
		
		for(int row = 1; row <= N; row++) {
			for(int col = 1; col <= N; col++) {
				if (map[row][col]) continue;
				if (!map[row - 1][col]) {
					cases[row][col][1] += cases[row - 1][col][1] + cases[row - 1][col][2];
				}
				if (!map[row][col - 1]) {
					cases[row][col][0] += cases[row][col - 1][0] + cases[row][col - 1][2];
				}
				if (!map[row - 1][col] && !map[row][col - 1]) {
					cases[row][col][2] += cases[row - 1][col - 1][0] + cases[row - 1][col - 1][1] + cases[row - 1][col - 1][2];
				}
			}
		}
		
		System.out.println(cases[N][N][0] + cases[N][N][1] + cases[N][N][2]);
		
	}
}