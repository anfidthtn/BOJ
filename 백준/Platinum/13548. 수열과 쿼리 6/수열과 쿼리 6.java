import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] numberCount;
	static int[] countCount;
	static int maxCount;

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
		int N = Integer.parseInt(br.readLine());
		final int sqrtN = (int) Math.sqrt(N);
		int[] numberinfos = new int[N + 1];
		numberCount = new int[100001];
		countCount = new int[100001];
		maxCount = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numberinfos[i] = Integer.parseInt(st.nextToken());
		}

		int Q = Integer.parseInt(br.readLine());
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
		int[] results = new int[Q];
		int leftIdx = 1;
		int rightIdx = 0;
		for (int i = 0; i < Q; i++) {
			int idx = querys[i].idx;
			int start = querys[i].start;
			int end = querys[i].end;
			while(start < leftIdx) {
				plus(numberinfos[--leftIdx]);
			}
			while(rightIdx < end) {
				plus(numberinfos[++rightIdx]);
			}
			while(leftIdx < start) {
				minus(numberinfos[leftIdx++]);
			}
			while(end < rightIdx) {
				minus(numberinfos[rightIdx--]);
			}
			results[idx] = maxCount;
		}
		StringBuilder sb = new StringBuilder();
		for(int result : results) {
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void plus(int number) {
		if (countCount[numberCount[number]] > 0) {
			countCount[numberCount[number]]--;
		}
		if (maxCount == numberCount[number]) {
			maxCount = numberCount[number] + 1;
		}
		countCount[++numberCount[number]]++;
	}

	public static void minus(int number) {
		if (numberCount[number] == maxCount) {
			if (countCount[numberCount[number]] == 1) {
				maxCount--;
			}
		}
		countCount[numberCount[number]]--;
		countCount[--numberCount[number]]++;
	}
}