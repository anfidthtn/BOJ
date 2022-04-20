import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			boolean[] isVisited = new boolean[N];
			isVisited[i] = true;
			if (dfs(adj, isVisited, i, 0)) {
				System.out.print(1);
				return;
			}
		}
		System.out.print(0);
		
	}
	public static boolean dfs(ArrayList<Integer>[] adj, boolean[] isVisited, int now, int depth) {
		if (depth == 4) return true;
		for(int next : adj[now]) {
			if (isVisited[next]) continue;
			isVisited[next] = true;
			if (dfs(adj, isVisited, next, depth + 1)) return true;
			isVisited[next] = false;
		}
		
		
		return false;
	}
}