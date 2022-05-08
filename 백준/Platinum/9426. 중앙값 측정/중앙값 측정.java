import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int value;
		int position;
		public Node(int value) {
			super();
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> smaller = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.value - o1.value;
			}
		});
		PriorityQueue<Node> bigger = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
		Queue<Node> nodes = new LinkedList<>();
		long total = 0;
		while(nodes.size() < K - 1) {
			Node node = new Node(Integer.parseInt(br.readLine()));
			nodes.add(node);
			if (bigger.isEmpty() || node.value < bigger.peek().value) {				
				smaller.add(node);
				node.position = 1;
			}
			else {
				bigger.add(node);
				node.position = 2;
			}
			if (smaller.size() == bigger.size() + 2) {
				Node temp = smaller.poll();
				temp.position = 2;
				bigger.add(temp);
			}
			if (smaller.size() < bigger.size()) {
				Node temp = bigger.poll();
				temp.position = 1;
				smaller.add(temp);
			}
		}
		int smallerSize = smaller.size();
		int biggerSize = bigger.size();
		
		for(int i = K - 1; i < N; i++) {
			Node node = new Node(Integer.parseInt(br.readLine()));
			nodes.add(node);
			cleanPQ(smaller);
			cleanPQ(bigger);
			if (smallerSize == biggerSize + 2) {
				Node temp = smaller.poll();
				temp.position = 2;
				bigger.add(temp);
				smallerSize--;
				biggerSize++;
				cleanPQ(smaller);
				cleanPQ(bigger);
			}
			if (smallerSize < biggerSize) {
				Node temp = bigger.poll();
				temp.position = 1;
				smaller.add(temp);
				smallerSize++;
				biggerSize--;
				cleanPQ(smaller);
				cleanPQ(bigger);
			}
			if (bigger.isEmpty() || node.value < bigger.peek().value) {				
				smaller.add(node);
				node.position = 1;
				smallerSize++;
				cleanPQ(smaller);
			}
			else {
				bigger.add(node);
				node.position = 2;
				biggerSize++;
				cleanPQ(bigger);
			}
			
			if (smallerSize == biggerSize + 2) {
				Node temp = smaller.poll();
				temp.position = 2;
				bigger.add(temp);
				smallerSize--;
				biggerSize++;
				cleanPQ(smaller);
				cleanPQ(bigger);
			}
			if (smallerSize < biggerSize) {
				Node temp = bigger.poll();
				temp.position = 1;
				smaller.add(temp);
				smallerSize++;
				biggerSize--;
				cleanPQ(smaller);
				cleanPQ(bigger);
			}
			total += smaller.peek().value;
			Node temp = nodes.poll();
			if (temp.position == 1) {
				smallerSize--;
			}
			else {
				biggerSize--;
			}
			temp.position = -1;
		}
		
		System.out.println(total);
	}
	public static void cleanPQ(PriorityQueue<Node> pq) {
		while(!pq.isEmpty() && pq.peek().position == -1) {
			pq.poll();
		}
	}
}