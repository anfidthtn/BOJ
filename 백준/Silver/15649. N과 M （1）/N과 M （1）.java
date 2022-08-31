import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int N;
	static int M;
	static int[] selected;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new int[M];
		visited = new boolean[N + 1];
		recur(0);
		System.out.print(sb.toString());
	}

	public static void recur(int cur) {
		if (cur == M) {
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			selected[cur] = i;
			recur(cur + 1);
			visited[i] = false;
		}
	}
}