import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 사람 수
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 시작점
		int start = Integer.parseInt(st.nextToken());
		// 끝점
		int end = Integer.parseInt(st.nextToken());
		// 관계 수
		int M = Integer.parseInt(br.readLine());
		// 촌수 관계 입력받을 그래프
		List<List<Integer>> graph = new ArrayList<>();
		// 그래프 초기화
		graph.add(null);
		for(int i = 1; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		// 관계 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// 현재 탐색중인 사람의 촌수
		int len = 0;
		// 방문여부체크
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		while(!queue.isEmpty()) {
			// 현재 큐의 노드 개수 저장
			int queuesize = queue.size();
			// 촌수 올리기
			len++;
			// 노드 개수만큼 돌림
			for(int i = 0; i < queuesize; i++) {
				int now = queue.poll();
				// 현재 사람과 1촌관계인 사람 다 봄
				for(int next : graph.get(now)) {
					// 방문한 거면 넘어감
					if (visited[next])
						continue;
					// 아니면 방문체크
					visited[next] = true;
					// 다음 사람이 목표인물이면 스톱하고
					if (next == end) {
						// 촌수 출력
						System.out.println(len);
						return;
					}
					queue.add(next);
				}
			}
		}
		// 다 돌고도 못 찾으면 -1
		System.out.println(-1);
	}
}