import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] L;
	static int[] J;

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		L = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		J = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ans = 0;
		check(0, 100, 0);
		System.out.print(ans);
	}

	public static void check(int idx, int nowL, int nowJ) {
		ans = Math.max(ans, nowJ);
		if (idx == N) {
			return;
		}
		if (nowL > L[idx]) {
			check(idx + 1, nowL - L[idx], nowJ + J[idx]);
		}
		check(idx + 1, nowL, nowJ);
	}
}