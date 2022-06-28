import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge{
		int dest;
		long value;
		public Edge(int dest, long value) {
			this.dest = dest;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Edge>[] graph = new ArrayList[N + 1];
		long[] costs = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			costs[i] = 100_000 * 1_000;
		}
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.value - o2.value < 0) {
					return -1;
				}
				else if (o1.value - o2.value > 0) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		pq.add(new Edge(start, 0));
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if (now.dest == end) {
				System.out.println(now.value);
				return;
			}
			if (now.value > costs[now.dest]) {
				continue;
			}
			costs[now.dest] = now.value;
			for(Edge next : graph[now.dest]) {
				if (now.value + next.value >= costs[next.dest]) {
					continue;
				}
				costs[next.dest] = now.value + next.value;
				pq.add(new Edge(next.dest, costs[next.dest]));
			}
		}
	}
}