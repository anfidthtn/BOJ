import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();
			int score = 0;
			for (char c : str) {
				if ('A' <= c && c <= 'Z') {
					score += c - 'A' + 1;
				}
			}
			if (score == 100) {
				System.out.println("PERFECT LIFE");
			} else {
				System.out.println(score);
			}
		}
	}
}