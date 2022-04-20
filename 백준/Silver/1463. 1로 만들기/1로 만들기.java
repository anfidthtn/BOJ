import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cases = new int[N + 1];
		for(int i = 2; i <= N; i++) {
			cases[i] = cases[i - 1] + 1;
			if (i % 3 == 0) {
				cases[i] = Math.min(cases[i], cases[i / 3] + 1);
			}
			if ((i & 1) == 0) {
				cases[i] = Math.min(cases[i], cases[i / 2] + 1);
			}
		}
		System.out.println(cases[N]);
	}
}