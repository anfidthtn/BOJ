import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			String str = br.readLine();
			boolean[] check = new boolean[26];
			for(int i = 0; i < str.length(); i++) {
				if ('a' <= str.charAt(i) && str.charAt(i) <= 'z') {
					check[str.charAt(i) - 'a'] = true;
				}
				if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
					check[str.charAt(i) - 'A'] = true;
				}
			}
			boolean ok = true;
			for(boolean c : check) {
				if (!c) {
					ok = false;
					break;
				}
			}
			if (ok) {
				sb.append("pangram\n");
			}
			else {
				sb.append("missing ");
				for(int i = 0; i < 26; i++) {
					if (!check[i]) {
						sb.append((char) (i + 'a'));
					}
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}