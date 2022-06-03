import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String origin = br.readLine();
		int[] originCount = new int[26];
		for(int i = 0; i < origin.length(); i++) {
			originCount[origin.charAt(i) - 'A']++;
		}
		int count = 0;
		for(int i = 1; i < N; i++) {
			String comp = br.readLine();
			int[] compCount = new int[26];
			for(int idx = 0; idx < comp.length(); idx++) {
				compCount[comp.charAt(idx) - 'A']++;
			}
			int oC = 0;
			int cC = 0;
			for(int alpha = 0; alpha < 26; alpha++) {
				if (originCount[alpha] > compCount[alpha]) {
					oC += originCount[alpha] - compCount[alpha];
				}
				else if (originCount[alpha] < compCount[alpha]) {
					cC += compCount[alpha] - originCount[alpha];
				}
			}
			if ((oC | cC) <= 1) {
				count++;
			}
		}
		System.out.println(count);
	}
}