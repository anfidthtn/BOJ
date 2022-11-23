import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] answers;
//	static boolean[][] visited;
//	static int[] dr = { -1, 0, 1, 0 };
//	static int[] dc = { 0, -1, 0, 1 };
//	static final int N = 1;
//	static long count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answers = new long[] { 0, 1, 2, 5, 12, 30, 73, 183, 456, 1151, 2900, 7361, 18684, 47652, 121584, 311259, 797311,
				2047384, 5260692, 13542718, 34884239, 89991344, 232282110, 600281932, 1552096361, 4017128206l,
				10401997092l, 26957667445l, 69892976538l};
		StringTokenizer st = new StringTokenizer(br.readLine());
//		count = 0;
//		visited = new boolean[30][30];
//		visited[0][0] = true;
//		dfs(0, 0, 0);
//		System.out.println(count / 2);
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long answer = 0;
		for(int i = a; i <= b; i++) {
			answer += answers[i];
		}
		System.out.println(answer);
	}

//	public static void dfs(int row, int col, int depth) {
//		if (depth == N) {
//			count++;
//			return;
//		}
//		for (int d = 0; d < 4; d++) {
//			int nextRow = row + dr[d];
//			int nextCol = col + dc[d];
//			if (nextRow < 0 || nextCol < 0) {
//				continue;
//			}
//			if (visited[nextRow][nextCol]) {
//				continue;
//			}
//			visited[nextRow][nextCol] = true;
//			dfs(nextRow, nextCol, depth + 1);
//			visited[nextRow][nextCol] = false;
//		}
//	}
}