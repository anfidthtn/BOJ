import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static int[] hats;
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
		int sqrtN = (int) Math.sqrt(N);
		int C = Integer.parseInt(st.nextToken());
		hats = new int[N + 1];
		colorCount = new int[C + 1];
		countCount = new int[300001];
		maxCount = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			hats[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		Query[] querys = new Query[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			querys[i] = new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(querys, new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				if(o1.start / sqrtN == o2.start / sqrtN) {
					return o1.end - o2.end;
				}
				return o1.start / sqrtN - o2.start / sqrtN;
			}
		});
		
		int[] results = new int[M];
		int left = 1;
		int right = 0;
		for(int i = 0; i < M; i++) {
			while(right < querys[i].end) {
				plus(hats[++right]);
			}
			while(querys[i].start < left) {
				plus(hats[--left]);
			}
			while(querys[i].end < right) {
				minus(hats[right--]);
			}
			while(left < querys[i].start) {
				minus(hats[left++]);
			}
			if (maxCount > (right - left + 1) / 2) {
				results[querys[i].idx] = getMaxColor(left, right);
			}
			else {
				results[querys[i].idx] = -1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int result : results) {
			if (result == -1) {
				sb.append("no");
			}
			else {
				sb.append("yes ").append(result);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int getMaxColor(int left, int right) {
		Random random = new Random();
		while(true) {
			int idx = left + random.nextInt(right - left + 1);
			if (colorCount[hats[idx]] == maxCount) {
				return hats[idx];
			}
		}
	}
	public static void plus(int color) {
		countCount[colorCount[color]]--;
		colorCount[color]++;
		countCount[colorCount[color]]++;
		if (colorCount[color] > maxCount) {
			maxCount++;
		}
	}
	public static void minus(int color) {
		if (colorCount[color] == maxCount && countCount[colorCount[color]] == 1) {
			maxCount--;
		}
		countCount[colorCount[color]]--;
		colorCount[color]--;
		countCount[colorCount[color]]++;
		
	}
}