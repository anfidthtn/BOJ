import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;


/**
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] costTable = new int[N][];
		int[][] minValue = new int[N][1 << (N - 1)];
		for (int i = 0; i < N; i++) {
			costTable[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		System.out.print(getMinValue(costTable, minValue, 0, N - 1, 0));
	}
	
	public static int getMinValue(int[][] costTable, int[][] minValue, int isVisited, int now, int depth) {
		if (minValue[now][isVisited] != 0) return minValue[now][isVisited];
		int N = costTable.length;
		if (depth == N - 1) {
			if (costTable[now][N - 1] == 0) return minValue[now][isVisited] = Integer.MAX_VALUE;
			return minValue[now][isVisited] = costTable[now][N - 1];
		}
		
		minValue[now][isVisited] = Integer.MAX_VALUE;
		
		for (int i = 0; i < N - 1; i++) {
			if ((isVisited & (1 << i)) != 0) continue;
			if (minValue[i][isVisited ^ (1 << i)] == Integer.MAX_VALUE) continue;
			if (costTable[now][i] == 0) continue;
			int subMin = getMinValue(costTable, minValue, isVisited ^ (1 << i), i, depth + 1);
			if (subMin == Integer.MAX_VALUE) continue;
			if (minValue[now][isVisited] > subMin + costTable[now][i]) {
				minValue[now][isVisited] = subMin + costTable[now][i];
			}
		}
		return minValue[now][isVisited];
	}
}