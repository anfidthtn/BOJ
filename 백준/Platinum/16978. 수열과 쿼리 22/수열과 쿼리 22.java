import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class ChangeQuery {
		int idx;
		int value;

		public ChangeQuery(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}

	static class GetQuery {
		int targetQuery;
		int start;
		int end;
		int saveIdx;

		public GetQuery(int targetQuery, int start, int end, int saveIdx) {
			this.targetQuery = targetQuery;
			this.start = start;
			this.end = end;
			this.saveIdx = saveIdx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] values = new int[N + 1];
		long[] segTree = new long[(N + 1) * 4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			update(segTree, 1, 1, N, i, values[i]);
		}
		int M = Integer.parseInt(br.readLine());
		List<ChangeQuery> changeQuerys = new ArrayList<>();
		List<GetQuery> getQuerys = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken().charAt(0)) {
			case '1':
				changeQuerys.add(new ChangeQuery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				break;
			case '2':
				getQuerys.add(new GetQuery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), getQuerys.size()));
				break;
			}
		}
		long[] results = new long[getQuerys.size()];
		getQuerys.sort(new Comparator<GetQuery>() {
			@Override
			public int compare(GetQuery o1, GetQuery o2) {
				return o1.targetQuery - o2.targetQuery;
			}
		});
		int nextChangeIdx = 0;
		for (GetQuery getQuery : getQuerys) {
			while (nextChangeIdx < getQuery.targetQuery) {
				int idx = changeQuerys.get(nextChangeIdx).idx;
				int diff = changeQuerys.get(nextChangeIdx).value - values[idx];
				values[idx] = changeQuerys.get(nextChangeIdx).value;
				update(segTree, 1, 1, N, idx, diff);
				nextChangeIdx++;
			}
			results[getQuery.saveIdx] = getSum(segTree, 1, 1, N, getQuery.start, getQuery.end);
		}
		StringBuilder sb = new StringBuilder();
		for (long result : results) {
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void update(long[] segTree, int nodeNum, int left, int right, int targetIdx, int value) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		segTree[nodeNum] += value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		update(segTree, nodeNum * 2, left, mid, targetIdx, value);
		update(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}

	public static long getSum(long[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid, start, end)
				+ getSum(segTree, nodeNum * 2 + 1, mid + 1, right, start, end);
	}
}