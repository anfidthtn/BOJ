import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int diff = -Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			int left = 0;
			int right = Integer.MAX_VALUE;
			int tempAns = -1;
			long mid = -1;
			while(left <= right) {
				mid = (left + right) >> 1;
				if (mid * (mid + 1) <= diff) {
					tempAns = (int) mid;
					left = (int) mid + 1;
				}
				else {
					right = (int) mid - 1;
				}
			}
			int ans = tempAns * 2;
			diff -= tempAns * (tempAns + 1);
			while(diff > 0) {
				diff -= (tempAns + 1);
				ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}