import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] ways;
	static final int MODNUM = 9901;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ways = new int[N * M][1 << M];
		System.out.println(getWay(0, 0));
	}

	public static int getWay(int linearIdx, int status) {
		if (linearIdx >= N * M) {
			// 끝까지 왔다면 된 것
			if (linearIdx == N * M && status == 0) {
				return 1;
			}
			return 0;
		}
		if (ways[linearIdx][status] == -1) {
			// 경우의 수가 없었을 때 -1 저장하기로 약속했으므로 0 리턴
			return 0;
		}
		if (ways[linearIdx][status] > 0) {
			return ways[linearIdx][status];
		}
		int result = 0;
		if ((status & 1) == 0) {
			if (linearIdx % M != M - 1) {
				// 해당 칸이 가장 끝 칸이 아닌 경우 가로 막대를 놔볼 수 있는지 본다.
				if ((status & 3) == 0) {
					// 연속 2칸이 비었을 경우 가로 막대를 놔볼 수 있다.
					result += getWay(linearIdx + 2, status >> 2);
				}
			}
			if (linearIdx / M != N - 1) {
				// 해당 칸이 가장 끝 줄이 아닌 경우 세로 막대를 놔본다.
				result += getWay(linearIdx + 1, (status + (1 << M)) >> 1);
			}
		} else {
			// 해당 칸이 차 있었다면 그대로 진행
			result += getWay(linearIdx + 1, status >> 1);
		}

		if (result == 0) {
			// 경우의 수가 없었을 경우 -1을 저장하고 0을 리턴
			ways[linearIdx][status] = -1;
			return 0;
		}
		return ways[linearIdx][status] = result % MODNUM;
	}
}