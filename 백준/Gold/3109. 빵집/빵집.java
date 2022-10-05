import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 위아래 패딩용으로 +2를 해준다.
		char[][] map = new char[R + 2][];
		// 위아래 패딩을 해준다.
		map[0] = new char[C];
		map[R + 1] = new char[C];
		// 각 행 입력을 받는다.
		for(int i = 1; i <= R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int count = 0;
		for(int i = 1; i <= R; i++) {
			//시작점으로 한다.
			if (finder(map, i, 0)) count++;
		}
		System.out.print(count);
	}
	public static boolean finder(char[][] map, int row, int col) {
		// 끝까지 도달하면 true반환
		if (col == C - 1) return true;
		boolean isFound = false;
		if (map[row - 1][col + 1] == '.') {
			// 다음 열의 한 칸 위를 보는데, 빈칸이면 시도해볼 수 있다.
			// 방문한 곳으로 만들고 탐색한다.
			map[row - 1][col + 1] = 'x';
			isFound = finder(map, row - 1, col + 1);
			if (isFound) return true;
		}
		if (map[row][col + 1] == '.') {
			// 다음 열의 같은 칸을 본다.
			map[row][col + 1] = 'x';
			isFound = finder(map, row, col + 1);
			if (isFound) return true;
		}
		if (map[row + 1][col + 1] == '.') {
			// 다음 열의 아래 칸을 본다.
			map[row + 1][col + 1] = 'x';
			isFound = finder(map, row + 1, col + 1);
			if (isFound) return true;
		}
		return false;
	}
}