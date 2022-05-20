import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] colorCount;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		final int sqrtN = (int) Math.sqrt(N);
		int Q = Integer.parseInt(st.nextToken());
		int[] colorinfos = new int[N + 1];
		colorCount = new int[200001];
		countCount = new int[100001];
		maxCount = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colorinfos[i] = Integer.parseInt(st.nextToken()) + 100_000;
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
		int[] results = new int[Q];
		int leftIdx = 1;
		int rightIdx = 0;
		for (int i = 0; i < Q; i++) {
			int idx = querys[i].idx;
			int start = querys[i].start;
			int end = querys[i].end;
			while(start < leftIdx) {
				plus(colorinfos[--leftIdx]);
			}
			while(rightIdx < end) {
				plus(colorinfos[++rightIdx]);
			}
			while(leftIdx < start) {
				minus(colorinfos[leftIdx++]);
			}
			while(end < rightIdx) {
				minus(colorinfos[rightIdx--]);
			}
			results[idx] = maxCount;
		}
		StringBuilder sb = new StringBuilder();
		for(int result : results) {
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void plus(int color) {
		if (countCount[colorCount[color]] > 0) {
			countCount[colorCount[color]]--;
		}
		if (maxCount == colorCount[color]) {
			maxCount = colorCount[color] + 1;
		}
		countCount[++colorCount[color]]++;
	}

	public static void minus(int color) {
		if (colorCount[color] == maxCount) {
			if (countCount[colorCount[color]] == 1) {
				maxCount--;
			}
		}
		countCount[colorCount[color]]--;
		countCount[--colorCount[color]]++;
	}
}