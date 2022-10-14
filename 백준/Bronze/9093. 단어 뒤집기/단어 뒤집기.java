import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				String str = st.nextToken();
				for(int idx = str.length() - 1; idx >= 0; idx--) {
					sb.append(str.charAt(idx));
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}