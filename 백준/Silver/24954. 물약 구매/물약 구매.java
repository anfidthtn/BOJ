import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] prices;
	static boolean[] selected;
	static int[][][] infos;

	static int minCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		minCost = 1 << 30;
		N = Integer.parseInt(br.readLine());
		prices = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		selected = new boolean[N];
		infos = new int[N][][];
		for (int i = 0; i < N; i++) {
			int P = Integer.parseInt(br.readLine());
			infos[i] = new int[P + 1][];
			for (int p = 0; p < P; p++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				infos[i][p] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };
			}
		}
		check(0, 0);
		System.out.println(minCost);
	}

	public static void check(int idx, int cost) {
		if (minCost <= cost) {
			return;
		}
		if (idx == N) {
			minCost = cost;
			return;
		}
		for(int i = 0; i < N; i++) {
			if (selected[i]) {
				continue;
			}
			selected[i] = true;
			for(int p = 0; p < infos[i].length - 1; p++) {
				prices[infos[i][p][0]] -= infos[i][p][1];
			}
			check(idx + 1, cost + Math.max(1, prices[i]));
			for(int p = 0; p < infos[i].length - 1; p++) {
				prices[infos[i][p][0]] += infos[i][p][1];
			}
			selected[i] = false;
		}
	}
}