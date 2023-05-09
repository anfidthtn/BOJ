import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] graph;
	static int ans;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int left = i - num;
			int right = i + num;
			if (left >= 0) {
				graph[i].add(left);
			}
			if (right < N) {
				graph[i].add(right);
			}
		}
		ans = 0;
		visited = new boolean[N];
		int start = Integer.parseInt(br.readLine()) - 1;
		visited[start] = true;
		dfs(start);
		System.out.print(ans);
	}

	public static void dfs(int now) {
		ans++;
		for (int next : graph[now]) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			dfs(next);
		}
	}
}