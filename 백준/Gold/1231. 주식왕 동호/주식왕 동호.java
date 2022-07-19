import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_VALUE = 500_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] stockInfos = new int[C][D];
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < D; j++) {
				stockInfos[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i < D; i++) {
			int[] nextBest = new int[M + 1];
			int nextMoney = M;
			for(int m = 1; m <= M; m++) {
				for(int c = 0; c < C; c++) {
					// 사봐야 이득볼 수 없는 것은 살 필요 없다.
					if (stockInfos[c][i - 1] >= stockInfos[c][i]) {
						continue;
					}
					if (stockInfos[c][i - 1] <= m) {
						int temp = nextBest[m - stockInfos[c][i - 1]] + stockInfos[c][i];
						if (nextBest[m] < temp) {
							nextBest[m] = temp;
						}
						if (nextMoney < nextBest[m] + M - m) {
							nextMoney = nextBest[m] + M - m;
						} 
					}
				}
			}
			M = nextMoney;
		}
		System.out.println(M);
	}
}