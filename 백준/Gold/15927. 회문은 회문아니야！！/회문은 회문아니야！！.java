import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		boolean full = true;
		boolean pal = true;
		for(int i = 0; i < len; i++) {
			if (full && str.charAt(i) != str.charAt(0)) {
				full = false;
			}
			if (str.charAt(i) != str.charAt(len - i - 1)) {
				pal = false;
				break;
			}
		}
		if (!pal) {
			System.out.println(len);
		}
		else if (!full) {
			System.out.println(len - 1);
		}
		else {
			System.out.println(-1);
		}
	}
}