import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		if (str.length() == 1) {
			System.out.println("NO");
			return;
		}
		for (int i = 0; i < str.length() - 1; i++) {
			int left = 1;
			int right = 1;
			for (int k = 0; k <= i; k++) {
				left *= str.charAt(k) - '0';
			}
			for (int k = i + 1; k < str.length(); k++) {
				right *= str.charAt(k) - '0';
			}
			if (left == right) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}
}