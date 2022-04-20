import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 3;
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			int[][] bitCount = new int[2][63];
			for(int n = 0; n < N; n++) {
				long num = Long.parseLong(br.readLine());
				if (num == 0) continue;
				int bitIdx = 0;
				if (num < 0) {
					num *= -1;
					bitIdx = 1;
				}
				int bit = 0;
				while(num > 0) {
					bitCount[bitIdx][bit++] += num & 1;
					num >>= 1;
				}
			}
			for(int i = 0; i < 62; i++) {
				for(int bitIdx = 0; bitIdx < 2; bitIdx++) {
					bitCount[bitIdx][i + 1] += bitCount[bitIdx][i] >> 1;
					bitCount[bitIdx][i] &= 1;
				}
			}
			sb.append(result(bitCount)).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static char result(int[][] bitCount) {
		for(int i = 62; i >= 0; i--) {
			if (bitCount[0][i] > bitCount[1][i]) return '+';
			if (bitCount[0][i] < bitCount[1][i]) return '-';
		}
		return '0';
	}
}