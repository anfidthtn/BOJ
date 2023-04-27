import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int[] count = new int[26];
		int ans = 0;
		while ((line = br.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) != ' ') {
					if(++count[line.charAt(i) - 'a'] > ans) {
						ans++;
					}
				}
			}
		}
		for(int i = 0; i < 26; i++) {
			if (count[i] == ans) {
				System.out.print((char)(i + 'a'));
			}
		}
	}
}