import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String text = br.readLine();
		String pass = br.readLine();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ' ') {
				sb.append(' ');
			} else {
				sb.append((char) ((text.charAt(i) - pass.charAt(i % pass.length()) + 25) % 26 + 'a'));
			}
		}
		System.out.println(sb.toString());
	}
}