import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static char[] opers;
	static int[] nums;
	static boolean[] visited;
	static long min;
	static long max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		opers = new char[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			opers[i] = st.nextToken().charAt(0);
		}
		nums = new int[N + 1];
		visited = new boolean[10];
		min = -1;
		max = -1;
		check(0);
		String formatString = "%0" + (N + 1) + "d\n";
		System.out.print(String.format(formatString, max));
		System.out.print(String.format(formatString, min));
	}
	public static void check(int idx) {
		if (idx == N + 1) {
			long num = numsTonum();
			if (min == -1) {
				min = num;
			}
			max = num;
			return;
		}
		for(int i = 0; i < 10; i++) {
			if (visited[i]) {
				continue;
			}
			if (idx > 0) {
				if (opers[idx - 1] == '<') {
					if (nums[idx - 1] > i) {
						continue;
					}
				}
				else {
					if (nums[idx - 1] < i) {
						continue;
					}
				}
			}
			visited[i] = true;
			nums[idx] = i;
			check(idx + 1);
			visited[i] = false;
		}
	}
	public static long numsTonum() {
		long num = 0;
		for(int i = 0; i <= N; i++) {
			num *= 10;
			num += nums[i];
		}
		return num;
	}
}