import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long count = 0;
		for(long i = 2; i <= N / 2; i++) {
			count += (N / i - 1) * i;
			count %= 1_000_000;
		}
		System.out.println(count);
	}
}