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
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = 1_000_000;
			int max = -1_000_000;
			for(int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (num < min) {
					min = num;
				}
				if (num > max) {
					max = num;
				}
			}
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(sb.toString());
	}
}