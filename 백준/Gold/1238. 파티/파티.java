import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N, M, X;
	static Map<Integer, Integer>[] oriGraph;
	static Map<Integer, Integer>[] revGraph;

	static class Node {
		int end, dist;

		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		oriGraph = new TreeMap[N + 1];
		revGraph = new TreeMap[N + 1];
		for (int i = 1; i <= N; i++) {
			oriGraph[i] = new TreeMap<>();
			revGraph[i] = new TreeMap<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			oriGraph[a].put(b, d);
			revGraph[b].put(a, d);
		}
		int[] oD = getDist(oriGraph);
		int[] rD = getDist(revGraph);
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(oD[i] + rD[i], answer);
		}
		System.out.println(answer);
	}

	public static int[] getDist(Map<Integer, Integer>[] graph) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE >> 5);
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		dist[X] = 0;
		pq.add(new Node(X, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.end] < now.dist) {
				continue;
			}
			for (int next : graph[now.end].keySet()) {
				int nextDist = graph[now.end].get(next) + now.dist;
				if (dist[next] <= nextDist) {
					continue;
				}
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
		return dist;
	}
}