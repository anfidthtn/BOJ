import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder[] sbs = new StringBuilder[N];
		for(int i = 0; i < N; i++) {
			sbs[i] = new StringBuilder();
			for(int j = 0; j < i; j++) {
				sbs[i].append(" ");
			}
			for(int j = 0; j < 2 * N - 2 * i - 1; j++) {
				sbs[i].append("*");
			}
		}
		for(int i = 0; i < N; i++) {
			System.out.println(sbs[i]);
		}
		for(int i = N - 2; i >= 0; i--) {
			System.out.println(sbs[i]);
		}
	}
}