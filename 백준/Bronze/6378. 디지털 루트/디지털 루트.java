import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(!(line = br.readLine()).equals("0")) {
			System.out.println(getRoot(line));
		}
	}
	public static int getRoot(String num) {
		int root = 0;
		for(int i = 0; i < num.length(); i++) {
			root += num.charAt(i) - '0';
		}
		return getRoot(root);
	}
	public static int getRoot(int num) {
		if (num < 10) {
			return num;
		}
		int root = 0;
		while(num > 0) {
			root += num % 10;
			num /= 10;
		}
		return getRoot(root);
	}
}