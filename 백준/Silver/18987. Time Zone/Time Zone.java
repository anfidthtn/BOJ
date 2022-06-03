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
			int h = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int utc = (int)(Double.parseDouble(st.nextToken().substring(3)) * 10);
			utc -= 80;
			m += utc * 6;
			while(m < 0) {
				m += 60;
				h -= 1;
			}
			while(m >= 60) {
				m -= 60;
				h += 1;
			}
			while (h < 0) {
				h += 24;
			}
			while(h >= 24) {
				h -= 24;
			}
			if (h < 10) {
				sb.append(0);
			}
			sb.append(h).append(":");
			if(m < 10) {
				sb.append(0);
			}
			sb.append(m).append("\n");
		}
		System.out.print(sb.toString());
	}
}