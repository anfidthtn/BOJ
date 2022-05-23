import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] nums;
	static int[] numCount;
	static int N;
	static int sqrtN;
	static long power;

	static class Query {
		int idx;
		int start;
		int end;

		public Query(int idx, int start, int end) {
			this.idx = idx;
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		sqrtN = (int) Math.sqrt(N);
		int Q = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		numCount = new int[1000001];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Query[] querys = new Query[Q];
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			querys[i] = new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(querys, new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				if (o1.start / sqrtN == o2.start / sqrtN) {
					return o1.end - o2.end;
				}
				return o1.start / sqrtN - o2.start / sqrtN;
			}
		});

		long[] results = new long[Q];
		power = 0;
		int leftIdx = 1;
		int rightIdx = 0;
		for (int i = 0; i < Q; i++) {
			while (rightIdx < querys[i].end) {
				rightIdx++;
				modify(nums[rightIdx], 1);
			}
			while (querys[i].start < leftIdx) {
				leftIdx--;
				modify(nums[leftIdx], 1);
			}
			while (querys[i].end < rightIdx) {
				modify(nums[rightIdx], -1);
				rightIdx--;
			}
			while (leftIdx < querys[i].start) {
				modify(nums[leftIdx], -1);
				leftIdx++;
			}
			results[querys[i].idx] = power;
		}
		StringBuilder sb = new StringBuilder();
		for (long result : results) {
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void modify(int num, int value) {
		power -= (long) num * numCount[num] * numCount[num];
		numCount[num] += value;
		power += (long) num * numCount[num] * numCount[num];
	}
}