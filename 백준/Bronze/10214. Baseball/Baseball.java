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
			int y = 0;
			int k = 0;
			for(int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				y += Integer.parseInt(st.nextToken());
				k += Integer.parseInt(st.nextToken());
			}
			if (y > k) {
				sb.append("Yonsei\n");
			}
			else if (y < k) {
				sb.append("Korea\n");
			}
			else {
				sb.append("Draw\n");
			}
		}
		System.out.print(sb.toString());
	}
}