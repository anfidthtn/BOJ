import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 4방탐색용 벡터
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= 1; tNum++) {
			// sb.append("#").append(tNum).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 세로
			int R = Integer.parseInt(st.nextToken());
			// 가로
			int C = Integer.parseInt(st.nextToken());
			// 맵 (패딩을 함)
			int[][] map = new int[R + 2][C + 2];
			// 맵 입력 받기
			for(int i = 1; i <= R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 카운팅용 dp 배열
			long[][] caseMap = new long[R + 2][C + 2];
			// 시작점은 1개의 경우의 수이다.
			caseMap[1][1] = 1;
			
			// 도착점에서 4방향으로 요청을 해서 dfs형태로 결과값을 받아온다.
			sb.append(getCase(map, caseMap, R, C)).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static long getCase(int[][] map, long[][] caseMap, int row, int col) {
		// 이미 구해진 결과값이 있으면
		if (caseMap[row][col] > 0) {
			// 구해진 결과값을 반환한다.
			return caseMap[row][col];
		}
		// 이미 구했으나 0이었던 경우가 있었으면 -1로 저장을 해 뒀다. (이 메소드 아래쪽)
		if (caseMap[row][col] == -1) {
			// 이 경우 0을 반환한다.
			return 0;
		}
		// 이 메소드 들어오는 조건에 의해 0인 경우에는 무조건 경우의 수를 구해야하는 셀이 된다.
		// * 들어오는 조건은 현재 지점보다 더 높은 지점에서 return을 요청한 경우이다.
		
		// 4방향에 대해서 경우의 수를 구해 합해준다.
		for(int d = 0; d < 4; d++) {
			// 다음 탐색 지점이 오르막길 일 경우 탐색한다.
			// 문제 조건과 반대로 탐색하는 이유는 도착점에서 시작해서 역으로 거슬러 올라가며 탐색하기 때문이다.
			if (map[row][col] < map[row + dr[d]][col + dc[d]]) {
				// 탐색한 결과를 저장한다.
				caseMap[row][col] += getCase(map, caseMap, row + dr[d], col + dc[d]);
			}
		}
		// 만약 경우의 수가 0개이면
		if (caseMap[row][col] == 0) {
			// 구하지 않은 것과 구분하기 위해 -1을 저장하고 0을 리턴한다.
			caseMap[row][col] = -1;
			return 0;
		}
		// 경우의 수가 있으면 구한 값을 리턴한다.
		return caseMap[row][col];
	}
}