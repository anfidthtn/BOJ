import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class SccNode {
		int minValue;
		int minIdx;
		boolean hasParent;

		public SccNode() {
			this.minValue = Integer.MAX_VALUE;
			this.minIdx = -1;
			this.hasParent = false;
		}
	}

	static class SccInfo {
		int N;
		int[] costs;
		List<Integer>[] graph;
		int[] visit;
		int[] sccIdx;
		int visitNum;
		int sccGroupNum;
		Stack<Integer> stack;

		List<SccNode> sccGraph;

		public SccInfo(int N) {
			this.N = N;
			graph = new ArrayList[N];
			costs = new int[N];

			visit = new int[N];
			sccIdx = new int[N];

			visitNum = 0;
			sccGroupNum = 0;

			stack = new Stack<>();

			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
				visit[i] = -1;
				sccIdx[i] = -1;
			}

			sccGraph = new ArrayList<>();
		}

		public int scc(int now) {
			visit[now] = visitNum++;
			stack.add(now);

			int result = visit[now];
			for (int next : graph[now]) {
				if (visit[next] == -1) {
					result = Math.min(result, scc(next));
				} else if (sccIdx[next] == -1) {
					result = Math.min(result, visit[next]);
				}
			}
			if (result == visit[now]) {
				sccGraph.add(new SccNode());
				while (true) {
					int top = stack.pop();
					sccIdx[top] = sccGroupNum;
					if (sccGraph.get(sccGroupNum).minValue > costs[top]) {
						sccGraph.get(sccGroupNum).minValue = costs[top];
						sccGraph.get(sccGroupNum).minIdx = top;
					}
					if (top == now) {
						break;
					}
				}
				sccGroupNum++;
			}
			return result;
		}

		public void makeSccLink() {
			for (int now = 0; now < N; now++) {
				for (int next : graph[now]) {
					if (sccIdx[now] == sccIdx[next]) {
						continue;
					}
					sccGraph.get(sccIdx[next]).hasParent = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		SccInfo info = new SccInfo(N);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			info.costs[i] = Integer.parseInt(st.nextToken());
			String linkInfo = br.readLine();
			for (int j = 0; j < N; j++) {
				if (linkInfo.charAt(j) == 'Y') {
					info.graph[i].add(j);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			if (info.visit[i] == -1) {
				info.scc(i);
			}
		}
		info.makeSccLink();
		
		
		Set<Integer> installedPoint = new HashSet<>();
		List<Integer> minIdxList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			minIdxList.add(i);
		}
		minIdxList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return info.costs[o1] - info.costs[o2];
			}
		});
		
		double totalCost = 0;
		int count = 0;
		for (SccNode node : info.sccGraph) {
			if (!node.hasParent) {
				totalCost += node.minValue;
				installedPoint.add(node.minIdx);
				count++;
			}
		}
		for(int i = 0; i < N; i++) {
			if (installedPoint.contains(minIdxList.get(i))) {
				continue;
			}
			if (totalCost / count > info.costs[minIdxList.get(i)]) {
				totalCost += info.costs[minIdxList.get(i)];
				count++;
			}
			else {
				break;
			}
		}
		System.out.println(totalCost / count);
	}
}