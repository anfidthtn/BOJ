import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int maxnum;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 테두리에 패딩하고, 입력을 저장
		char[][] map = new char[R + 2][C + 2];
		for (int i = 1; i <= R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j + 1] = s.charAt(j);
			}
		}
		
		// 지나간 알파벳 저장
		Map<Character, Boolean> isVisited = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			isVisited.put((char) (i + 'A'), false);
		}
		
		// 처음에 하나 밟고 시작하니 1넣고 시작
		maxnum = 1;
		isVisited.put(map[1][1], true);
		// 1, 1부터 탐색
		getMaxDepth(map, isVisited, 1, 1, 1);
		System.out.print(maxnum);
	}
	
	public static void getMaxDepth(char[][] map, Map<Character, Boolean> isVisited, int row, int col, int usedCount) {
		if (usedCount == 26) return;
		for (int d = 0; d < 4; d++) {
			char next = map[row + dr[d]][col + dc[d]]; 
			if (next == 0) continue;
			if (isVisited.get(next)) continue;
			isVisited.put(next, true);
			if (maxnum < usedCount + 1) maxnum = usedCount + 1;
			getMaxDepth(map, isVisited, row + dr[d], col + dc[d], usedCount + 1);
			if (maxnum == 26) return;
			isVisited.put(next, false);
		}
	}
}