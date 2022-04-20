import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tNum = 1; tNum <= t; tNum++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			char[][] map = new char[N][M];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			int[][] saved = new int[N][1 << M];
			sb.append(getMax(map, saved, 0, 0)).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int getMax(char[][] map, int[][] saved, int beforeCase, int nowIdx) {
		int N = map.length;
		if (nowIdx == N) {
			return 0;
		}

		// 이미 구해진 최대값이 있다면 구한 것으로 리턴
		if (saved[nowIdx][beforeCase] > 0)
			return saved[nowIdx][beforeCase];
		// int는 0으로 자동초기화가 되어 0개의 경우와 구분하기 위해서 구했는데 0인 곳은 -1로 마킹
		// 따로 -1같은걸로 초기화를 하기에는 오버헤드가 더 날 수 있음
		if (saved[nowIdx][beforeCase] == -1) {
			return 0;
		}
		// 구하지 않은 경우는 모두 탐색
		int M = map[0].length;
		for (int nowCase = 0; nowCase < 1 << M; nowCase++) {
			// 이 경우 이번 라인에 몇 명 앉는지.
			int bitCount = 0;
			// 해당 자리에 올 수 있는지 비트별 검사
			boolean able = true;
			for (int bit = 0; bit < M; bit++) {
				if ((nowCase & (1 << bit)) != 0) {
					if (map[nowIdx][bit] == 'x') {
						able = false;
						break;
					}
					if (bit > 0) {
						// 가장 오른쪽 비트가 아니면 오른쪽 위, 오른쪽에 비트가 있었는지 확인
						if ((beforeCase & (1 << (bit - 1))) != 0) {
							able = false;
							break;
						}
						if ((nowCase & (1 << (bit - 1))) != 0) {
							able = false;
							break;
						}
					}
					if (bit < M - 1) {
						// 가장 왼쪽 비트가 아니면 왼쪽 위, 왼쪽에 비트가 있었는지 확인
						if ((beforeCase & (1 << (bit + 1))) != 0) {
							able = false;
							break;
						}
						if ((nowCase & (1 << (bit + 1))) != 0) {
							able = false;
							break;
						}
					}
					bitCount++;
				}
			}
			if (!able)
				continue;
			// 가능한 경우이면 최대치 갱신
			saved[nowIdx][beforeCase] = Math.max(saved[nowIdx][beforeCase],
					bitCount + getMax(map, saved, nowCase, nowIdx + 1));
		}
		if (saved[nowIdx][beforeCase] == 0) {
			saved[nowIdx][beforeCase] = -1;
			return 0;
		} else {
			return saved[nowIdx][beforeCase];
		}
	}
}