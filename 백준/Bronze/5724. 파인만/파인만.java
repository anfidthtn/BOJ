import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] ans = new int[101];
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			if (ans[N] == 0) {
				for (int i = 1; i <= N; i++) {
					ans[N] += i * i;
				}
			}
			sb.append(ans[N]).append("\n");
		}
		System.out.print(sb.toString());
	}
}