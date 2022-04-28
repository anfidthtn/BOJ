import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int date;
		int score;
		public Node(int date, int score) {
			super();
			this.date = date;
			this.score = score;
		}
		@Override
		public int compareTo(Node o) {
			if (score - o.score == 0) {
				return o.date - date;
			}
			return o.score - score;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Node>[] list = new ArrayList[1001];
		for(int i = 1; i <= 1000; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int date = Integer.parseInt(st.nextToken());
			list[date].add(new Node(date, Integer.parseInt(st.nextToken())));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int score = 0;
		for(int date = 1000; date >= 1; date--) {
			pq.addAll(list[date]);
			if(!pq.isEmpty()) {
				score+=pq.poll().score;
			}
		}
		System.out.println(score);
	}
}