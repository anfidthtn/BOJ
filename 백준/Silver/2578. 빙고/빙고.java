import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// <해당 숫자가, 어디에 있는지> 를 저장하는 것
		Map<Integer, Integer[]> points = new HashMap<>();
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				// 받은 숫자의 위치 저장
				points.put(Integer.parseInt(st.nextToken()), new Integer[] {i, j});
			}
		}
		int[] rowCount = new int[5];
		int[] colCount = new int[5];
		// 우하향 대각선
		int dDia = 0;
		// 우상향 대각선
		int uDia = 0;
		int bingoCount = 0;
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				// 부른 숫자의 위치 불러오기
				Integer[] point = points.get(Integer.parseInt(st.nextToken()));
				if (++rowCount[point[0]] == 5) {
					// 가로 5개 연속 나오면 빙고
					bingoCount++;
				}
				if (++colCount[point[1]] == 5) {
					// 세로 5개 연속 나오면 끝
					bingoCount++;
				}
				if (point[0] + point[1] == 4) {
					// 우상향대각
					if (++uDia == 5) {
						bingoCount++;
					}
				}
				if (point[0] == point[1]) {
					// 우하향대각
					if (++dDia == 5) {
						bingoCount++;
					}
				}
				if (bingoCount >= 3) {
					System.out.print(i * 5 + j + 1);
					return;
				}
			}
		}
	}
}