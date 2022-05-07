import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class SccNode {
		int count;
		Set<Integer> link;

		public SccNode() {
			count = 0;
			link = new HashSet<>();
		}
	}

	static class SccInfo {
		int visitNum, sccGroupNum;
		int[] visited, sccIdx;
		List<Integer>[] graph;
		Stack<Integer> stack;
		List<SccNode> sccGraph;

		public SccInfo(int N) {
			visitNum = 0;
			visited = new int[N];
			sccGroupNum = 0;
			sccIdx = new int[N];
			graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				visited[i] = -1;
				sccIdx[i] = -1;
				graph[i] = new ArrayList<>();
			}
			stack = new Stack<>();
			sccGraph = new ArrayList<>();
		}

		public int scc(int now) {
			visited[now] = visitNum++;
			stack.add(now);

			int result = visited[now];
			for (int next : graph[now]) {
				if (visited[next] == -1) {
					result = Math.min(result, scc(next));
				} else if (sccIdx[next] == -1) {
					result = Math.min(result, visited[next]);
				}
			}

			if (result == visited[now]) {
				sccGraph.add(new SccNode());
				while (true) {
					int top = stack.pop();
					sccIdx[top] = sccGroupNum;
					if (top == now) {
						break;
					}
				}
				sccGroupNum++;
			}
			return result;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		SccInfo info = new SccInfo(N);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int dist = Integer.parseInt(st.nextToken());
			if (i - dist >= 0) {
				info.graph[i].add(i - dist);
			}
			if (i + dist < N) {
				info.graph[i].add(i + dist);
			}
		}
		for (int i = 0; i < N; i++) {
			if (info.visited[i] == -1) {
				info.scc(i);
			}
		}
		for (int i = 0; i < N; i++) {
			info.sccGraph.get(info.sccIdx[i]).count++;
			for(int next : info.graph[i]) {
				if (info.sccIdx[i] == info.sccIdx[next]) {
					continue;
				}
				info.sccGraph.get(info.sccIdx[i]).link.add(info.sccIdx[next]);
			}
		}
		int startPoint = Integer.parseInt(br.readLine()) - 1;
		int[] maxCount = new int[info.sccGroupNum];
		int[] totalMax = new int[1];
		dfs(info, maxCount, totalMax, info.sccIdx[startPoint], 0);
		System.out.println(maxCount[0]);
	}
	public static void dfs(SccInfo info, int[] maxCount, int[] totalMax, int now, int beforeCount) {
		if (beforeCount + info.sccGraph.get(now).count <= maxCount[now]) {
			return;
		}
		maxCount[0] = Math.max(maxCount[0], beforeCount + info.sccGraph.get(now).count);
		for(int next : info.sccGraph.get(now).link) {
			dfs(info, maxCount, totalMax, next, beforeCount + info.sccGraph.get(now).count);
		}
	}
}