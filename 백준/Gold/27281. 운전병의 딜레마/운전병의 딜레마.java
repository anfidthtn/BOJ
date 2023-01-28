import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		/**
		 * 불편도, 시간, 목적지 정보가 담긴 간선
		 */
		long comp, time;
		int dest;

		public Edge(int dest, long time, long comp) {
			super();
			this.dest = dest;
			this.time = time;
			this.comp = comp;
		}
	}

	static class Node {
		/**
		 * 목적지, 시간을 담는 pq의 노드
		 */
		int dest;
		long time;

		public Node(int dest, long time) {
			super();
			this.dest = dest;
			this.time = time;
		}
	}

	static List<List<Edge>> graph;
	static int N, M, T;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		graph.add(null);
		for (int i = 1; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Edge(b, t, s));
			graph.get(b).add(new Edge(a, t, s));
		}
		/**
		 * 정답 범위 밖의 수를 초기값으로 두고 이분탐색 시작
		 */
		ans = 1 << 30;
		/**
		 * 불편도는 최대 0까지 줄일 수 있고, 최대 조건은 10억이니 이렇게 설정
		 */
		int left = 0, right = 1_000_000_000;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (check(mid)) {
				ans = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.print(ans == 1 << 30 ? -1 : ans);
	}

	public static boolean check(long limComp) {
		long[] visited = new long[N + 1];
		Arrays.fill(visited, 1 << 30);
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.time, b.time));
		pq.add(new Node(1, 0));
		visited[1] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			/**
			 * 뺀 녀석이 더 크면 패스
			 */
			if (visited[now.dest] < now.time) {
				continue;
			}
			for (Edge nextEdge : graph.get(now.dest)) {
				long nextTime = now.time + nextEdge.time;
				/**
				 * 간선 불편도가 불편도 제한보다 클 경우 그만큼 시간을 희생해야함.
				 */
				if (nextEdge.comp > limComp) {
					nextTime += nextEdge.comp - limComp;
				}
				/**
				 * 도달 시간이 제한보다 커지면 도달할 수 없음
				 */
				if (nextTime > T) {
					continue;
				}
				/**
				 * 목적지 == 최종목적지인 경우
				 * 최종목적지까지 T시간 안에 도달가능한 거니까
				 * true 반환
				 */
				if (nextEdge.dest == N) {
					return true;
				}
				/**
				 * 이미 더 빠르게 해당 위치에 도달가능한 경우는 더 느린 경우를 볼 필요 없으니 패스함
				 */
				if (visited[nextEdge.dest] <= nextTime) {
					continue;
				}
				/**
				 * 현 위치에 가장 빠르게 도달 가능한 시간을 기록
				 */
				visited[nextEdge.dest] = nextTime;
				/**
				 * 가장 빠르게 도달 가능한 시간을 큐에 넣기
				 */
				pq.add(new Node(nextEdge.dest, nextTime));
			}
		}
		return false;
	}
}