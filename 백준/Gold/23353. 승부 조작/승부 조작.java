import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int[] dr = { 0, //
			-1, -1, -1, //
			0,    0, //
			1, 1, 1 //
	};
	static int[] dc = { 0, //
			-1, 0, 1, //
			-1,    1, //
			-1, 0, 1 //
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][][] map = new int[N + 2][N + 2][9];
		boolean[] answer = new boolean[1001];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if (map[i][j][0] == 1) {
					for (int d = 1; d <= 4; d++) {
						map[i][j][d] = map[i + dr[d]][j + dc[d]][d] + 1;
						answer[map[i][j][d]] = true;
					}
				}
			}
		}
		for (int i = N; i >= 1; i--) {
			for (int j = N; j >= 1; j--) {
				if (map[i][j][0] == 1) {
					for (int d = 5; d <= 8; d++) {
						map[i][j][d] = map[i + dr[d]][j + dc[d]][d] + 1;
						answer[map[i][j][d]] = true;
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j][0] == 2) {
					for (int d = 1; d <= 4; d++) {
						answer[map[i + dr[d]][j + dc[d]][d] + map[i + dr[9 - d]][j + dc[9 - d]][9 - d] + 1] = true;
					}
				}
			}
		}
		for (int ans = 1000; ans >= 0; ans--) {
			if(answer[ans]) {
				System.out.println(ans);
				return;
			}
		}
	}
}