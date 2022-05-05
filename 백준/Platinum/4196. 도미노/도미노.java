import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static List<Integer>[] graph;
	static int[] visit;
	static int[] sccIdx;
	static int visitNum;
	static int sccGroupNum;
	static Stack<Integer> stack;
	static boolean[] sccGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// N, M 받기
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 그래프, 방문여부 체크 배열, sccIdx 체크 배열 만들기
			graph = new ArrayList[N + 1];
			visit = new int[N + 1];
			sccIdx = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				visit[i] = -1;
				sccIdx[i] = -1;
			}

			// 그래프 입력 받기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}

			// 방문을 몇 번째로 했는지 카운팅할 변수
			visitNum = 0;
			// scc 그래프가 몇 개 나왔는지 카운팅할 변수
			sccGroupNum = 0;

			// 방문 정보를 기록할 스택
			stack = new Stack<>();

			for (int i = 1; i <= N; i++) {
				if (visit[i] == -1) {
					scc(i);
				}
			}
			
			
			// 계속 부모 부모 하니까 코드한테 패드립 박는 것 같네..
			// 부모가 있는지 여부를 체크할 배열 (원래는 그래프로 재구성해야하는데 부모 없는 녀석 개수만 세면 되기 때문)
			sccGraph = new boolean[sccGroupNum];
			for (int start = 1; start <= N; start++) {
				for (int end : graph[start]) {
					if (sccIdx[start] == sccIdx[end]) {
						continue;
					}
					sccGraph[sccIdx[end]] = true;
				}
			}
			int count = 0;
			for(boolean existParent : sccGraph) {
				// 부모가 없..는?... 노드 개수 캐운트
				if (!existParent) {
					count++;
				}
			}

			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int scc(int now) {
		// 몇 번째로 방문했는지 저장
		visit[now] = visitNum++;
		// 방문 정보를 스택에 푸시
		stack.push(now);

		// scc 구성요소 중 최소값을 반환하기 위해 현재 노드부터 일단 저장
		int result = visit[now];
		// 자신과 연결된 녀석을 dfs
		for (int next : graph[now]) {
			if (visit[next] == -1) {
				// 다음 지점을 방문하지 않은 경우 다음 지점을 방문해 방문 정보를 알아내고 최솟값 갱신을 한다.
				result = Math.min(result, scc(next));
			} else if (sccIdx[next] == -1) {
				// 방문은 했는데, 다음 지점이 scc에 묶여있지 않은 경우 방문 정보로 최솟값 갱신을 한다.
				result = Math.min(result, visit[next]);
			}
		}

		if (result == visit[now]) {
			// stack에서 자기 자신이 나올 때까지 pop하면서 scc그룹화를 한다.
			while (true) {
				int top = stack.pop();
				sccIdx[top] = sccGroupNum;
				if (top == now) {
					break;
				}
			}
			sccGroupNum++;
		}
		return result;
	}
}