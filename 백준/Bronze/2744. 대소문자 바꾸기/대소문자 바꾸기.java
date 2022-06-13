import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		for(int i = 0; i < str.length(); i++) {
			if ('a' <= str.charAt(i) && str.charAt(i) <= 'z') {
				System.out.print((char) (str.charAt(i) - 'a' + 'A'));
			}
			else {
				System.out.print((char) (str.charAt(i) - 'A' + 'a'));
			}
		}
	}
}