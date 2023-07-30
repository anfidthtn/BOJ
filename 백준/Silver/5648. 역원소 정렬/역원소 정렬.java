import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String line;
		int n = Integer.parseInt(st.nextToken());
		long[] ans = new long[n];
		int idx = 0;
		while(st.hasMoreTokens()) {
			ans[idx++] = reverse(Long.parseLong(st.nextToken()));			
		}
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			while(st.hasMoreTokens()) {
				ans[idx++] = reverse(Long.parseLong(st.nextToken()));			
			}
		}
		Arrays.sort(ans);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static long reverse(long x) {
		long res = 0;
		while (x > 0) {
			res *= 10;
			res += x % 10;
			x /= 10;
		}
		return res;
	}
}