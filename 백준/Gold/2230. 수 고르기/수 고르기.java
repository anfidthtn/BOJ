import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M;
	static TreeSet<Long> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		long ans = 1l << 35;
		set = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			Long min = set.floor(num - M);
			if (min == null) {
				min = -(1l << 35);
			}
			ans = Math.min(ans, num - min);
			Long max = set.ceiling(num + M);
			if (max == null) {
				max = 1l << 35;
			}
			ans = Math.min(ans, max - num);
			set.add(num);
		}
		System.out.print(ans);
	}
}