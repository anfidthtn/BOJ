import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parents;

	static class Edge {
		int start, end, amount;

		public Edge(int start, int end, int amount) {
			this.start = start;
			this.end = end;
			this.amount = amount;
		}
	}

	static PriorityQueue<Edge> edges;

	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		edges = new PriorityQueue<>((a, b) -> b.amount - a.amount);
		ans = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (d == 1) {
				union(a, b);
			} else {
				edges.add(new Edge(a, b, c));
			}
		}
		while (!edges.isEmpty()) {
			Edge now = edges.poll();
			if (find(now.start) != find(now.end)) {
				union(now.start, now.end);
			} else {
				ans += now.amount;
			}
		}
		System.out.print(ans);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return;
		}
		parents[b] = a;
	}

	public static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}

}