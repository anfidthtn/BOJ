import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int num;
		int count;

		public Node(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.count == o2.count) {
					return Math.abs(o1.num - N) - Math.abs(o2.num - N);
				}
				return o1.count - o2.count;
			}
		});
		int[] minCount = new int[1000001];
		Arrays.fill(minCount, 1 << 30);
		minCount[100] = 0;
		pq.add(new Node(100, 0));
		boolean[] broken = new boolean[10];
		int M = Integer.parseInt(br.readLine());
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		if (!broken[0]) {
			minCount[0] = 1;
			pq.add(new Node(0, 1));
		}
		for(int i = 1; i <= 1000000; i++) {
			if (i == 100) {
				continue;
			}
			int num = i;
			int count = 0;
			boolean ok = true;
			while(num > 0) {
				if (broken[num % 10]) {
					ok = false;
					break;
				}
				num /= 10;
				count++;
			}
			if (ok) {
				minCount[i] = count;
				pq.add(new Node(i, count));
			}
		}
		while(true) {
			Node now = pq.poll();
			if (now.num == N) {
				System.out.println(now.count);
				return;
			}
			if (now.count > minCount[now.num]) {
				continue;
			}
			if (now.num > 0) {
				if (minCount[now.num - 1] > now.count + 1) {
					minCount[now.num - 1] = now.count + 1;
					pq.add(new Node(now.num - 1, now.count + 1));
				}
			}
			if (now.num < 1000000) {
				if (minCount[now.num + 1] > now.count + 1) {
					minCount[now.num + 1] = now.count + 1;
					pq.add(new Node(now.num + 1, now.count + 1));
				}				
			}
		}
	}
}