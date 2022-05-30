import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] sizes = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Set<Integer> link = new HashSet<>();
		for (int i = 0; i < M; i++) {
			link.add(sizes[i]);
			for(int j = i + 1; j < M; j++) {
				if (sizes[i] + sizes[j] <= N) {
					link.add(sizes[i] + sizes[j]);
				}
			}
		}
		boolean[] visited = new boolean[N + 1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		int count = 0;
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			count++;
			for(int i = 0; i < qSize; i++) {
				int now = queue.poll();
				for(int next : link) {
					if (now + next > N) {
						continue;
					}
					if (visited[now + next]) {
						continue;
					}
					visited[now + next] = true;
					if (now + next == N) {
						System.out.print(count);
						return;
					}
					queue.add(now + next);
				}
			}
		}
		System.out.print(-1);
	}
}