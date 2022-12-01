import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] portals;
	static int[] par;
	static int groups;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		portals = new int[N][5];
		par = new int[2 * N + 1];
		for (int i = 1; i <= 2 * N; i++) {
			par[i] = i;
		}
		groups = 2 * N;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				portals[i][j] = Integer.parseInt(st.nextToken());
			}
			union(portals[i][1], portals[i][2]);
			union(portals[i][3], portals[i][4]);
		}
		Arrays.sort(portals, (a, b) -> a[0] - b[0]);
		int answer = 0;
		for (int i = 0; i < N && groups > 1; i++) {
			if (union(portals[i][1], portals[i][4])) {
				answer += portals[i][0];
			}
		}
		System.out.println(answer);
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			par[b] = a;
			groups--;
			return true;
		}
		return false;
	}

	public static int find(int x) {
		if (par[x] == x) {
			return x;
		} else {
			return par[x] = find(par[x]);
		}
	}
}