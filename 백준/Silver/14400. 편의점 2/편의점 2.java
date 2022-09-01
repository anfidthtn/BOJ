import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] Xs = new int[N];
		int[] Ys = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Xs[i] = Integer.parseInt(st.nextToken());
			Ys[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(Xs);
		Arrays.sort(Ys);
		int x = Xs[N / 2];
		int y = Ys[N / 2];

		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += Math.abs(Xs[i] - x);
			sum += Math.abs(Ys[i] - y);
		}
		System.out.println(sum);
	}
}