import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		int score = 0;
		char[] str= br.readLine().toCharArray();
		for (char c : str) {
			score += c - 'A' + 1;
		}
		System.out.println(score);
	}
}