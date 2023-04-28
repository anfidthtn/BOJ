import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final char[][] ans = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
			{ 'M', 'N', 'O', '.' } };
	static char[][] now;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		now = new char[4][];
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 4; j++) {
				if (str.charAt(j) == '.') {
					continue;
				}
				ans += getdist(str.charAt(j), i, j);
			}
		}
		System.out.print(ans);
	}

	public static int getdist(char target, int r, int c) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (ans[i][j] == target) {
					return Math.abs(r - i) + Math.abs(c - j);
				}
			}
		}
		return -1;
	}
}