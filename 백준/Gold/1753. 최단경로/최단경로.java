import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to;
		int weight;
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(weight, o.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		// 입력받을 인접 리스트를 만든다.
		@SuppressWarnings("unchecked")
		List<Node>[] adjList = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 인접 리스트에 정보를 입력받는다.
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			adjList[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// 우선순위 큐로 노드의 가중치가 적은 순서대로 동작하도록 한다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 거리 최대치로 초기화
		int[] distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		// 미탐색으로 초기화
		boolean[] isVisited = new boolean[V + 1];
		// start -> start 의 가중치 0인 간선을 시작으로 하도록 한다.
		pq.add(new Node(start, 0));
		// pq가 비지 않아도 끝나게 하기위해 거리가 정해진 개수를 세어준다.
		// 근데 이거 넣든 안넣든 시간차이 크게 없는거같긴 하고, 오히려 더 걸리는 경우가 있는거 같아서 다시 주석처리.
//		int setCount = 1;
		distance[start] = 0;
		
		// 간선이 남아있으면 계속 탐색한다.
		while(!pq.isEmpty()) {
			Node nowNode = pq.poll();
			int to = nowNode.to;
			// 이미 최단 거리가 나온 후 간선을 본 목적지는 넘어간다.
			if(isVisited[to]) continue;
			// 손님 QR체크 하고 가실게요~
			isVisited[to] = true;
//			setCount++;
			
			// 해당 목적지는 최단거리가 나온 상태이며, 여기에서 이어지는 간선을 보지는 않았다.
			for(Node node : adjList[to]) {
				// 이어지는 간선의 목적지가 최단거리가 나온 목적지면 안 봐도 되긴 하는데, 보더라도 시간상의 큰 차이는 없어서 따로 컨티뉴 처리는 하지 않았다.
				if(distance[node.to] > distance[to] + node.weight) {
					// start -> 간선도착지점 현재까지 최단거리 > start -> 현재지점 + 현재지점 -> 간선도착지점 이면 갱신
					distance[node.to] = distance[to] + node.weight;
					// 갱신이 됐으면 start -> 간선도착지점의 거리를 최단거리로 해서 pq에 넣어준다.
					pq.add(new Node(node.to, distance[node.to]));
				}
			}
//			if (setCount == V) break;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) sb.append("INF");
			else sb.append(distance[i]);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}