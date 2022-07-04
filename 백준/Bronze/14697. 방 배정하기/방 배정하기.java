import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		for (int a = 0; a <= N; a += A) {
			for (int b = 0; a + b <= N; b += B) {
				for (int c = 0; a + b + c <= N; c += C) {
					if (a + b + c == N) {
						System.out.println(1);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}
}