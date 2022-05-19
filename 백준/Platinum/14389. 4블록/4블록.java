import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] scores;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		scores = new int[N * M][1 << (N + 1)];
		int score = 0;

		map = new char[M + 2][N];
		for (int j = 0; j < N; j++) {
			String line = br.readLine();
			for (int i = 0; i < M; i++) {
				map[i][j] = line.charAt(i);
				if (map[i][j] == '1') {
					score++;
				}
			}
		}
		System.out.print(score + getScore(0, 0));
	}

	public static int getScore(int linearIdx, int status) {
		if (linearIdx >= N * M) {
			if (linearIdx == N * M && status == 0) {
				return 0;
			}
			return -100;
		}
		if (scores[linearIdx][status] == -1) {
			return 0;
		}
		if (scores[linearIdx][status] > 0) {
			return scores[linearIdx][status];
		}
		int result = 0;
		if (map[linearIdx / N][linearIdx % N] != '.' || (status & 1) != 0) {
			result = Math.max(result, getScore(linearIdx + 1, status >> 1));
		} else {
			result = Math.max(result, getScore(linearIdx + 1, status >> 1) + 1);
			if (linearIdx % N != N - 1) {
				if ((status & 3) == 0 && (status & (3 << N)) == 0) {
					if (map[(linearIdx + 1) / N][(linearIdx + 1) % N] == '.'
							&& map[(linearIdx + N) / N][(linearIdx + N) % N] == '.'
							&& map[(linearIdx + N + 1) / N][(linearIdx + N + 1) % N] == '.') {
						int nextStatus = status;
						nextStatus |= 3 << N;
						nextStatus |= 3;
						result = Math.max(result, getScore(linearIdx + 1, nextStatus >> 1) + 16);
					}
				}
			}
		}
		if (result == 0) {
			scores[linearIdx][status] = -1;
			return 0;
		}
		else {
			return scores[linearIdx][status] = result;
		}
	}
}