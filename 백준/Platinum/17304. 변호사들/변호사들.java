import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Integer>[] graph;
	static List<Integer>[] newGraph;
	static boolean[] mark;

	static int[] visitNums;
	static boolean[] markingVisit;
	static boolean[] checkingVisit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		if (N > M) {
			System.out.println("NO");
			return;
		}

		graph = new HashSet[N + 1];
		newGraph = new ArrayList[N + 1];

		mark = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new HashSet<>();
			newGraph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (graph[b].contains(a)) {
				graph[b].remove(a);
				newGraph[a].add(b);
				newGraph[b].add(a);
			} else {
				graph[a].add(b);
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int link : graph[i]) {
				mark[link] = true;
			}
		}

		visitNums = new int[N + 1];
		markingVisit = new boolean[N + 1];
		checkingVisit = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			if (!mark[i]) {
				visitNums[i] = 1;
				if (searchDFS(i, 1) || checkingDFS(i)) {
					markingDFS(i);
				}
				else {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
	}
	
	public static boolean checkingDFS(int now) {
		checkingVisit[now] = true;
		if (mark[now]) {
			return true;
		}
		for(int next : newGraph[now]) {
			if (checkingVisit[next]) {
				continue;
			}
			if(checkingDFS(next)) {
				return true;
			}
		}
		return false;
	}
	
	public static void markingDFS(int now) {
		markingVisit[now] = true;
		mark[now] = true;
		for(int next : newGraph[now]) {
			if (markingVisit[next]) {
				continue;
			}
			markingDFS(next);
		}
	}

	public static boolean searchDFS(int now, int depth) {
		for (int next : newGraph[now]) {
			if (visitNums[next] == 0) {
				visitNums[next] = depth + 1;
				if (searchDFS(next, depth + 1)) {
					return true;
				}
			} else if (visitNums[now] - visitNums[next] >= 2) {
				return true;
			}
		}
		return false;
	}
}