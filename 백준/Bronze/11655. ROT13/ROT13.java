import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if ('a' <= str.charAt(i) && str.charAt(i) <= 'z') {
				sb.append((char)((str.charAt(i) - 'a' + 13) % 26 + 'a'));
			}
			else if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
				sb.append((char)((str.charAt(i) - 'A' + 13) % 26 + 'A'));
			}
			else {
				sb.append(str.charAt(i));
			}
		}
		System.out.print(sb.toString());
	}
}