import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int distance = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		
		@SuppressWarnings("unchecked")
		List<Integer[]>[] adj = new LinkedList[V + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (adj[a] == null) adj[a] = new LinkedList<>();
			if (adj[b] == null) adj[b] = new LinkedList<>();
			adj[a].add(new Integer[] {b, c});
			adj[b].add(new Integer[] {a, c});
		}
		PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				if (o1[1] == o2[1]) return 0;
				if (o1[1] < o2[1]) return -1;
				return 1;
			}
		});
		
		boolean[] isVisited = new boolean[V + 1];
		isVisited[1] = true;
		dfs(adj, isVisited, pq, 1);
		System.out.print(distance);
	}
	public static void dfs(List<Integer[]>[] adj, boolean[] isVisited, PriorityQueue<Integer[]> pq, int node) {
		for (Integer[] edge : adj[node]) {
			if (isVisited[edge[0]]) continue;
			pq.add(edge);
		}
		while(!pq.isEmpty() && isVisited[pq.peek()[0]]) {
			pq.poll();
		}
		if (!pq.isEmpty()) {
			Integer[] edge = pq.poll();
			distance += edge[1];
			isVisited[edge[0]] = true;
			dfs(adj, isVisited, pq, edge[0]);
		}
	}
}