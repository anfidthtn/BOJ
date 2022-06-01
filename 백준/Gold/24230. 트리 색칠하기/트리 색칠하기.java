import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] colors = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		List<Integer>[] link = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			link[i] = new ArrayList<>();
		}
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			link[a].add(b);
			link[b].add(a);
		}
		int count = 0;
		if (colors[0] > 0) {
			count++;
		}
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		visited[0] = true;
		queue.add(0);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : link[now]) {
				if (visited[next]) {
					continue;
				}
				visited[next] = true;
				if (colors[now] != colors[next]) {
					count++;
				}
				queue.add(next);
			}
		}
		System.out.println(count);
	}
}