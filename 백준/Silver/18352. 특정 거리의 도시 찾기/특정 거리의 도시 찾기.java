import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M, K, X;
	static Set<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new TreeSet[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new TreeSet<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(X);
		visited[X] = true;
		for (int i = 0; i < K && !queue.isEmpty(); i++) {
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				int now = queue.poll();
				for (int next : graph[now]) {
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		List<Integer> answer = new ArrayList<>(queue);
		answer.sort(Integer::compareTo);
		if (answer.isEmpty()) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int ans : answer) {
				sb.append(ans).append("\n");
			}
			System.out.print(sb.toString());
		}
	}
}