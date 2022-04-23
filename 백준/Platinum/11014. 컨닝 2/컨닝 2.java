import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, -1, 0, 1 };
	static int[] dc = { -1, -1, -1, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int count = 0;
			char[][] map = new char[N + 2][M + 2];
			for (int i = 1; i <= N; i++) {
				String line = br.readLine();
				for (int j = 1; j <= M; j++) {
					map[i][j] = line.charAt(j - 1);
					if (map[i][j] == '.')
						count++;
				}
			}
			List<Integer>[][] link = new LinkedList[N + 2][M + 2];
			for (int j = 1; j <= M; j += 2) {
				for (int i = 1; i <= N; i++) {
					if (map[i][j] == '.') {
						link[i][j] = new LinkedList<>();
						for (int d = 0; d < 6; d++) {
							if (map[i + dr[d]][j + dc[d]] == '.') {
								link[i][j].add(getIdx(i + dr[d], j + dc[d]));
							}
						}
					}
				}
			}
			int[][] matched = new int[N + 2][M + 2];
			for (int j = 1; j <= M; j += 2) {
				for (int i = 1; i <= N; i++) {
					if (map[i][j] == '.') {
						boolean[][] visited = new boolean[N + 2][M + 2];
						if (matching(link, i, j, matched, visited)) {
							count--;
						}
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static boolean matching(List<Integer>[][] link, int row, int col, int[][] matched, boolean[][] visited) {
		for (Integer Idx : link[row][col]) {
			int nowRow = getRow(Idx);
			int nowCol = getCol(Idx);
			if (visited[nowRow][nowCol])
				continue;
			visited[nowRow][nowCol] = true;
			if (matched[nowRow][nowCol] == 0 || matching(link, getRow(matched[nowRow][nowCol]),
					getCol(matched[nowRow][nowCol]), matched, visited)) {
				matched[nowRow][nowCol] = getIdx(row, col);
				return true;
			}
		}
		return false;
	}

	public static int getIdx(int row, int col) {
		return (row << 8) + col;
	}

	public static int getRow(int Idx) {
		return Idx >> 8;
	}

	public static int getCol(int Idx) {
		return Idx & ((1 << 8) - 1);
	}
}