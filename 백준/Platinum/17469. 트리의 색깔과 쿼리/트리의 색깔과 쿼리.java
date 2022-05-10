import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] parentInfo = new int[N + 1];
		Set<Integer>[] color = new HashSet[N + 1];
		String[] query = new String[N - 1 + Q];
		int[] parent = new int[N + 1];
		int[] result = new int[Q];

		for (int i = 2; i <= N; i++) {
			parentInfo[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i <= N; i++) {
			color[i] = new HashSet<>();
			color[i].add(Integer.parseInt(br.readLine()));
			parent[i] = i;
		}

		for (int i = 0; i < N - 1 + Q; i++) {
			query[i] = br.readLine();
		}

		for (int rIdx = Q - 1, i = N - 1 + Q - 1; i >= 0; i--) {
			st = new StringTokenizer(query[i]);
			if (st.nextToken().charAt(0) == '1') {
				int a = Integer.parseInt(st.nextToken());
				merge(parent, color, a, parentInfo[a]);
			}
			else {
				result[rIdx--] = color[getParent(parent, Integer.parseInt(st.nextToken()))].size();
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void merge(int[] parent, Set<Integer>[] color, int a, int b) {
		int pa = getParent(parent, a);
		int pb = getParent(parent, b);
		if (color[pa].size() > color[pb].size()) {
			parent[pb] = pa;
			color[pa].addAll(color[pb]);
			color[pb] = null;
		} else {
			parent[pa] = pb;
			color[pb].addAll(color[pa]);
			color[pa] = null;
		}
	}

	public static int getParent(int[] parent, int now) {
		if (parent[now] == now) {
			return now;
		}
		return parent[now] = getParent(parent, parent[now]);
	}
}