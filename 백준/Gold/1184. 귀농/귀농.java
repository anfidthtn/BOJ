import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] earn = new int[N + 1][N + 1];
		int[][] earnSum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				earn[i][j] = Integer.parseInt(st.nextToken());
				earnSum[i][j] = earnSum[i - 1][j] + earnSum[i][j - 1] - earnSum[i - 1][j - 1] + earn[i][j];
			}
		}

		int count = 0;
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				count += getCount(earnSum, row, col, N);
			}
		}
		System.out.println(count);
	}
	public static int getCount(int[][] earnSum, int row, int col, int N) {
		int count = 0;
		
		Map<Integer, Integer> left = new TreeMap<>();
		Map<Integer, Integer> right = new TreeMap<>();
		
		// 좌상단 사각형 합별로 개수 세기
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col; j++) {
				int areaSum = earnSum[row][col] - earnSum[i - 1][col] - earnSum[row][j - 1] + earnSum[i - 1][j - 1];
				if (!left.containsKey(areaSum)) {
					left.put(areaSum, 0);
				}
				left.put(areaSum, left.get(areaSum) + 1);
			}
		}
		// 우하단 사각형 합별로 개수 세기
		for(int i = row + 1; i <= N; i++) {
			for(int j = col + 1; j <= N; j++) {
				int areaSum = earnSum[i][j] - earnSum[i][col] - earnSum[row][j] + earnSum[row][col];
				if (!right.containsKey(areaSum)) {
					right.put(areaSum, 0);
				}
				right.put(areaSum, right.get(areaSum) + 1);
			}
		}
		
		for(int areaSum : right.keySet()) {
			if (left.containsKey(areaSum)) {
				count += left.get(areaSum) * right.get(areaSum);				
			}
		}
		
		left = new TreeMap<>();
		right = new TreeMap<>();
		
		// 좌하단 사각형 합별로 개수 세기
		for(int i = row; i <= N; i++) {
			for(int j = 1; j <= col; j++) {
				int areaSum = earnSum[i][col] - earnSum[row - 1][col] - earnSum[i][j - 1] + earnSum[row - 1][j - 1];
				if (!left.containsKey(areaSum)) {
					left.put(areaSum, 0);
				}
				left.put(areaSum, left.get(areaSum) + 1);
			}
		}
		
		// 우상단 사각형 합별로 개수 세기
		for(int i = 1; i <= row - 1; i++) {
			for(int j = col + 1; j <= N; j++) {
				int areaSum = earnSum[row - 1][j] - earnSum[i - 1][j] - earnSum[row - 1][col] + earnSum[i - 1][col];
				if (!right.containsKey(areaSum)) {
					right.put(areaSum, 0);
				}
				right.put(areaSum, right.get(areaSum) + 1);
			}
		}
		
		for(int areaSum : right.keySet()) {
			if (left.containsKey(areaSum)) {
				count += left.get(areaSum) * right.get(areaSum);				
			}
		}
		
		return count;
	}
}