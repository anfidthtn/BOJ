import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int a;
		int b;
		int value;
		public Node(int a, int b, int value) {
			this.a = a;
			this.b = b;
			this.value = value;
		}
	}
	static List<List<Node>> graph;
	static int[] leafCount;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		visited = new boolean[200001];
		graph = new ArrayList<>(200001);
		graph.add(null);
		for(int i = 1; i <= 200000; i++) {
			graph.add(new LinkedList<>());
		}
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			for(int i = 1; i <= N; i++) {
				/**
				 * 새로 linkedlist 껍데기 주소 할당하지 말고 그냥 원소만 비우기
				 */
				graph.get(i).clear();
			}
			System.gc();
			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				/**
				 * 양쪽에 {a, height}, {b, height} 넣으면 두 개니까
				 * 그냥 하나 {a, b, height}만들어 넣어버리기
				 */
				Node temp = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				graph.get(temp.a).add(temp);
				graph.get(temp.b).add(temp);
			}
			leafCount = new int[1001];
			/**
			 * 새로 boolean 만들지 말고 원소만 비우기
			 */
			Arrays.fill(visited, false);
			visited[K] = true;
			dfs(K, 1001);
			
			int[] minerCount = new int[1001];
			int m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				minerCount[Integer.parseInt(st.nextToken())]++;
			}
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			for(int i = 1000; i >= 1; i--) {
				if (leafCount[i] > 0) {
					pq.add(i);
				}
			}
			int total = 0;
			int ableField = 0;
			for(int i = 1000; i >= 1; i--) {
				while (!pq.isEmpty() && pq.peek() >= i) {
					ableField += leafCount[pq.poll()];
				}
				int temp = Math.min(ableField, minerCount[i]);
				total += temp;
				ableField -= temp;
				minerCount[i] -= temp;
			}
			sb.append(total).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void dfs(int now, int minHeight) {
		if (minHeight <= 1000 && graph.get(now).size() == 1) {
			// 시작점이 아닌 연결관계가 1개인 곳 = 리프
			leafCount[minHeight]++;
			return;
		}
		for(Node nextNode : graph.get(now)) {
			int next = nextNode.a;
			if (nextNode.a == now) {
				next = nextNode.b;
			}
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			dfs(next, Math.min(minHeight, nextNode.value));
		}
	}
}