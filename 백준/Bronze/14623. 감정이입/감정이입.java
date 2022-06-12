import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long a = 0;
		long b = 0;
		String str1 = br.readLine();
		for(int i = 0; i < str1.length(); i++) {
			a <<= 1;
			a += str1.charAt(i) - '0';
		}
		String str2 = br.readLine();
		for(int i = 0; i < str2.length(); i++) {
			b <<= 1;
			b += str2.charAt(i) - '0';
		}
		System.out.println(Long.toBinaryString(a * b));
	}
}