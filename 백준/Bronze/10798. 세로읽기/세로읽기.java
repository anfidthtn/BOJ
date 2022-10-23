import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = new String[5];
		for(int i = 0; i < 5; i++) {
			strs[i] = br.readLine();
		}
		StringBuilder sb = new StringBuilder();
		for(int idx = 0; idx < 100; idx++) {
			for(int i = 0; i < 5; i++) {
				if (idx < strs[i].length()) {
					sb.append(strs[i].charAt(idx));
				}
			}
		}
		System.out.print(sb.toString());
	}
}