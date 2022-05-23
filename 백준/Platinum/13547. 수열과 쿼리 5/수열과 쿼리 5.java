import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] nums;
	static int[] numCount;

	static int diffCount;

	static class Query {
		int start;
		int end;
		int idx;

		public Query(int start, int end, int idx) {
			this.start = start;
			this.end = end;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numCount = new int[1000001];
		int N = Integer.parseInt(br.readLine());
		final int sqrtN = (int) Math.sqrt(N);
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		Query[] querys = new Query[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			querys[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(querys, new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				if (o1.start / sqrtN - o2.start / sqrtN == 0) {
					return o1.end - o2.end;
				}
				return o1.start / sqrtN - o2.start / sqrtN;
			}
		});
		int[] results = new int[M];
		diffCount = 0;
		int leftIdx = 1;
		int rightIdx = 0;
		for (int i = 0; i < M; i++) {
			while (rightIdx < querys[i].end) {
				append(nums[++rightIdx]);
			}
			while (querys[i].start < leftIdx) {
				append(nums[--leftIdx]);
			}
			while (querys[i].end < rightIdx) {
				pop(nums[rightIdx--]);
			}
			while (leftIdx < querys[i].start) {
				pop(nums[leftIdx++]);
			}
			results[querys[i].idx] = diffCount;
		}
		StringBuilder sb = new StringBuilder();
		for (int result : results) {
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void append(int num) {
		if (numCount[num]++ == 0) {
			diffCount++;
		}
	}

	public static void pop(int num) {
		if (--numCount[num] == 0) {
			diffCount--;
		}
	}
}