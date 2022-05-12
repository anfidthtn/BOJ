import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int link;
		int weight;

		public Edge(int link, int weight) {
			this.link = link;
			this.weight = weight;
		}
	}

	static List<List<Edge>> graph;
	static long[] innerWeights;
	static boolean[] visited;
	static final int MODNUM = 1_000_000_007;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		innerWeights = new long[N + 1];
		visited = new boolean[N + 1];
		result = 0;
		graph.add(null);
		for (int i = 1; i <= N; i++) {
			graph.add(new LinkedList<>());
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Edge(b, w));
			graph.get(b).add(new Edge(a, w));
		}
		
		visited[1] = true;
		dfs(1, 0);
		System.out.println(result);
	}

	public static void dfs(int now, int parentWeight) {
		long childSum = 0;
		Queue<Long> nexts = new LinkedList<>();
		for (Edge next : graph.get(now)) {
			if (visited[next.link]) {
				continue;
			}
			visited[next.link] = true;
			dfs(next.link, next.weight);
			childSum += innerWeights[next.link];
			nexts.add(innerWeights[next.link]);
			childSum %= MODNUM;
		}
		innerWeights[now] = parentWeight;
		innerWeights[now] += (childSum * parentWeight) % MODNUM;
		innerWeights[now] %= MODNUM;
		result += innerWeights[now];
		result %= MODNUM;
		while (!nexts.isEmpty()) {
			long realNextWeight = nexts.poll();
			childSum -= realNextWeight;
			if (childSum < 0) {
				childSum += MODNUM;
			}
			result += (realNextWeight * childSum) % MODNUM;
			result %= MODNUM;
		}
	}
}