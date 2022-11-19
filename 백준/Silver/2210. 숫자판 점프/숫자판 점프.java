import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] nums;
	static boolean[] check;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nums = new int[5][5];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check = new boolean[1_000_000];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				find(i, j, 1, 0);
			}
		}
		int count = 0;
		for(int i = 0; i < 1_000_000; i++) {
			if (check[i]) {
				count++;
			}
		}
		System.out.println(count);
	}
	public static void find(int row, int col, int digit, int value) {
		if (row < 0 || row >= 5 || col < 0 || col >= 5) {
			return;
		}
		value += nums[row][col];
		if (digit == 6) {
			check[value] = true;
			return;
		}
		for(int d = 0; d < 4; d++) {
			find(row + dr[d], col + dc[d], digit + 1, value * 10);
		}
	}
}