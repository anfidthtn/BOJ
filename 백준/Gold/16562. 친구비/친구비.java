import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static int K;
	static Set<Integer>[] graph;
	static int[] costs;
	static int groupNum;
	static List<Set<Integer>> group;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new TreeSet[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new TreeSet<>();
		}
		costs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		visited = new boolean[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
			graph[b].add(a);
		}
		group = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			group.add(new TreeSet<>());
			dfs(i);
			groupNum++;
		}
		long answer = 0;
		for (int i = 0; i < group.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int num : group.get(i)) {
				min = Math.min(costs[num], min);
			}
			answer += min;
		}
		if (answer > K) {
			System.out.println("Oh no");
		}
		else {
			System.out.println(answer);
		}
	}

	public static void dfs(int now) {
		if (visited[now]) {
			return;
		}
		visited[now] = true;
		group.get(groupNum).add(now);
		for (int next : graph[now]) {
			dfs(next);
		}
	}
}