import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] segTree = new long[N * 4 + 4];
		long[] lazy = new long[N * 4 + 4];
		for(int i = 1; i <= N; i++) {
			initTree(segTree, 1, 1, N, i, Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken().charAt(0)) {
			case '1':
				updateTree(segTree, lazy, 1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case '2':
				sb.append(get(segTree, lazy, 1, 1, N, Integer.parseInt(st.nextToken()))).append("\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}
	public static long get(long[] segTree, long[] lazy, int nodeNum, int left, int right, int targetIdx) {
		lazyPropagation(segTree, lazy, nodeNum, left, right);
		if (targetIdx < left || right < targetIdx) {
			return 0;
		}
		if (left == right) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return get(segTree, lazy, nodeNum * 2, left, mid, targetIdx)
				+ get(segTree, lazy, nodeNum * 2 + 1, mid + 1, right, targetIdx);
	}
	public static void updateTree(long[] segTree, long[] lazy, int nodeNum, int left, int right, int start, int end, int value) {
		if (end < left || right < start) {
			lazyPropagation(segTree, lazy, nodeNum, left, right);
			return;
		}
		if (start <= left && right <= end) {
			lazy[nodeNum] += value;
			lazyPropagation(segTree, lazy, nodeNum, left, right);
			return;
		}
		int mid = (left + right) >> 1;
		updateTree(segTree, lazy, nodeNum * 2, left, mid, start, end, value);
		updateTree(segTree, lazy, nodeNum * 2 + 1, mid + 1, right, start, end, value);
		segTree[nodeNum] = segTree[nodeNum * 2] + segTree[nodeNum * 2 + 1];
	}
	public static void lazyPropagation(long[] segTree, long[] lazy, int nodeNum, int left, int right) {
		segTree[nodeNum] += lazy[nodeNum] * (right - left + 1);
		if (right - left + 1 >= 2) {
			lazy[nodeNum * 2] += lazy[nodeNum];
			lazy[nodeNum * 2 + 1] += lazy[nodeNum];
		}
		lazy[nodeNum] = 0;
	}
	public static void initTree(long[] segTree, int nodeNum, int left, int right, int targetIdx, int value) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		segTree[nodeNum] += value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		initTree(segTree, nodeNum * 2, left, mid, targetIdx, value);
		initTree(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}
}