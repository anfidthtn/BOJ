import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> primes;
	static List<char[]> prime_strings;
	static int[] idxx;
	static boolean[] isNotPrime;
	static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		makeList();
		makeGraph();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int res = bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(res >= 0 ? res : "impossible").append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int bfs(int now, int target) {
		boolean[] visited = new boolean[10000];
		visited[now] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(idxx[now]);
		int targetidx = idxx[target];
		int nowMove = 0;
		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				int nowidx = queue.poll();
				if (nowidx == targetidx) {
					return nowMove;
				}
				for(int nextidx : graph.get(nowidx)) {
					if (visited[nextidx]) {
						continue;
					}
					visited[nextidx] = true;
					queue.add(nextidx);
				}
			}
			nowMove++;
		}
		return -1;
	}

	public static void makeGraph() {
		graph = new ArrayList<>();
		for (int i = 0; i < primes.size(); i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < primes.size(); i++) {
			for (int j = i + 1; j < primes.size(); j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					if (prime_strings.get(i)[k] == prime_strings.get(j)[k]) {
						cnt++;
					}
				}
				if (cnt == 3) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}
	}

	public static void makeList() {
		idxx = new int[10000];
		isNotPrime = new boolean[10001];
		primes = new ArrayList<>();
		prime_strings = new ArrayList<>();
		for (int p = 2; p <= 10000; p++) {
			if (!isNotPrime[p]) {
				if (1000 <= p && p <= 9999) {
					idxx[p] = primes.size();
					primes.add(p);
					prime_strings.add(Integer.toString(p).toCharArray());
				}
				for (int np = p * p; np <= 10000; np += p) {
					isNotPrime[np] = true;
				}
			}
		}
	}
}