import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Query {
		char classNum;
		int idx;

		public Query(char classNum, int idx) {
			this.classNum = classNum;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		List<Set<Integer>> graph = new ArrayList<>();
		int[] distance = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			distance[i] = 1_000_000;
		}
		graph.add(null);
		for (int i = 1; i <= N; i++) {
			graph.add(new HashSet<>());
		}
		int[] edgeStart = new int[M + 1];
		int[] edgeEnd = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edgeStart[i] = a;
			edgeEnd[i] = b;
			graph.get(a).add(b);
		}
		Query[] querys = new Query[Q];
		int printCount = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			char classNum = st.nextToken().charAt(0);
			int idx = Integer.parseInt(st.nextToken());
			if (classNum == 'U') {
				graph.get(edgeStart[idx]).remove(edgeEnd[idx]);
			} else {
				printCount++;
			}
			querys[i] = new Query(classNum, idx);
		}
		
		bfs(graph, distance, 1);

		int[] results = new int[printCount];
		for (int i = Q - 1; i >= 0; i--) {
			char classNum = querys[i].classNum;
			int idx = querys[i].idx;
			if (classNum == 'U') {
				graph.get(edgeStart[idx]).add(edgeEnd[idx]);
				if (distance[edgeStart[idx]] + 1 < distance[edgeEnd[idx]]) {
					// 1 ~ 간선시작점 거리에서 + 1해서 기존 1 ~ 간선 도착점 거리를 갱신시킬 수 있다면 갱신
					distance[edgeEnd[idx]] = distance[edgeStart[idx]] + 1;
					// 갱신 이후 해당 지점에서 지속 갱신
					bfs(graph, distance, edgeEnd[idx]);
				}
			} else {
				results[--printCount] = distance[idx];
				if(results[printCount] == 1_000_000) {
					results[printCount] = -1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int result : results) {
			sb.append(result).append("\n");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}

	public static void bfs(List<Set<Integer>> graph, int[] distance, int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : graph.get(now)) {
				if (distance[now] + 1 >= distance[next]) {
					// 현재 지점까지 거리 + 1보다 다음 지점에 같거나 더 빨리갈 수 있으면 갱신되지 않는다.
					continue;
				}
				// 갱신할 수 있으면 갱신한다.
				distance[next] = distance[now] + 1;
				queue.add(next);
			}
		}
	}
}