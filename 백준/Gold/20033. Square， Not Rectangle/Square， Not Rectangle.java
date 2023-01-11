import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] heights;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		heights = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int left = 1;
		int right = N;
		int answer = 1;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (check(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.print(answer);
	}

	public static boolean check(int target) {
		int idx = 0;
		int straight = 0;
		while (idx < N) {
			if (heights[idx] >= target) {
				if (++straight >= target) {
					return true;
				}
			} else {
				straight = 0;
			}
			idx++;
		}
		return false;
	}
}