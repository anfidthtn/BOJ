import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] heights = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(heights);
		long[] treeSum = new long[N];
		treeSum[0] = heights[0];
		for(int i = 1; i < N; i++) {
			treeSum[i] = treeSum[i - 1] + heights[i];
		}
		System.out.print(getMax(treeSum, heights, 0, 1_000_000_000, N, M));
	}
	public static int getMax(long[] treeSum, int[] heights, int left, int right, int N, int M) {
		if (right <= left) {
			return right;
		}
		int mid = (int) (((long) left + (long) right + 1) / 2);
		if (!check(treeSum, mid, heights, N, M)) {
			return getMax(treeSum, heights, left, mid - 1, N, M);
		}
		else {
			return getMax(treeSum, heights, mid, right, N, M);
		}
	}
	public static boolean check(long[] treeSum, int H, int[] heights, int N, int M) {
		int idx = getIdx(heights, 0, N, H);
		if (idx == N) {
			return false;
		}
		long total = treeSum[N - 1];
		if (idx != 0) {
			total -= treeSum[idx - 1];
		}
		int count = N - idx;
		total -= count * (long) H;
		if (total >= M) {
			return true;
		}
		return false;
	}
	public static int getIdx(int[] heights, int left, int right, int target) {
		if (right <= left) {
			return right;
		}
		int mid = (left + right) >> 1;
		if (heights[mid] <= target) {
			return getIdx(heights, mid + 1, right, target);
		}
		else {
			return getIdx(heights, left, mid, target);
		}
	}
}