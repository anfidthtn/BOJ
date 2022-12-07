import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] counts = new int[26];
		boolean ok = false;
		for(int i = 0; i < N; i++) {
			if(++counts[br.readLine().charAt(0) - 'a'] >= 5) {
				ok = true;
			}
		}
		if (!ok) {
			System.out.println("PREDAJA");
		}
		else {
			for(int i = 0; i < 26; i++) {
				if (counts[i] >= 5) {
					System.out.print((char)('a' + i));
				}
			}
		}
	}
}