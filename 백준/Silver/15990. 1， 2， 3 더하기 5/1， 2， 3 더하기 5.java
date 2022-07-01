import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[][] ways = new int[100_001][3];
		int max = 3;
		ways[1][0] = 1;
		ways[2][1] = 1;
		ways[3][0] = 1;
		ways[3][1] = 1;
		ways[3][2] = 1;
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int n = Integer.parseInt(br.readLine());
			while(max < n) {
				max++;
				ways[max][0] = (ways[max - 1][1] + ways[max - 1][2]) % 1_000_000_009;
				ways[max][1] = (ways[max - 2][0] + ways[max - 2][2]) % 1_000_000_009;
				ways[max][2] = (ways[max - 3][0] + ways[max - 3][1]) % 1_000_000_009;
			}
			sb.append(((long) ways[n][0] + ways[n][1] + ways[n][2]) % 1_000_000_009).append("\n");
		}
		System.out.print(sb.toString());
	}
}