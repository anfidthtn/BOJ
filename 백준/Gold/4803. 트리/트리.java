import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tNum = 1;; tNum++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0) break;
			sb.append("Case ").append(tNum).append(": ");
			
			// 간선정보 입력할 배열 초기화
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] adj = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
			
			// 간선 정보 입력받아 저장
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a].add(b);
				adj[b].add(a);
			}
			
			// 트리 개수 셀 녀석
			int treeCount = 0;
			int[] visited = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				// 기존에 방문한 노드는 넘어간다.
				if (visited[i] != 0) continue;
				// 새로운 미방문노드가 발견되면 그것을 root로 하는 tree형으로 볼 수 있는지 본다.
				boolean isTree = true;
				// 1번부터 n번까지 미방문노드에 대해서 bfs든 dfs든 탐색을 할 예정
				Queue<Integer> bfs = new LinkedList<>();
				bfs.offer(i);
				// root는 -1로 한다.
				visited[i] = -1;
				while (!bfs.isEmpty()) {
					int node = bfs.poll();
					// 해당 노드의 연결노드를 본다.
					for(int nextNode : adj[node]) {
						// 만약 미방문 노드이면 방문정보로 해당 노드를 등록하여, 해당 노드가 이번 탐색의 부모노드 역할을 한 것이라고 저장한다.
						if (visited[nextNode] == 0) {
							visited[nextNode] = node;
							bfs.offer(nextNode);
						}
						// 만약 아직 사이클이 발견되지 않아서 트리라고 간주했는데
						// 연결한 방문한 노드이면서 지금의 노드의 부모노드가 아니면 사이클이 발생한다는 소리이므로 트리가 아니라고 한다. 
						else if (isTree && nextNode != visited[node]) {
							isTree = false;
						}
					}
				}
				if (isTree) treeCount++;
			}
			if (treeCount == 0) {
				sb.append("No trees.");
			}
			else if (treeCount == 1) {
				sb.append("There is one tree.");
			}
			else {
				sb.append("A forest of ").append(treeCount).append(" trees.");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}