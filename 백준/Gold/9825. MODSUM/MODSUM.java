import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] infos;
	static int answer = 0;
	static final int[] four = {0, 1, 1, 1, 1};
	static final int[] two = {0, 1, 4, 4, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		infos = new int[N][2]; 
		for(int i = 0; i < N; i++) {
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(answer);
	}
	public static void dfs(int idx, int sum) {
		if (idx == N) {
			answer += 1 + (four[sum] + 2 * two[sum]) % 5;
			return;
		}
		for(int i = infos[idx][0]; i <= infos[idx][1]; i++) {
			dfs(idx + 1, (sum + i) % 5);
		}
	}
}