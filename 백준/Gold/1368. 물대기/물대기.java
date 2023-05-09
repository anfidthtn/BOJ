import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int count;
	static int[] size;
	static int[] parent;
	static boolean[] water;
	static List<Element> list;
	static long ans;

	static class Element {
		int x, y, cost;

		public Element(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		count = 0;
		size = new int[N];
		parent = new int[N];
		water = new boolean[N];
		list = new ArrayList<>(N * N + N);
		ans = 0;
		for (int i = 0; i < N; i++) {
			size[i] = 1;
			parent[i] = i;
			list.add(new Element(-1, i, Integer.parseInt(br.readLine())));
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (j <= i) {
					continue;
				}
				list.add(new Element(i, j, cost));
			}
		}
		list.sort((a, b) -> (int) (a.cost - b.cost));
		for (int i = 0; i < list.size() && count < N; i++) {
			Element now = list.get(i);
			if (now.x == -1) {
				fill(now);
			} else {
				union(now);
			}
		}
		System.out.println(ans);
	}

	public static void fill(Element e) {
		fill(e.y, e.cost);
	}

	public static void union(Element e) {
		union(e.x, e.y, e.cost);
	}

	public static void fill(int x, int cost) {
		x = find(x);
		if (water[x]) {
			return;
		}
		ans += cost;
		water[x] = true;
		count += size[x];
	}

	public static void union(int x, int y, int cost) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}
		if (water[x] && water[y]) {
			return;
		}
		ans += cost;
		if (water[x]) {
			count += size[y];
		}
		if (water[y]) {
			count += size[x];
		}
		parent[y] = x;
		size[x] += size[y];
		water[x] |= water[y];
	}

	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}