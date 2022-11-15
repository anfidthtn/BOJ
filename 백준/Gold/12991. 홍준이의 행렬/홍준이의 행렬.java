import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int K;
	static long[] As;
	static long[] Bs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		As = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		Bs = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		Arrays.sort(As);
		Arrays.sort(Bs);
		long left = 1;
		long right = 1_000_000_000l * 1_000_000_000;
		long answer = -1;
		while (left <= right) {
			long mid = (left + right) >> 1;
			int count = 0;
			int prevRowCount = N;
			for(int i = 0; i < N && prevRowCount > 0 && count < K; i++) {
				prevRowCount = getRowCount(i, prevRowCount - 1, mid);
				count += prevRowCount;
			}
			if (count < K) {
				left = mid + 1;
			}
			else {
				answer = mid;
				right = mid - 1;
			}
		}
		System.out.print(answer);
	}
	public static int getRowCount(int row, int colLimit, long target) {
		int left = 0;
		int right = colLimit;
		int answer = -1;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (As[row] * Bs[mid] <= target) {
				answer = mid;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		return answer + 1;
	}
}