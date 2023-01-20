import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (check(br.readLine())) {
			System.out.print("◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!");
		}
		else {
			System.out.print("흥칫뿡!! <(￣ ﹌ ￣)>");
		}
	}
	public static boolean check(String str) {
		if (str.length() == 1) {
			return true;
		}
		int diff = str.charAt(0) - str.charAt(1);
		for(int i = 2; i < str.length(); i++) {
			if (diff != str.charAt(i - 1) - str.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}