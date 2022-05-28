import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			String s = st.nextToken(), t = st.nextToken();
			int sIdx = 0;
			for(int tIdx = 0; tIdx < t.length(); tIdx++) {
				if (sIdx == s.length()) {
					break;
				}
				if (s.charAt(sIdx) == t.charAt(tIdx)) {
					sIdx++;
				}
			}
			if (sIdx == s.length()) {
				sb.append("Yes\n");
			}
			else {
				sb.append("No\n");
			}
		}
		System.out.print(sb.toString());
	}
}