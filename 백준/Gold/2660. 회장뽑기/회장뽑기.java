import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<Integer>[] graph = new HashSet[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new HashSet<>();
		}
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == -1) {
				break;
			}
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		int[] score = new int[N + 1];
		int maxScore = 100_000_000;
		int maxCount = 0;
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			visited[i] = true;
			Queue<Integer> queue = new LinkedList<>();
			queue.add(i);
			while(!queue.isEmpty()) {
				score[i]++;
				int qSize = queue.size();
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
			score[i]--;
			if (score[i] < maxScore) {
				maxScore = score[i];
				maxCount = 1;
			}
			else if (score[i] == maxScore) {
				maxCount++;
			}
		}
		System.out.println(maxScore + " " + maxCount);
		for(int i = 1; i <= N; i++) {
			if (score[i] == maxScore) {
				System.out.print(i + " ");
			}
		}
	}
}