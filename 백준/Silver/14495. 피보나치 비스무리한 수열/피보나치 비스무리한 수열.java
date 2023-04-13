import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] ans = new long[200];
		ans[1] = 1;
		ans[2] = 1;
		ans[3] = 1;
		for (int i = 4; i <= N; i++) {
			ans[i] = ans[i - 3] + ans[i - 1];
		}
		System.out.println(ans[N]);
	}
}