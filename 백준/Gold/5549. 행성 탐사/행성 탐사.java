import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static int[][][] counts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		counts = new int[R + 1][C + 1][3];
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				int num = 0;
				switch (str.charAt(j - 1)) {
				case 'O':
					num = 1;
					break;
				case 'I':
					num = 2;
					break;
				}
				counts[i][j][num]++;
				for (int k = 0; k < 3; k++) {
					counts[i][j][k] += counts[i][j - 1][k] + counts[i - 1][j][k] - counts[i - 1][j - 1][k];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 3; j++) {
				sb.append(counts[c][d][j] + counts[a - 1][b - 1][j] - counts[a - 1][d][j] - counts[c][b - 1][j])
						.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}