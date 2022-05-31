import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static long order = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		List<Integer>[] link = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		boolean[] visited = new boolean[N + 1];
		visited[R] = true;
		System.out.println(dfs(link, visited, R, 0));
	}
	public static long dfs(List<Integer>[] link, boolean[] visited, int now, long depth) {
		long result = order * depth;
		order++;
		link[now].sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		for(int next : link[now]) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			result += dfs(link, visited, next, depth + 1);
		}
		return result;
	}
}