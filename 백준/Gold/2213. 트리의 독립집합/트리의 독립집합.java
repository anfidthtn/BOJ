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
	static int[] values;
	static List<List<Integer>> firstGraph;
	static int[] parents;
	static List<List<Integer>> childs;
	static int[] childCounts;
	static int[] noSelectSum;
	static int[] selectSum;
	
	/**
	 * noSelectSum을 trace해야할 경우, 
	 * 해당 noSelectSum이 noSelectSum으로 온건지 selectSum으로 온건지 구분
	 */
	static boolean[] bestNoSelect;
	static List<Integer> answers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		firstGraph = new ArrayList<>(N + 1);
		firstGraph.add(null);
		parents = new int[N + 1];
		childs = new ArrayList<>(N + 1);
		childs.add(null);
		values = new int[N + 1];
		childCounts = new int[N + 1];
		noSelectSum = new int[N + 1];
		selectSum = new int[N + 1];
		bestNoSelect = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			firstGraph.add(new LinkedList<>());
			childs.add(new LinkedList<>());
			selectSum[i] = values[i];
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			firstGraph.get(a).add(b);
			firstGraph.get(b).add(a);
		}

		boolean[] visited = new boolean[N + 1];
		makeTree(visited, 1);

		
		answers = new ArrayList<>();
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (childCounts[i] == 0) {
				queue.add(i);
			}
		}
		for (int i = 1; i < N; i++) {
			int now = queue.poll();
			int parent = parents[now];
			selectSum[parent] += noSelectSum[now];
			if (selectSum[now] >= noSelectSum[now]) {
				noSelectSum[parent] += selectSum[now];
				bestNoSelect[now] = false;
			}
			else {
				noSelectSum[parent] += noSelectSum[now];
				bestNoSelect[now] = true;				
			}
			if (--childCounts[parent] == 0) {
				queue.add(parent);
			}
		}
		if (selectSum[1] >= noSelectSum[1]) {
			System.out.println(selectSum[1]);
			traceSelectSum(1);
		}
		else {			
			System.out.println(noSelectSum[1]);
			traceNoSelectSum(1);
		}
		answers.sort(Integer::compareTo);
		StringBuilder sb = new StringBuilder();
		for(int answer : answers) {
			sb.append(answer).append(" ");
		}
		System.out.print(sb.toString());
	}
	public static void traceSelectSum(int now) {
		answers.add(now);
		for(int child : childs.get(now)) {
			traceNoSelectSum(child);
		}
	}
	public static void traceNoSelectSum(int now) {
		for(int child : childs.get(now)) {
			if (bestNoSelect[child]) {
				traceNoSelectSum(child);
			}
			else {
				traceSelectSum(child);
			}
		}
	}

	public static void makeTree(boolean[] visited, int now) {
		visited[now] = true;
		for (int next : firstGraph.get(now)) {
			if (visited[next]) {
				continue;
			}
			parents[next] = now;
			childs.get(now).add(next);
			childCounts[now]++;
			makeTree(visited, next);
		}
	}
}