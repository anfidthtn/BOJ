import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(weight, ((Edge)o).weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, weight));
			adj[b].add(new Edge(a, weight));
		}
		
		long totalCost = 0;
		int maxBridgeLen = 0;
		int findCount = 1;
		boolean[] isLinked = new boolean[N + 1];
		isLinked[1] = true;
		PriorityQueue<Edge> pq = new PriorityQueue<>(adj[1]);
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if (isLinked[now.to]) continue;
			findCount++;
			isLinked[now.to] = true;
			totalCost += now.weight;
			maxBridgeLen = Math.max(maxBridgeLen, now.weight);
			if (findCount == N) break;
			for(Edge next : adj[now.to]) {
				if (isLinked[next.to]) continue;
				pq.add(next);
			}
		}
		System.out.print(totalCost - maxBridgeLen);
		
	}
}