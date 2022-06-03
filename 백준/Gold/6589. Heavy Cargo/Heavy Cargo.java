import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static class Edge{
		int dest;
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1;; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0){
				break;
			}
			int R = Integer.parseInt(st.nextToken());
			if (tNum > 1) {
				sb.append("\n");
			}
			sb.append("Scenario #").append(tNum).append("\n");
			Map<String, Integer> cityIdx = new TreeMap<>();
			List<Edge>[] link = new ArrayList[N];
			int[] maxWeight = new int[N];
			for(int i = 0; i < N; i++) {
				link[i] = new ArrayList<>();
//				maxWeight[i] = 1_000_000;
			}
			int idx = 0;
			for(int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				int weight = Integer.parseInt(st.nextToken());
				if (!cityIdx.containsKey(a)) {
					cityIdx.put(a, idx++);
				}
				if (!cityIdx.containsKey(b)) {
					cityIdx.put(b, idx++);
				}
				int aIdx = cityIdx.get(a);
				int bIdx = cityIdx.get(b);
				link[aIdx].add(new Edge(bIdx, weight));
				link[bIdx].add(new Edge(aIdx, weight));
			}
			for(int i = 0; i < N; i++) {
				link[i].sort(new Comparator<Edge>() {
					@Override
					public int compare(Edge o1, Edge o2) {
						return o2.weight - o1.weight;
					}
				});
			}
			st = new StringTokenizer(br.readLine());
			int startIdx = cityIdx.get(st.nextToken());
			int endIdx = cityIdx.get(st.nextToken());
			maxWeight[startIdx] = 1_000_000;
			Queue<Integer> queue = new LinkedList<>();
			queue.add(startIdx);
			while(!queue.isEmpty()) {
				int qSize = queue.size();
				Set<Integer> visitSet = new HashSet<>();
				for(int i = 0; i < qSize; i++) {
					int now = queue.poll();
					for(Edge next : link[now]) {
						if (Math.min(maxWeight[now], next.weight) <= maxWeight[next.dest]) {
							continue;
						}
						visitSet.add(next.dest);
						maxWeight[next.dest] = Math.min(maxWeight[now], next.weight);
					}
				}
				queue.addAll(visitSet);
			}
			sb.append(maxWeight[endIdx]).append(" tons\n");
		}
		System.out.print(sb.toString());
	}
}