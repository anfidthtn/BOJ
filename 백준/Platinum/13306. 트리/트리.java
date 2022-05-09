import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] parentlink = new int[N + 1];
		boolean[] result = new boolean[Q];
		String[] query = new String[N - 1 + Q];

		for (int i = 2; i <= N; i++) {
			parentlink[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N - 1 + Q; i++) {
			query[i] = br.readLine();
		}

		int[] parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = N - 1 + Q - 1, rIdx = Q - 1; i >= 0; i--) {
			st = new StringTokenizer(query[i]);
			if (st.nextToken().charAt(0) == '0') {
				int child = Integer.parseInt(st.nextToken());
				union(parent, child, parentlink[child]);
			}
			else {
				result[rIdx--] = queryExcute(parent, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			if (result[i]) {
				sb.append("YES");
			}
			else {
				sb.append("NO");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static boolean queryExcute(int[] parent, int a, int b) {
		if (getParent(parent, a) == getParent(parent, b)) {
			return true;
		}
		return false;
	}
	public static void union(int[] parent, int a, int b) {
		int pa = getParent(parent, a);
		int pb = getParent(parent, b);
		parent[pb] = pa;
	}

	public static int getParent(int[] parent, int now) {
		if (parent[now] == now) {
			return now;
		}
		return parent[now] = getParent(parent, parent[now]);
	}
}