import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] fibs = new long[1 + Integer.parseInt(br.readLine())];
		fibs[1] = 1;
		for(int i = 2; i < fibs.length; i++) {
			fibs[i] = fibs[i - 1] + fibs[i - 2];
		}
		System.out.println(fibs[fibs.length - 1]);
	}
}