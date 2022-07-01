import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] costs = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			for(int idx = i; idx <= N; idx++) {
				costs[idx] = Math.max(costs[idx], costs[idx - i] + cost);
			}
		}
		System.out.println(costs[N]);
	}
}