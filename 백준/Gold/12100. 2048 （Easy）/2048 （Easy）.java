import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(map[i][j], max);
			}
		}
		rotate(map, 0);
		System.out.println(max);
	}
	public static void rotate(int[][] map, int count) {
		if (count == 5) {
			return;
		}
		int[][] newMap = new int[N + 2][N + 2];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		move(newMap);
		rotate(newMap, count + 1);
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				newMap[j][N - i + 1] = map[i][j];
			}
		}
		move(newMap);
		rotate(newMap, count + 1);
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				newMap[N - i + 1][N - j + 1] = map[i][j];
			}
		}
		move(newMap);
		rotate(newMap, count + 1);
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				newMap[N - j + 1][i] = map[i][j];
			}
		}
		move(newMap);
		rotate(newMap, count + 1);
		
	}
	public static void move(int[][] map) {
		for(int row = 1; row <= N; row++) {
			int left = 1;
			int right = 2;
			while(left <= N && right <= N) { 
				if (right <= left) {
					right++;
				}
				else if (map[row][right] == 0) {
					right++;
				}
				else if (map[row][left] == 0) {
					left++;
				}
				else if (map[row][left] != map[row][right]) {
					left = right;
				}
				else {
					map[row][left] *= 2;
					map[row][right] = 0;
					max = Math.max(map[row][left], max);
					left = right + 1;
				}
			}
			left = 1;
			right = 2;
			while(left <= N && right <= N) {
				if (right <= left) {
					right++;
				}
				else if (map[row][right] == 0) {
					right++;
				}
				else if (map[row][left] == 0) {
					map[row][left] = map[row][right];
					map[row][right] = 0;
					left++;
					right++;
				}
				else {
					left++;
				}
			}
		}
	}
}