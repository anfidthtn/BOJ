import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int dest;
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public boolean equals(Object obj) {
			// 사용되는 부분에서는 중복이 없음이 보장되어서 목적지만 같아도 같은 간선으로 볼 수 있다.
			if (((Edge)obj).dest == dest) {
				return true;
			}
			return false;
		}
		@Override
		public int hashCode() {
			// (비교를 해야할 때) 중복이 없음이 보장되어서 목적지만 같으면 된다.
			return dest;
		}
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			// 간선을 받을 곳
			@SuppressWarnings("unchecked")
			Set<Edge>[] adj = new HashSet[N];
			for(int i = 0; i < N; i++) {
				adj[i] = new HashSet<>();
			}
			
			// 간선 입력을 받는다.
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				adj[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			
			// 최단거리로 세팅된 순간의 출발위치를 저장해서 나중에 최단 거리 간선을 제거할 때 사용한다.
			@SuppressWarnings("unchecked")
			List<Integer>[] minLenStartIdx = new ArrayList[N];
			minLenStartIdx[S] = new ArrayList<>();
			minLenStartIdx[D] = new ArrayList<>();
			
			// 최단거리가 측정된 노드
			boolean[] isMinLen = new boolean[N];
			int[] minDistance = new int[N];
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			// S번 노드부터 시작
			pq.add(new Edge(S, 0));
			minDistance[S] = 0;
			while(!pq.isEmpty()) {
				// 여기에서 나오는 녀석은 원점에서부터의 (간선이 더해질 떄 구해진) 최단거리이다.
				Edge now = pq.poll();
				// 최단거리가 이미 정해진 도착지면 넘어간다.
				if (isMinLen[now.dest]) continue;
				// 여기 들어왔다는 것은 S로부터 최단거리가 땅땅땅 결정된다는 것이다.
				isMinLen[now.dest] = true;
				// 목적지까지 최단 거리가 구해졌다면 더 볼 필요가 없다.
				if (isMinLen[D]) break;
				
				// 현재 간선의 도착지로부터 최단거리 계산에 들어간다. 
				for(Edge next : adj[now.dest]) {
					// 현재 지점을 경유해서 next로 갈 때의 거리
					int nowVisitDistance = minDistance[now.dest] + next.weight;
					// 더 많이 걸리면 그냥 무시
					if (nowVisitDistance > minDistance[next.dest]) continue;
					if (nowVisitDistance < minDistance[next.dest]) {
						// 더 적게 걸리면 출발지점을 새로 만들고, 우선순위 큐에 S로부터의 도착지점까지 최단거리를 넣는다.
						minLenStartIdx[next.dest] = new ArrayList<>();
						minDistance[next.dest] = nowVisitDistance;
						pq.add(new Edge(next.dest, nowVisitDistance));
					}
					// 더 적게 걸리거나 같은 경우, 
					minLenStartIdx[next.dest].add(now.dest);
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				for(Edge e : adj[i]) {
//					System.out.print(e.dest + " ");
//				}
//				System.out.println();
//			}
			
			// 목적지부터 시작해서 최단거리에 포함되는 간선 삭제를 시작한다.
			deleteEdge(adj, minLenStartIdx, new boolean[N], D);
			
//			for(int i = 0; i < N; i++) {
//				for(Edge e : adj[i]) {
//					System.out.print(e.dest + " ");
//				}
//				System.out.println();
//			}

			// 재사용 가능한 변수 재사용하며 GC에 메모리 할당 해제를 맡겨보자.
			isMinLen = new boolean[N];
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			pq = new PriorityQueue<>();
			// S번 노드부터 시작
			pq.add(new Edge(S, 0));
			minDistance[S] = 0;
			while(!pq.isEmpty()) {
				// 여기에서 나오는 녀석은 원점에서부터의 (간선이 더해질 떄 구해진) 최단거리이다.
				Edge now = pq.poll();
				// 최단거리가 이미 정해진 도착지면 넘어간다.
				if (isMinLen[now.dest]) continue;
				// 여기 들어왔다는 것은 S로부터 최단거리가 땅땅땅 결정된다는 것이다.
				isMinLen[now.dest] = true;
				// 목적지까지 최단 거리가 구해졌다면 더 볼 필요가 없다.
				if (isMinLen[D]) break;
				for(Edge next : adj[now.dest]) {
					// 현재 지점을 경유해서 next로 갈 때의 거리
					int nowVisitDistance = minDistance[now.dest] + next.weight;
					// 더 많이 걸리면 그냥 무시
					if (nowVisitDistance < minDistance[next.dest]) {
						// 더 적게 걸리면 우선순위 큐에 S로부터의 도착지점까지 최단거리를 넣는다.
						minDistance[next.dest] = nowVisitDistance;
						pq.add(new Edge(next.dest, nowVisitDistance));
					}
				}
			}
			if (minDistance[D] == Integer.MAX_VALUE) sb.append(-1);
			else sb.append(minDistance[D]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void deleteEdge(Set<Edge>[] adj, List<Integer>[] sIdx, boolean[] isDeleted, int now) {
		if (isDeleted[now]) return;
		isDeleted[now] = true;
		for (int start : sIdx[now]) {
			adj[start].remove(new Edge(now, 0));
			deleteEdge(adj, sIdx, isDeleted, start);
		}
	}
}