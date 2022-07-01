import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>(N + 1);
		graph.add(null);
		for(int i = 1; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		int[] count = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		visited[R] = true;
		dfs(count, visited, R);
		for(int i = 0; i < Q; i++) {
			bw.write(String.valueOf(count[Integer.parseInt(br.readLine())]));
			bw.write('\n');
		}
		bw.flush();
	}
	public static int dfs(int[] count, boolean[] visited, int now) {
		int childs = 1;
		for(int next : graph.get(now)) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			childs += dfs(count, visited, next);
		}
		return count[now] = childs;
	}
}