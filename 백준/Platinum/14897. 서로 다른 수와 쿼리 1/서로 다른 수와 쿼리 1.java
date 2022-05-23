import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] nums;
	static int[] numCount;

	static int diffCount;
	static Map<Integer, Integer> valueIdx;

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
		int N = Integer.parseInt(br.readLine());
		final int sqrtN = (int) Math.sqrt(N);
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N + 1];
		Set<Integer> valueSet = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			valueSet.add(nums[i]);
		}
		List<Integer> valueList = new ArrayList<>(valueSet);
		valueList.sort(Integer::compareTo);
		for(int i = 1; i <= N; i++) {
			nums[i] = getIdx(valueList, 0, valueList.size() - 1, nums[i]);
		}
		
		numCount = new int[valueList.size()];
		
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}
	public static int getIdx(List<Integer> list, int left, int right, int value) {
		if (left == right) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (list.get(mid) == value) {
			return mid;
		}
		if (list.get(mid) < value) {
			return getIdx(list, mid + 1, right, value);
		}
		return getIdx(list, left, mid - 1, value);
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