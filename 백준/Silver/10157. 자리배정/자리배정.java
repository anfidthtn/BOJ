import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		if (K > C * R) {
			System.out.print(0);
			return;
		}
		// (0, 1)의 0부터 시작
		int row = 0;
		int col = 1;
		int depth = 1;
		int moveDistance = 0;
		while (K > 0) {
			if (col == depth) {
				// 위로 이동할 수 있는 경우
				moveDistance = Math.min(K, R - (depth * 2 - 2));
				row += moveDistance;
				K -= moveDistance;
			}
			if (row == R + 1 - depth) {
				// 오른쪽으로 이동할 수 있는 경우
				moveDistance = Math.min(K, C - (depth * 2 - 1));
				col += moveDistance;
				K -= moveDistance;
			}
			if (col == C + 1 - depth) {
				// 아래로 이동할 수 있는 경우
				moveDistance = Math.min(K, R - (depth * 2 - 1));
				row -= moveDistance;
				K -= moveDistance;
			}
			if (row == depth) {
				// 왼쪽으로 이동할 수 있는 경우
				moveDistance = Math.min(K, C - (depth * 2));
				col -= moveDistance;
				K -= moveDistance;
			}
			depth++;
		}
		System.out.print(col + " " + row);
	}
}