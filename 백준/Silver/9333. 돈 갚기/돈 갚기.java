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
			String[] strs;
			strs = st.nextToken().split("\\.");
			long R = 10000 + Integer.parseInt(strs[0]) * 100 + Integer.parseInt(strs[1]);			
			strs = st.nextToken().split("\\.");
			long B = Integer.parseInt(strs[0]) * 1000 + Integer.parseInt(strs[1]) * 10;			
			strs = st.nextToken().split("\\.");
			long M = Integer.parseInt(strs[0]) * 1000 + Integer.parseInt(strs[1]) * 10;
			if (nextB(B, R) - M >= B) {
				sb.append("impossible\n");
				continue;
			}
			boolean end = false;
			for(int i = 1; i <= 1200; i++) {
				B = nextB(B, R);
				B -= M;
				if (B <= 0) {
					sb.append(i).append("\n");
					end = true;
					break;
				}
			}
			if (!end) {
				sb.append("impossible\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static long nextB(long B, long R) {
		long next = B * R / 10000;
		if (next % 10 >= 5) {
			next += 10;
		}
		next -= next % 10;
		return next;
	}
}