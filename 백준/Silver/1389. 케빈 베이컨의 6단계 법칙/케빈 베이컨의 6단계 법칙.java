import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static Set<Integer>[] graph;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new TreeSet[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new TreeSet<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		int answer = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			Queue<Integer> queue = new LinkedList<>();
			queue.add(i);
			visited[i] = true;
			int tempAnswer = 0;
			int value = 0;
			while(!queue.isEmpty() && tempAnswer < min) {
				int qSize = queue.size();
				tempAnswer += qSize * value;
				value++;
				for(int q = 0; q < qSize; q++) {
					int now = queue.poll();
					for(int next : graph[now]) {
						if (visited[next]) {
							continue;
						}
						visited[next] = true;
						queue.add(next);
					}
				}
			}
			if (tempAnswer < min) {
				min = tempAnswer;
				answer = i;
			}
		}
		System.out.println(answer);
	}
}