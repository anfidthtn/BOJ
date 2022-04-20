import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pw = br.readLine();
		if(pw.length() == 0 || pw.charAt(0) == '0') {
			System.out.print(0);
			return;
		}
		int[] ways = new int[pw.length() + 1];
		ways[0] = 1;
		ways[1] = 1;
		for(int i = 2; i <= pw.length(); i++) {
			int num = (pw.charAt(i - 2) - '0') * 10 + pw.charAt(i - 1) - '0';
			if (num == 10 || num == 20) {
				ways[i] = ways[i - 2];
			}
			else if (num % 10 == 0) {
				System.out.println(0);
				return;
			}
			else if (10 < num && num <= 26) {
				ways[i] = ways[i - 1] + ways[i - 2];
			}
			else if (0 < num){
				if (i > 2) {
					ways[i] = ways[i - 1];
				}
				else {
					ways[i] = 1;
				}
			}
//			System.out.println(num + " " + ways[i]);
			ways[i] %= 1000000;
		}
		System.out.print(ways[pw.length()]);
//		for(int num : ways) {
//			System.out.println(num);
//		}
	}
}