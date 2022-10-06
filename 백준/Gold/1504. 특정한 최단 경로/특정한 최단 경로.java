import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N, E;

	static class Node {
		int end, dist;

		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
	}

	static Map<Integer, Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new Map[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new TreeMap<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[a].put(b, d);
			graph[b].put(a, d);
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int toV1 = getMinDist(1, v1);
		int toV2 = getMinDist(1, v2);
		int v1toV2 = getMinDist(v1, v2);
		int v1toN = getMinDist(v1, N);
		int v2toN = getMinDist(v2, N);
		int answer = Math.min(toV1 + v1toV2 + v2toN, toV2 + v1toV2 + v1toN);
		if (answer >= Integer.MAX_VALUE >> 3) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}

	public static int getMinDist(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE >> 3);
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		pq.add(new Node(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.end == end) {
				return now.dist;
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
		return Integer.MAX_VALUE >> 3;
	}
}