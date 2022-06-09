import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[b].add(a);
		}
		boolean[] visited = new boolean[N + 1];
		int target = Integer.parseInt(br.readLine());
		visited[target] = true;
		System.out.print(dfs(graph, target, visited));
	}
	public static int dfs(List<Integer>[] graph, int now, boolean[] visited) {
		int result = 0;
		for(int next : graph[now]) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			result++;
			result += dfs(graph, next, visited);
		}
		return result;
	}
}