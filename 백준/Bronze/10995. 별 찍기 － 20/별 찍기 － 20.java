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
			for(int j = 0; j < 2 * N; j++) {
				if ((i + j) % 2 == 0) {
					sbs[i].append("*");
				}
				else {
					sbs[i].append(" ");
				}
			}
		}
		for(int i = 0; i < N; i++) {
			System.out.println(sbs[i]);
		}
	}
}