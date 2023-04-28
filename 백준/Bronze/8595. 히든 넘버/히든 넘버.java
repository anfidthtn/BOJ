import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long ans = 0;
		StringBuilder sb = new StringBuilder();
		char[] line = br.readLine().toCharArray();
		for (char c : line) {
			if ('0' <= c && c <= '9') {
				sb.append(c);
			} else {
				sb.append(" ");
			}
		}
		StringTokenizer st = new StringTokenizer(sb.toString());

		while (st.hasMoreTokens()) {
			ans += Integer.parseInt(st.nextToken());
		}
		System.out.print(ans);
	}
}