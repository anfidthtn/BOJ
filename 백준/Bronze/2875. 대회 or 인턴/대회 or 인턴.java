import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = 0;
		for(int k = 0; k <= K; k++) {
			if (N - k < 0) {
				continue;
			}
			if (M - (K - k) < 0) {
				continue;
			}
			max = Math.max(max, Math.min((N - k) / 2, (M - (K - k))));
		}
		System.out.println(max);
	}
}