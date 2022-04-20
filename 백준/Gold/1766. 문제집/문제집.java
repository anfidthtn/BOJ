import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		Set<Integer> parent;
		Set<Integer> child;

		public Node() {
			this.parent = new HashSet<>();
			this.child = new HashSet<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Node[] problem = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			problem[i] = new Node();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			problem[a].child.add(b);
			problem[b].parent.add(a);
		}
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (problem[i].parent.isEmpty())
				pq.add(i);
		}
		while (!pq.isEmpty()) {
			Integer now = pq.poll();
			sb.append(now).append(' ');
			for (int child : problem[now].child) {
				problem[child].parent.remove(now);
				if (problem[child].parent.isEmpty())
					pq.add(child);
			}
		}
		System.out.print(sb.toString());
	}
}