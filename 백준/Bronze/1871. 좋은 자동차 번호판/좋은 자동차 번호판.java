import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			int a = 0;
			for(int j = 0; j < 3; j++) {
				a *= 26;
				a += s.charAt(j) - 'A';
			}
			int b = 0;
			for(int j = 4; j < 8; j++) {
				b *= 10;
				b += s.charAt(j) - '0';
			}
			if (Math.abs(a - b) <= 100) {
				System.out.println("nice");
			}
			else {
				System.out.println("not nice");
			}
		}
	}
}