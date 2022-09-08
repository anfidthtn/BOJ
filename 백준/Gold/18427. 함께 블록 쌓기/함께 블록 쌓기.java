import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		List<Integer>[] blocks = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			blocks[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			blocks[i].add(0);
			while (st.hasMoreTokens()) {
				blocks[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		int[][] ways = new int[N + 1][H + 1];
		ways[0][0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int block : blocks[i]) {
				for (int h = H - block; h >= 0; h--) {
					ways[i][h + block] += ways[i - 1][h];
					ways[i][h + block] %= 10007;
				}
			}
		}
		System.out.println(ways[N][H]);
	}
}