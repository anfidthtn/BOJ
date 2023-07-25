import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] perm = { //
			{ 0, 1, 2 }, //
			{ 1, 0, 2 }, //
			{ 1, 2, 0 }, //
			{ 2, 1, 0 }, //
			{ 0, 2, 1 }, //
			{ 2, 0, 1 } //
	};

	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] hp = new int[3];
		visited = new int[1 << 18];
		Arrays.fill(visited, 1000000);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			hp[i] = Integer.parseInt(st.nextToken());
		}
		int now = (hp[0] << 12) + (hp[1] << 6) + hp[2];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(now);
		visited[now] = 0;
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			for(int q = 0; q < qSize; q++) {
				now = queue.poll();
				if (now == 0) {
					System.out.println(visited[now]);
					return;
				}
				hp[0] = now >> 12;
				hp[1] = (now >> 6) & 63;
				hp[2] = now & 63;
				for(int[] per : perm) {
					hp[per[0]] -= 9;
					hp[per[1]] -= 3;
					hp[per[2]] -= 1;
					int next = (Math.max(0, hp[0]) << 12) + (Math.max(0, hp[1]) << 6) + (Math.max(0, hp[2]));
					if (visited[next] > visited[now] + 1) {
						visited[next] = visited[now] + 1;
						queue.add(next);
					}
					hp[per[0]] += 9;
					hp[per[1]] += 3;
					hp[per[2]] += 1;
				}
			}
		}
		
		System.out.println(visited[0]);
	}
}