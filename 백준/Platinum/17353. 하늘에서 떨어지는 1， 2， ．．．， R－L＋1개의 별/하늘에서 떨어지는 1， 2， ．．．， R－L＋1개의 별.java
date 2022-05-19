import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] segTree;
	static long[] lazy;
	static long[] lazyCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		segTree = new long[4 * N];
		lazy = new long[4 * N];
		lazyCount = new long[4 * N];
		for(int i = 1; i <= N; i++) {
			initTree(1, 1, N, i, Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().charAt(0) == '1') {
				update(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(getSum(1, 1, N, Integer.parseInt(st.nextToken()))).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static void update(int nodeNum, int left, int right, int start, int end) {
		lazyPropagate(nodeNum, left, right);
		if (end < left || right < start) {
			return;
		}
		if (start <= left && right <= end) {
			lazy[nodeNum] += left - start + 1;
			lazyCount[nodeNum]++;
			return;
		}
		int mid = (left + right) >> 1;
		update(nodeNum * 2, left, mid, start, end);
		update(nodeNum * 2 + 1, mid + 1, right, start, end);
		segTree[nodeNum] = segTree[nodeNum * 2] + segTree[nodeNum * 2 + 1];
	}
	
	public static long getSum(int nodeNum, int left, int right, int targetIdx) {
		lazyPropagate(nodeNum, left, right);
		if(targetIdx < left || right < targetIdx) {
			return 0;
		}
		if (left == right) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(nodeNum * 2, left, mid, targetIdx)
				+ getSum(nodeNum * 2 + 1, mid + 1, right, targetIdx);
	}
	public static void lazyPropagate(int nodeNum, int left, int right) {
		int size = right - left + 1;
		if (size >= 2) {
			if (lazyCount[nodeNum] > 0) {
				int mid = (left + right) >> 1;
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazyCount[nodeNum * 2] += lazyCount[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum] + (mid - left + 1) * lazyCount[nodeNum];
				lazyCount[nodeNum * 2 + 1] += lazyCount[nodeNum];
			}
		}
		segTree[nodeNum] += lazy[nodeNum] * size;
		segTree[nodeNum] += calcSum(size) * lazyCount[nodeNum];
		lazy[nodeNum] = 0;
		lazyCount[nodeNum] = 0;
	}
	public static long calcSum(long size) {
		return (size * (size - 1)) / 2;
	}
	public static void initTree(int nodeNum, int left, int right, int targetIdx, int value) {
		if(targetIdx < left || right < targetIdx) {
			return;
		}
		segTree[nodeNum] += value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		initTree(nodeNum * 2, left, mid, targetIdx, value);
		initTree(nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}
}