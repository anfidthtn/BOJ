import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int H;
	static int[][] parents;
	static int[] depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			// 그냥 구현하면 더 쉽겠지만 알고리즘 연습
			N = Integer.parseInt(br.readLine());
			H = 0;
			int num = N;
			while(num > 0) {
				H++;
				num >>= 1;
			}
			parents = new int[N + 1][H];
			depth = new int[N + 1];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				parents[Integer.parseInt(st.nextToken())][0] = p;
			}
			for(int i = 1; i <= N; i++) {
				if (depth[i] == 0) {
					setDepths(i);
				}
			}
			for(int h = 1; h < H; h++) {
				for(int i = 1; i <= N; i++) {
					parents[i][h] = parents[parents[i][h - 1]][h - 1];
				}
			}
			st = new StringTokenizer(br.readLine());
			sb.append(lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int lca(int x, int y) {
		if (depth[x] < depth[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		int diff = depth[x] - depth[y];
		for(int h = 0; h < H && diff > 0; h++, diff >>= 1) {
			if ((diff & 1) == 1) {
				x = parents[x][h];
			}
		}
		if (x == y) {
			return x;
		}
		for(int h = H - 1; h >= 0; h--) {
			if (parents[x][h] != parents[y][h]) {
				x = parents[x][h];
				y = parents[y][h];
			}
		}
		return parents[x][0];
	}
	public static int setDepths(int now) {
		if (now == 0) {
			return 0;
		}
		if (depth[now] > 0) {
			return depth[now];
		}
		return depth[now] = 1 + setDepths(parents[now][0]);
	}
}