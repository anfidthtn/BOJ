import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			int total = N;
			int rev = 0;
			while (N > 0) {
				rev *= 10;
				rev += N % 10;
				N /= 10;
			}
			total += rev;
			String str = String.valueOf(total);
			boolean OK = true;
			for(int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
					OK = false;
					break;
				}
			}
			sb.append(OK ? "YES\n" : "NO\n");
		}
		System.out.print(sb.toString());
	}
}