import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] g1, g2;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		g1 = new ArrayList[N + 1];
		g2 = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			g1[i] = new ArrayList<>();
			g2[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g1[a].add(b);
			g2[b].add(a);
		}
		int ans = 0;
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			visited[i] = true;
			if (bfs1(i) + bfs2(i) == N - 1) {
				ans++;
			}
		}
		System.out.println(ans);
	}
	public static int bfs1(int x) {
		int ret = 0;
		for(int y : g1[x]) {
			if (visited[y]) {
				continue;
			}
			visited[y] = true;
			ret++;
			ret += bfs1(y);
		}
		return ret;
	}
	public static int bfs2(int x) {
		int ret = 0;
		for(int y : g2[x]) {
			if (visited[y]) {
				continue;
			}
			visited[y] = true;
			ret++;
			ret += bfs2(y);
		}
		return ret;
	}
}