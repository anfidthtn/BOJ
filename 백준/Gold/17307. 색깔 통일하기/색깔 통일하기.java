import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] colors;
	static long[] L2R, R2L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		colors = new int[N + 2];
		L2R = new long[N + 2];
		R2L = new long[N + 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 2; i <= N; i++) {
			L2R[i] = (colors[i] < colors[i - 1] ? C : 0) + colors[i] - colors[i - 1] + L2R[i - 1];
		}
		for (int i = N - 1; i >= 0; i--) {
			R2L[i] = (colors[i] < colors[i + 1] ? C : 0) + colors[i] - colors[i + 1] + R2L[i + 1];
		}
		int ans = -1;
		long ans_cnt = 1l << 60;
		for (int i = 1; i <= N; i++) {
			long cnt = Math.max(L2R[N] - L2R[i], R2L[1] - R2L[i]);
			if (ans_cnt > cnt) {
				ans = i;
				ans_cnt = cnt;
			}
		}
		System.out.println(ans);
		System.out.println(ans_cnt);
	}
}