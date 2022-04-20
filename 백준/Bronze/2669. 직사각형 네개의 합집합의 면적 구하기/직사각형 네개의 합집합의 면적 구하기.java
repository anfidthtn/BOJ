import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 직사각형 겹치는 거 제한 큰 문제 높은 난이도 문제에서 있긴 한데 이 문제는 제한이 낮으니 그냥 생각하기 편한대로 구현
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 색칠 되었는지 여부를 저장할 것을 만들고, (초기값 false다.)
		boolean[][] isColored = new boolean[100 + 1][100 + 1];
		
		// 카운팅할 변수 만들고
		int countColor = 0;
		// 직사각형 개수만큼 돌린다.
		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 입력에서 시작지점을 받는다.
			int stRow = Integer.parseInt(st.nextToken());
			int stCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			// 시작지점부터 끝점까지 칠한다.
			for(int i = stRow; i < endRow; i++) {
				for(int j = stCol; j < endCol; j++) {
					// 칠해지지 않은 곳만 칠한다.
					if (isColored[i][j] == false) {
						// 칠하면서
						isColored[i][j] = true;
						// 칠한 횟수를 올린다.
						countColor++;
					}
				}
			}
		}
		System.out.print(countColor);
	}

}