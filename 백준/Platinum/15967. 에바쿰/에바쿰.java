import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		long value;
		long lazy;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q1 = Integer.parseInt(st.nextToken());
		int Q2 = Integer.parseInt(st.nextToken());
		Node[] segTree = new Node[N * 4];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			init(segTree, 1, 1, N, i, Long.parseLong(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q1 + Q2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				sb.append(getSum(segTree, 1, 1, N, b, c)).append("\n");
			}
			else {
				update(segTree, 1, 1, N, b, c, Long.parseLong(st.nextToken()));
			}
		}
		System.out.println(sb.toString());
	}

	public static long getSum(Node[] segTree, int nodeNum, int left, int right, int start, int end) {
		lazyPropagation(segTree, nodeNum, left, right);
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum].value;
		}
		int mid = (left + right + 1) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid - 1, start, end)
				+ getSum(segTree, nodeNum * 2 + 1, mid, right, start, end);
	}

	public static void update(Node[] segTree, int nodeNum, int left, int right, int start, int end, long value) {
		lazyPropagation(segTree, nodeNum, left, right);
		if (end < left || right < start)
			return;
		if (start <= left && right <= end) {
			segTree[nodeNum].value += value * (right - left + 1);
			if (left < right) {
				segTree[nodeNum * 2].lazy += value;
				segTree[nodeNum * 2 + 1].lazy += value;
			}
			return;
		}
		int mid = (left + right + 1) >> 1;
		update(segTree, nodeNum * 2, left, mid - 1, start, end, value);
		update(segTree, nodeNum * 2 + 1, mid, right, start, end, value);
		segTree[nodeNum].value = segTree[nodeNum * 2].value + segTree[nodeNum * 2 + 1].value;
	}

	public static void lazyPropagation(Node[] segTree, int nodeNum, int left, int right) {
		if (right < left)
			return;
		segTree[nodeNum].value += segTree[nodeNum].lazy * (right - left + 1);
		if (segTree[nodeNum].lazy != 0) {
			if (left < right) {
				segTree[nodeNum * 2].lazy += segTree[nodeNum].lazy;
				segTree[nodeNum * 2 + 1].lazy += segTree[nodeNum].lazy;
			}
		}
		segTree[nodeNum].lazy = 0;
	}

	public static void init(Node[] segTree, int nodeNum, int left, int right, int tIdx, long value) {
		if (segTree[nodeNum] == null) {
			segTree[nodeNum] = new Node();
		}
		if (tIdx < left || right < tIdx) {
			return;
		}
		segTree[nodeNum].value += value;
		if (right <= left) {
			return;
		}
		int mid = (left + right + 1) >> 1;
		init(segTree, nodeNum * 2, left, mid - 1, tIdx, value);
		init(segTree, nodeNum * 2 + 1, mid, right, tIdx, value);
	}
}