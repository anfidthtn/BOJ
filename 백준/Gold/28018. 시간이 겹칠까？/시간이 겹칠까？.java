import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] times;
	static int Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		times = new int[1010101];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[Integer.parseInt(st.nextToken())]++;
			times[Integer.parseInt(st.nextToken()) + 1]--;
		}
		for (int i = 1; i < 1010101; i++) {
			times[i] += times[i - 1];
		}
		Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			sb.append(times[Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.print(sb);
	}
}