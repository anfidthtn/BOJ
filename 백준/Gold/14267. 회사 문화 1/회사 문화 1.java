import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int value;
		List<Integer> childs;

		public Node() {
			childs = new ArrayList<>();
		}
	}

	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node();
		}
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i <= N; i++) {
			nodes[Integer.parseInt(st.nextToken())].childs.add(i);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken())].value += Integer.parseInt(st.nextToken());
		}
		dfs(1, 0);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(nodes[i].value).append(" ");
		}
		System.out.print(sb.toString());
	}

	public static void dfs(int nowNum, int parentsValue) {
		nodes[nowNum].value += parentsValue;
		for (int next : nodes[nowNum].childs) {
			dfs(next, nodes[nowNum].value);
		}
	}
}