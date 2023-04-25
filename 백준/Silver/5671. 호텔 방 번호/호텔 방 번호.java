import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] cases;
	static boolean[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		count = new boolean[10];
		cases = new int[5001];
		for (int i = 1; i <= 5000; i++) {
			check(i);
			cases[i] += cases[i - 1];
		}
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			sb.append(cases[M] - cases[N - 1]).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void check(int x) {
		int y = x;
		Arrays.fill(count, false);
		while (x > 0) {
			if (count[x % 10]) {
				return;
			}
			count[x % 10] = true;
			x /= 10;
		}
		cases[y] = 1;
	}
}