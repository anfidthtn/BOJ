import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder[] sbs = new StringBuilder[2];
		sbs[0] = new StringBuilder();
		sbs[1] = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sbs[i % 2].append("*");
			sbs[(i + 1) % 2].append(" ");
		}
		for(int i = 0; i < N; i++) {
			System.out.println(sbs[0]);
			System.out.println(sbs[1]);
		}
	}
}