import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] nums;
	static int[] slots;
	static boolean[] visited;
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		slots = new int[N];
		visited = new boolean[N];
		check(0);
		System.out.println(max);
	}
	public static void check(int idx) {
		if (idx == N) {
			int sum = 0;
			for(int i = 0; i < N - 1; i++) {
				sum += Math.abs(slots[i] - slots[i + 1]);
			}
			max = Math.max(max, sum);
			return;
		}
		for(int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			slots[idx] = nums[i];
			check(idx + 1);
			visited[i] = false;
		}
	}
}