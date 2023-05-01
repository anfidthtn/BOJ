import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[][][] ans;
	static final long NONVALUE = -101010112123l;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ans = new long[21][21][21];
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 20; j++) {
				for (int k = 0; k <= 20; k++) {
					ans[i][j][k] = NONVALUE;
				}
			}
		}
		while (true) {
			String line = br.readLine();
			if (line.equals("-1 -1 -1")) {
				break;
			}
			StringTokenizer st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
					.append(getAns(a, b, c)).append("\n");
		}
		System.out.print(sb);
	}

	public static long getAns(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		if (a > 20 || b > 20 || c > 20) {
			return getAns(20, 20, 20);
		}
		if (ans[a][b][c] != NONVALUE) {
			return ans[a][b][c];
		}
		long temp = 0;
		if (a < b && b < c) {
			temp = getAns(a, b, c - 1) + getAns(a, b - 1, c - 1) - getAns(a, b - 1, c);
		} else {
			temp = getAns(a - 1, b, c) + getAns(a - 1, b - 1, c) + getAns(a - 1, b, c - 1)
					- getAns(a - 1, b - 1, c - 1);
		}
		ans[a][b][c] = temp;
		return ans[a][b][c];
	}
}