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
			if (str.charAt(0) == 'P') {
				sb.append("skipped\n");
				continue;
			}
			int idx = 0;
			boolean plus = false;
			int a = 0;
			int b = 0;
			while(idx < str.length()) {
				if (str.charAt(idx) == '+') {
					plus = true;
					idx++;
					continue;
				}
				if (plus) {
					b *= 10;
					b += str.charAt(idx) - '0';
				}
				else {
					a *= 10;
					a += str.charAt(idx) - '0';
				}
				idx++;
			}
			sb.append(a + b).append("\n");
		}
		System.out.print(sb.toString());
	}
}