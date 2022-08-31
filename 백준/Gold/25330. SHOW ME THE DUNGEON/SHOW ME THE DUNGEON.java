import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int K;
	static int[] atts;
	static int[] ps;
	static boolean[] visited;

	
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		atts = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ps = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		answer = 0;
		visited = new boolean[N];
		check(0, 0);
		System.out.println(answer);
	}
	public static void check(int idx, int p) {
		if (idx == N) {
			if (answer < p) {
				if(ableCheck()) {
					answer = p;
				}
			}
			return;
		}
		visited[idx] = true;
		check(idx + 1, p + ps[idx]);
		visited[idx] = false;
		check(idx + 1, p);
	}
	public static boolean ableCheck() {
		int hp = K;
		List<Integer> ATTs = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			if (visited[i]) {
				ATTs.add(atts[i]);
			}
		}
		ATTs.sort(Integer::compareTo);
		int damage = 0;
		for(int att : ATTs) {
			damage += att;
			if ((hp -= damage) < 0) {
				return false;
			}
		}
		return true;
	}
}