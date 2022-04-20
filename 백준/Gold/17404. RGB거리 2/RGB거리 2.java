import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int maxValue = 10000000;
		int N = Integer.parseInt(br.readLine());
		/**
		 * 0 : Red
		 * 1 : Greed
		 * 2 : Blue
		 */
		int[][] costTable = new int[N + 1][3];
		for(int i = 1; i <= N; i++) {
			costTable[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		/**
		 * 마지막 집이 Red로 칠해진 경우의 최소비용 테이블
		 * 마지막 집이 Green로 칠해진 경우의 최소비용 테이블
		 * 마지막 집이 Blue로 칠해진 경우의 최소비용 테이블
		 */
		int[][] lastRedTable = new int[N + 1][3];
		int[][] lastGreenTable = new int[N + 1][3];
		int[][] lastBlueTable = new int[N + 1][3];
		for(int[][] table : new int[][][]{lastRedTable, lastGreenTable, lastBlueTable}) {
			for(int i = 0; i < 3; i++) {
				table[1][i] = costTable[1][i];
			}
		}
		lastRedTable[1][0] = maxValue;
		lastGreenTable[1][1] = maxValue;
		lastBlueTable[1][2] = maxValue;
		int minValue = maxValue;
		int colorIdx = 0;
		for(int[][] table : new int[][][]{lastRedTable, lastGreenTable, lastBlueTable}) {
			for(int i = 2; i <= N; i++) {
				table[i][0] = Math.min(table[i - 1][1], table[i - 1][2]) + costTable[i][0];
				table[i][1] = Math.min(table[i - 1][2], table[i - 1][0]) + costTable[i][1];
				table[i][2] = Math.min(table[i - 1][0], table[i - 1][1]) + costTable[i][2];
			}
			minValue = Math.min(minValue, table[N][colorIdx++]);
		}
		System.out.println(minValue);
	}
}