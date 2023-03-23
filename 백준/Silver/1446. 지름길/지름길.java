import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, D;
	static List<int[]>[] path;
	static int[] moves;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		path = new ArrayList[10001];
		moves = new int[10001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if (path[end] == null) {
				path[end] = new ArrayList<>();
			}
			path[end].add(new int[] { start, dist });
		}
		for (int i = 1; i <= D; i++) {
			moves[i] = moves[i - 1] + 1;
			if (path[i] != null) {
				for (int[] p : path[i]) {
					moves[i] = Math.min(moves[i], moves[p[0]] + p[1]);
				}
			}
		}
		System.out.print(moves[D]);
	}
}