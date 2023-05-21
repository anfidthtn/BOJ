import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class 구간 {
		int start, end;

		public 구간(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static List<List<Integer>> graph;

	static List<구간> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		list.add(null);
		graph = new ArrayList<>();
		graph.add(null);
		StringBuilder sb = new StringBuilder();
		for (int _ = 0; _ < N; _++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (q == 1) {
				graph.add(new ArrayList<>());
				구간 node = new 구간(a, b);
				list.add(node);
				for (int i = 1; i < list.size() - 1; i++) {
					link(list.get(i), node, i, list.size() - 1);
					link(node, list.get(i), list.size() - 1, i);
				}
			} else {
				boolean[] visited = new boolean[list.size()];
				visited[a] = true;
				if (check(visited, a, b)) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
			}
		}
		System.out.print(sb);
	}

	public static boolean check(boolean[] visited, int now, int dest) {
		if (now == dest) {
			return true;
		}
		for (int next : graph.get(now)) {
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			if (check(visited, next, dest)) {
				return true;
			}
		}
		return false;
	}

	public static void link(구간 A, 구간 B, int a, int b) {
		if (B.start < A.start && A.start < B.end || B.start < A.end && A.end < B.end) {
			graph.get(a).add(b);
		}
	}
}