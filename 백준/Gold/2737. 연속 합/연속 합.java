import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			long N = Long.parseLong(br.readLine());
			int count = 0;
			long i = 2;
			while (true) {
				if (N - i * (i - 1) / 2 < i) {
					break;
				}
				if ((N - i * (i - 1) / 2) % i == 0) {
					count++;
				}
				i++;
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}
