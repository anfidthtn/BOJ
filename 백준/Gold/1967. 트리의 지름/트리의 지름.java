import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int end, dist;

		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
	}

	static List<Node>[] graph;

	static int farIdx;
	static int farDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		farIdx = 0;
		farDist = 0;
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		getNode(1, 0, visited);
		
		visited = new boolean[N + 1];
		visited[farIdx] = true;
		farDist = 0;
		getNode(farIdx, 0, visited);
		System.out.println(farDist);
	}

	public static void getNode(int now, int nowDist, boolean[] visited) {
		if (farDist <= nowDist) {
			farIdx = now;
			farDist = nowDist;
		}
		for (Node node : graph[now]) {
			if (visited[node.end]) {
				continue;
			}
			visited[node.end] = true;
			getNode(node.end, nowDist + node.dist, visited);
		}
	}
}