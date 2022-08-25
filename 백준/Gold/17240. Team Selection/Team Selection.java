import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Player{
		int[] ability;
		boolean status = false;
		public Player(String abilityString) {
			this.ability = new int[5];
			StringTokenizer st = new StringTokenizer(abilityString);
			for(int i = 0; i < 5; i++) {
				this.ability[i] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static int[] selected;
	static boolean[] visited;
	static Player[][] candidates;
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		selected = new int[5];
		visited = new boolean[5];
		candidates = new Player[5][];
		for(int i = 0; i < 5; i++) {
			candidates[i] = new Player[N];
		}
		for(int i = 0; i < N; i++) {
			Player temp = new Player(br.readLine());
			for(int j = 0; j < 5; j++) {
				candidates[j][i] = temp;
			}
		}
		for(int i = 0; i < 5; i++) {
			final int idx = i;
			Arrays.sort(candidates[i], (a, b) -> b.ability[idx] - a.ability[idx]);
		}
		recur(0);
		System.out.println(max);
	}
	public static void recur(int idx) {
		if (idx == 5) {
			int[] idxs = new int[5];
			int sum = 0;
			for(int i = 0; i < 5; i++) {
				while(candidates[selected[i]][idxs[selected[i]]].status) {
					idxs[selected[i]]++;
				}
				sum += candidates[selected[i]][idxs[selected[i]]].ability[selected[i]];
				candidates[selected[i]][idxs[selected[i]]].status = true;
			}
			max = Math.max(max, sum);
			for(int i = 0; i < 5; i++) {
				candidates[selected[i]][idxs[selected[i]]].status = false;
			}
			return;
		}
		for(int i = 0; i < 5; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			selected[idx] = i;
			recur(idx + 1);
			visited[i] = false;
		}
	}
}