import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *P16926_silver2_배열_돌리기_1
 *구현하기 귀찮아서 계속 nextPoint로 좌표를 구했는데,
 *공간을 더 쓰고 시간을 덜 쓰려면
 *인풋을 전부 저장하고, (1,1), (2,2)같은 각 사이클 시작점의 이동위치를 구한 다음
 *그 이동위치에서 상대적으로 1칸씩 움직이며 새로운 배열에 넣어서 추후 출력하는 방법도 있긴 할 것 같은데
 *구현 너무 귀찮을 것 같아서 포기
 */
public class Main {
	public static int getMin(int[] arr) {
		int minValue = Integer.MAX_VALUE;
		for(int num : arr) {
			minValue = Math.min(minValue, num);
		}
		return minValue;
	}
	public static int[] getNextPoint(int N, int M, int row, int col, int R) {
		// 회전 사이클의 깊이는 배열 경계 밖과 가장 가까운 거리이기도 하다.
		int cycleDepth = getMin(new int[] {N + 1 - row, row, M + 1 - col, col});
		/**
		 * 가로 2개, 세로 2개의 평행한 막대로 사이클을 덮었다고 생각하면
		 * 깊이 1에서 다음과 같다.
		 * ㅅㅇㅇㅇㅇㅅ
		 * ㅇㅁㅁㅁㅁㅇ
		 * ㅇㅁㅁㅁㅁㅇ
		 * ㅅㅇㅇㅇㅇㅅ
		 * ㅁ : 사이클에 포함되지 않은 자리
		 * ㅇ : 사이클에 포함된 자리 중 안 겹친 자리
		 * ㅅ : 사이클에 포함된 자리 중 겹친 자리
		 * 이 그림에서 가로 2회, 세로 2회를 더하면 ㅅ의 4꼭지점이 겹친다.
		 * 
		 * 깊이 2에서 다음과 같다.
		 * ㅁㄹㅁㅁㄹㅁ
		 * ㄹㅅㅇㅇㅅㄹ
		 * ㄹㅅㅇㅇㅅㄹ
		 * ㅁㄹㅁㅁㄹㅁ
		 * ㄹ : 사이클에 포함되지 않은 자리
		 * 이 그림에서 가로 2회, 세로 2회를 더하면 겹치는 지점 ㅅ 4곳, 바깥지점 ㄹ 8곳이 생긴다.
		 * 이런 규칙으로 사이클 원소의 개수를
		 * 2 * (가로 + 세로) - 4 * (2 * depth - 1)
		 * = 2 * (가로 + 세로 - 4depth + 2) 이라고 할 수 있다.
		 */
		R %= 2 * (N + M - 4 * cycleDepth + 2);
		while (R > 0) {
			if (col == cycleDepth) {
				// 아래로 이동가능한 경우
				int distance = Math.min(R, N + 1 - cycleDepth - row); 
				row += distance;
				R -= distance;
			}
			if (row == N + 1 - cycleDepth) {
				// 오른쪽으로 이동가능한 경우
				int distance = Math.min(R, M + 1 - cycleDepth - col); 
				col += distance;
				R -= distance;
			}
			if (col == M + 1 - cycleDepth) {
				// 위쪽으로 이동가능한 경우
				int distance = Math.min(R, row - cycleDepth);
				row -= distance;
				R -= distance;
			}
			if (row == cycleDepth) {
				// 왼쪽으로 이동가능한 경우
				int distance = Math.min(R, col - cycleDepth); 
				col -= distance;
				R -= distance;
			}
		}
		return new int[] {row, col};
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		int M = Integer.parseInt(info[1]);
		int R = Integer.parseInt(info[2]);
		
		int[][] result = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int[] point = getNextPoint(N, M, i, j, R);
				result[point[0]][point[1]] = Integer.parseInt(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}