import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] dist, cost;

	static long[][] mins;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new long[N];
		cost = new long[M];
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			cost[i] = Integer.parseInt(br.readLine());
		}
		mins = new long[N][M];
		System.out.println(getMin(0, 0));
	}

	public static long getMin(int nowCity, int day) {
		if (N - nowCity > M - day) {
			return 1l << 60;
		}
		if (nowCity == N) {
			return 0;
		}
		if (mins[nowCity][day] > 0) {
			return mins[nowCity][day];
		}
		mins[nowCity][day] = getMin(nowCity + 1, day + 1) + dist[nowCity] * cost[day];
		mins[nowCity][day] = Math.min(mins[nowCity][day], getMin(nowCity, day + 1));
		return mins[nowCity][day];
	}
}