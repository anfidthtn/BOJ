import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	// 상 좌 하 우
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int N;
	static int M;
	static int[][] map;
	static int[] groupMin;
	static int[] groupHeight;
	static int[] groupCount;
	static int[][] groupNum;
	static boolean[] checked;
	static List<Set<Integer>> graph;

	static int gNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
			}
		}

		int count = 0;
		for (int i = 1; i <= 9; i++) {
			makeGroup();
			checked = new boolean[gNum + 1];
			checkMin();
			for (int g = 1; g <= gNum; g++) {
				if (checked[g] && groupHeight[g] < groupMin[g]) {
					count += (groupMin[g] - groupHeight[g]) * groupCount[g];
					groupHeight[g] = groupMin[g];
				}
			}
			reload();
		}
		System.out.println(count);
	}

	public static void reload() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = groupHeight[groupNum[i][j]];
			}
		}
	}

	public static void checkMin() {
		for (int i = 1; i <= gNum; i++) {
			groupMin[i] = 9;
			checked[i] = false;
			for (int next : graph.get(i)) {
				if (groupHeight[next] == groupHeight[i]) {
					continue;
				}
				checked[i] = true;
				if (groupHeight[next] < groupMin[i]) {
					groupMin[i] = groupHeight[next];
				}
			}
		}
	}

	public static void makeGroup() {
		gNum = 0;
		groupNum = new int[N + 2][M + 2];
		groupMin = new int[N * M + 1];
		groupHeight = new int[N * M + 1];
		groupCount = new int[N * M + 1];
		graph = new ArrayList<>(N * M + 1);
		for (int i = 0; i < N * M; i++) {
			graph.add(new TreeSet<>());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (groupNum[i][j] == 0) {
					gNum++;
					groupHeight[gNum] = map[i][j];
					makeGroupDfs(i, j);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				for(int d = 0; d < 4; d++) {
					int nextI = i + dr[d];
					int nextJ = j + dc[d];
					if (groupNum[i][j] == groupNum[nextI][nextJ]) {
						continue;
					}
					graph.get(groupNum[i][j]).add(groupNum[nextI][nextJ]);
					graph.get(groupNum[nextI][nextJ]).add(groupNum[i][j]);
				}
			}
		}
		for (int j = 1; j <= M; j++) {
			graph.get(groupNum[N][j]).add(groupNum[N + 1][j]);
			graph.get(groupNum[N + 1][j]).add(groupNum[N][j]);
		}
	}

	public static void makeGroupDfs(int row, int col) {
		groupNum[row][col] = gNum;
		groupCount[gNum]++;
		for (int d = 0; d < 4; d++) {
			int nextRow = row + dr[d];
			int nextCol = col + dc[d];
			if (map[nextRow][nextCol] != map[row][col]) {
				continue;
			}
			if (groupNum[nextRow][nextCol] > 0) {
				continue;
			}
			makeGroupDfs(nextRow, nextCol);
		}
	}
}