import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			long N = Long.parseLong(br.readLine());
			if (N <= 1) {
				N = 2;
			}
			N--;
			while(true) {
				N++;
				boolean prime = true;
				for(long i = 2; i * i <= N; i++) {
					if (N % i == 0) {
						prime = false;
						break;
					}
				}
				if (prime) {
					break;
				}
			}
			sb.append(N).append("\n");
		}
		System.out.print(sb.toString());
	}
}