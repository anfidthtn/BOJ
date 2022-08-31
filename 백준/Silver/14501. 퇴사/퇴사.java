import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] times;
	static int[] prices;
	static int maxPrices;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		times = new int[N];
		prices = new int[N];
		maxPrices = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			prices[i] = Integer.parseInt(st.nextToken());
		}
		check(0, 0);
		System.out.println(maxPrices);
	}

	public static void check(int idx, int sum) {
		if (idx == N) {
			maxPrices = Math.max(maxPrices, sum);
			return;
		}
		if(idx + times[idx] <= N) {
			check(idx + times[idx], sum + prices[idx]);
		}
		check(idx + 1, sum);
	}
}