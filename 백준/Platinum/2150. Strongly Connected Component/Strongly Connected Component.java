import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class SccInfo {
		int[] visit;
		int visitNum;
		Stack<Integer> stack;
		int[] sccIdx;
		int sccGroupNum;
		List<Integer>[] graph;
		List<List<Integer>> sccGraph;

		public SccInfo(int V) {
			graph = new ArrayList[V + 1];
			visit = new int[V + 1];
			sccIdx = new int[V + 1];
			visitNum = 0;
			sccGroupNum = 0;
			for (int i = 1; i <= V; i++) {
				visit[i] = -1;
				sccIdx[i] = -1;
				graph[i] = new ArrayList<>();
			}
			stack = new Stack<>();
			sccGraph = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		SccInfo info = new SccInfo(V);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			info.graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= V; i++) {
			if (info.visit[i] == -1) {
				scc(i, info);
			}
		}

		System.out.println(info.sccGroupNum);
		StringBuilder sb = new StringBuilder();
		for (List<Integer> list : info.sccGraph) {
			list.sort(Integer::compareTo);
		}
		info.sccGraph.sort(new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});
		for (List<Integer> list : info.sccGraph) {
			for (int num : list) {
				sb.append(num).append(" ");
			}
			sb.append("-1\n");
		}
		System.out.println(sb.toString());
	}

	public static int scc(int now, SccInfo info) {
		info.visit[now] = info.visitNum++;
		info.stack.add(now);

		int result = info.visit[now];
		for (int next : info.graph[now]) {
			if (info.visit[next] == -1) {
				result = Math.min(result, scc(next, info));
			} else if (info.sccIdx[next] == -1) {
				result = Math.min(result, info.visit[next]);
			}
		}
		if (result == info.visit[now]) {
			info.sccGraph.add(new ArrayList<>());
			while (true) {
				int top = info.stack.pop();
				info.sccIdx[top] = info.sccGroupNum;
				info.sccGraph.get(info.sccGroupNum).add(top);
				if (now == top) {
					break;
				}
			}
			info.sccGroupNum++;
		}
		return result;
	}
}