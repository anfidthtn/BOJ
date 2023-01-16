import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N + 2][N + 2];
		int[][] counts = new int[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = str.charAt(j - 1);
				if ('1' <= map[i][j] && map[i][j] <= '9') {
					for (int dr = -1; dr <= 1; dr++) {
						for (int dc = -1; dc <= 1; dc++) {
							counts[i + dr][j + dc] += map[i][j] - '0';
						}
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == '.') {
					if (counts[i][j] >= 10) {
						sb.append('M');
					}
					else {
						sb.append(counts[i][j]);
					}
				} else {
					sb.append("*");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}