import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int[] nums = {1, 5, 10, 50};
		int N = Integer.parseInt(br.readLine());
		boolean[][] able = new boolean[N + 1][1101];
		able[0][0] = true;
		for(int i = 1; i <= N; i++) {
			for(int j = 1000; j >= 0; j--) {
				if (able[i - 1][j]) {
					for(int num : nums) {
						able[i][j + num] = true;
					}
				}
			}
		}
		int count = 0;
		for(int i = 0; i <= 1000; i++) {
			count += able[N][i] ? 1 : 0;
		}
		System.out.print(count);
	}
}