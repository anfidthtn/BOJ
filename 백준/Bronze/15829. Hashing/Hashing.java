import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String s = br.readLine();
		long hash = 0;
		for(int i = 0; i < L; i++) {
			hash *= 31;
			hash += s.charAt(L - i - 1) - 'a' + 1;
			hash %= 1234567891;
		}
		System.out.print(hash);
	}
}