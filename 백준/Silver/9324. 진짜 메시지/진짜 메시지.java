import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			if (check(br.readLine())) {
				sb.append("OK\n");
			}
			else {
				sb.append("FAKE\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static boolean check(String message) {
		int[] count = new int[26];
		for(int idx = 0; idx < message.length(); idx++) {
			if (++count[message.charAt(idx) - 'A'] == 3) {
				if (idx == message.length() - 1) {
					return false;
				}
				if (message.charAt(idx) != message.charAt(idx + 1)) {
					return false;
				}
				idx++;
				count[message.charAt(idx) - 'A'] = 0;
			}
		}
		return true;
	}
}