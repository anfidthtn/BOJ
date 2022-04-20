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
	static class Singer {
		int num;
		Set<Integer> before;
		Set<Integer> after;

		public Singer(int num) {
			super();
			this.num = num;
			this.before = new HashSet<>();
			this.after = new HashSet<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Singer[] singers = new Singer[N + 1];
		for (int i = 1; i <= N; i++) {
			singers[i] = new Singer(i);
		}
		for (int i = 0; i < M; i++) {
			int[] order = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 1; j < order[0]; j++) {
				singers[order[j]].after.add(order[j + 1]);
				singers[order[j + 1]].before.add(order[j]);
			}
		}
		int count = N;
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (singers[i].before.size() == 0) {
				queue.add(i);
				count--;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append('\n');
			for (int child : singers[now].after) {
				singers[child].before.remove((Integer) now);
				if (singers[child].before.size() == 0) {
					queue.add(child);
					count--;
				}
			}
		}
		if(count > 0) {
			System.out.print(0);
			return;
		}
		System.out.print(sb.toString());
	}
}