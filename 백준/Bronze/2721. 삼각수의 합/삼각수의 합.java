import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int num = Integer.parseInt(br.readLine());
			long ans = 0;
			for (int k = 1; k <= num; k++) {
				ans += k * T(k + 1);
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static long T(int K) {
		long ret = 0;
		for (int i = 1; i <= K; i++) {
			ret += i;
		}
		return ret;
	}
}