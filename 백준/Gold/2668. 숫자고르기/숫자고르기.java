import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] nums;
	static boolean[] visited;
	static boolean[] ok;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		ok = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (ok[i]) {
				continue;
			}
			visited = new boolean[N + 1];
			check(i);
		}
		count = 0;
		for (int i = 1; i <= N; i++) {
			if (ok[i]) {
				count++;
			}
		}
		System.out.println(count);
		for (int i = 1; i <= N; i++) {
			if (ok[i]) {
				System.out.println(i);
			}
		}

	}

	public static void check(int now) {
		if (visited[now]) {
			ok[now] = true;
			return;
		}
		visited[now] = true;
		check(nums[now]);
	}

}