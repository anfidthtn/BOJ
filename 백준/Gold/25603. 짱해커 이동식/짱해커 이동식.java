import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static Node[] nums;
	static int N;
	static int K;
	static class Node{
		int num;
		int idx;
		boolean status;
		public Node(int num, int idx) {
			this.num = num;
			this.idx = idx;
			this.status = true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new Node[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = new Node(Integer.parseInt(st.nextToken()), i);
		}
		nums[N] = new Node(Integer.MAX_VALUE, N);
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.num == o2.num) {
					return o2.idx - o1.idx;
				}
				return o1.num - o2.num;
			}
		});
		int answer = 0;
		int pqSize = 0;
		int left = 0;
		int right = -1;
		while (left < N && right < N) {
			if (pqSize < K) {
				pq.add(nums[++right]);
				pqSize++;
			}
			else if (pqSize > K) {
				while(!pq.isEmpty() && !pq.peek().status) {
					pq.poll();
				}
				pq.poll();
				pqSize--;
				left++;
			}
			else {				
				while(!pq.isEmpty() && !pq.peek().status) {
					pq.poll();
				}
				answer = Math.max(answer, pq.peek().num);
				int nextLeft = pq.poll().idx;
				for(int i = left; i <= nextLeft; i++) {
					nums[i].status = false;
					pqSize--;
				}
				left = nextLeft + 1;
			}
		}
		System.out.println(answer);
	}
}