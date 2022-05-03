import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(br.readLine());
		// 벽 정보 등록
		Map<Integer, Set<Integer>> walls = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!walls.containsKey(a)) {
				walls.put(a, new HashSet<>());
			}
			if (!walls.containsKey(b)) {
				walls.put(b, new HashSet<>());
			}
			walls.get(a).add(b);
			walls.get(b).add(a);
		}
		int[][] matchInfo = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if ((i + j) % 2 == 0) {
					boolean[][] visited = new boolean[N][M];
					match(walls, visited, matchInfo, getIdx(i, j));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if ((i + j) % 2 == 1) {
					sb.append(getIdx(i, j)).append(" ").append(matchInfo[i][j]).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
	
	public static boolean match(Map<Integer, Set<Integer>> walls, boolean[][] visited, int[][] matchInfo, int idx) {
		int row = getRow(idx);
		int col = getCol(idx);
		for(int d = 0; d < 4; d++) {
			int nextRow = row + dr[d];
			int nextCol = col + dc[d];
			if (!boundaryCheck(nextRow, nextCol)) {
				continue;
			}
			
			int nextIdx = getIdx(nextRow, nextCol);
			// 벽으로 막혀있어서 못 가면 continue
			if(walls.containsKey(idx)) {
				if (walls.get(idx).contains(nextIdx)) {
					continue;
				}
			}
			if (visited[nextRow][nextCol]) {
				continue;
			}
			visited[nextRow][nextCol] = true;
			// 이미 연결되지 않았거나
			// 연결 된 녀석을 다른 곳에 연결시킬 수 있으면 
			if (matchInfo[nextRow][nextCol] == 0 || match(walls, visited, matchInfo, matchInfo[nextRow][nextCol])) {
				matchInfo[nextRow][nextCol] = idx;
				return true;
			}
		}
		return false;
	}
	public static boolean boundaryCheck(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= M) {
			return false;
		}
		return true;
	}

	public static int getRow(int idx) {
		return (idx - 1) / M;
	}

	public static int getCol(int idx) {
		return (idx - 1) % M;
	}

	public static int getIdx(int row, int col) {
		return row * M + col + 1;
	}
}