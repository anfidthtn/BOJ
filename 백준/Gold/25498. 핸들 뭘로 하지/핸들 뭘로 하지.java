import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static String str;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		graph = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		visited = new boolean[N];
		visited[0] = true;
		// 아 귀찮은데 메인에다 다 때려박자
		sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		// 큐 빌 때까지 돌린다.
		while (!queue.isEmpty()) {
			// 큐에 있는 녀석을 정답으로 넣음.
			sb.append(str.charAt(queue.peek()));
			char nowMax = 'a' - 1;
			// nowMin과 같은 알파벳을 가진 녀석들을 다음에 탐색하기 위해 queue를 따로 만든다.
			Queue<Integer> maxIdxQueue = new LinkedList<>();
			// 큐에 새로 추가될 일은 없어서 qSize 측정 안 하고 그냥 빌 때까지 돌려버림.
			while (!queue.isEmpty()) {
				int now = queue.poll();
				// 연결된 원소만 본다.
				for (int next : graph.get(now)) {
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					// 최고값이 갱신될 때, maxIdxQueue를 새로 만든다.
					if (str.charAt(next) > nowMax) {
						nowMax = str.charAt(next);
						maxIdxQueue = new LinkedList<>();
					}
					// 최고값 == 현재 값이면 현재 인덱스를 기록한다.
					if (str.charAt(next) == nowMax) {
						maxIdxQueue.add(next);
					}
				}
			}
			queue = maxIdxQueue;
		}

		System.out.print(sb);
	}
}