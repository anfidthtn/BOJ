import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];
		int M = Integer.parseInt(br.readLine());
		List<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		visited[1] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		int ans = 0;
		for (int i = 0; i < 2; i++) {
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				int now = queue.poll();
				for (int next : graph[now]) {
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					ans++;
					queue.add(next);
				}
			}
		}
		System.out.println(ans);
	}
}