import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] counts = new int[N + 1][10];
		for(int i = 0; i < 10; i++) {
			counts[1][i] = 1;
		}
		for(int i = 2; i <= N; i++) {
			for(int digit = 0; digit < 10; digit++) {
				for(int prvDigit = digit; prvDigit >= 0; prvDigit--) {
					counts[i][digit] += counts[i - 1][prvDigit];
				}
				counts[i][digit] %= 10007;
			}
		}
		int sum = 0;
		for(int i = 0; i < 10; i++) {
			sum += counts[N][i];
		}
		System.out.println(sum % 10007);
	}
}