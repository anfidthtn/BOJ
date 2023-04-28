import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		long amount;
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			long N = Long.parseLong(st.nextToken());
			long ans = N;
			long K = Long.parseLong(st.nextToken());
			while (N >= K) {
				amount = N / K;
				ans += amount;
				N %= K;
				N += amount;
			}
			System.out.println(ans);
		}
	}
}