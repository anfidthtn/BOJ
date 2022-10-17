import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static int H;
	static int[] depths;
	static int[][] pTable;
	static List<Set<Integer>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		depths = new int[N + 1];
		graph = new ArrayList<>();
		graph.add(null);
		for(int i = 1; i <= N; i++) {
			graph.add(new TreeSet<>());
		}
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		H = 0;
		int num = N;
		while(num > 0) {
			num >>= 1;
			H++;
		}
		pTable = new int[N + 1][H + 1];
		setParents(0, 1, 1);
		for(int h = 1; h <= H; h++) {
			for(int i = 1; i <= N; i++) {
				pTable[i][h] = pTable[pTable[i][h - 1]][h - 1];
			}
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void setParents(int prev, int now, int depth) {
		depths[now] = depth;
		pTable[now][0] = prev;
		for(int next : graph.get(now)) {
			if (depths[next] != 0) {
				continue;
			}
			setParents(now, next, depth + 1);
		}
	}
	public static int lca(int x, int y) {
		if (depths[x] > depths[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		if (depths[x] < depths[y]) {
			int diff = depths[y] - depths[x];
			for(int i = 0; diff > 0; i++, diff >>= 1) {
				if ((diff & 1) == 1) {
					y = pTable[y][i];
				}
			}
		}
		if (x == y) {
			return x;
		}
		for(int i = H; i >= 0; i--) {
			if (pTable[x][i] != pTable[y][i]) {
				x = pTable[x][i];
				y = pTable[y][i];
			}
		}
		return pTable[x][0];
	}
}