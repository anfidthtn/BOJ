import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] par;
	static int[][] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		par = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			par[i] = i;
		}
		edges = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) };
		}
		st = new StringTokenizer(br.readLine());
		int targetA = Integer.parseInt(st.nextToken());
		int targetB = Integer.parseInt(st.nextToken());
		Arrays.sort(edges, (a, b) -> b[2] - a[2]);
		for (int i = 0; i < M; i++) {
			union(edges[i][0], edges[i][1]);
			if (find(targetA) == find(targetB)) {
				System.out.println(edges[i][2]);
				return;
			}
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		par[b] = par[a];
	}

	public static int find(int x) {
		if (par[x] == x) {
			return x;
		} else {
			return par[x] = find(par[x]);
		}
	}
}