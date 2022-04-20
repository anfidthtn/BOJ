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
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Node[] segTree = new Node[4 * N];
		for (int i = 1; i <= N; i++) {
			insertTree(segTree, 1, 1, N, i, Long.parseLong(br.readLine()));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = N + 2; i <= N + M + K + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 2) {
				sb.append(getRangeSum(segTree, 1, 1, N, b, c)).append("\n");
			} else {
				long d = Long.parseLong(st.nextToken());
				updateTree(segTree, 1, 1, N, b, c, d);
			}
		}
		System.out.println(sb.toString());
	}

	public static void insertTree(Node[] segTree, int nodeNum, int left, int right, int tIdx, long value) {
		if (tIdx < left || right < tIdx)
			return;
		if (segTree[nodeNum] == null) {
			segTree[nodeNum] = new Node();
		}
		segTree[nodeNum].value += value;
		if (right <= left)
			return;
		int mid = (left + right + 1) >> 1;
		insertTree(segTree, nodeNum * 2, left, mid - 1, tIdx, value);
		insertTree(segTree, nodeNum * 2 + 1, mid, right, tIdx, value);
	}

	public static void updateTree(Node[] segTree, int nodeNum, int left, int right, int start, int end, long value) {
		
		segTree[nodeNum].value += segTree[nodeNum].lazy * (right - left + 1);
		if (left < right) {
			segTree[nodeNum * 2].lazy += segTree[nodeNum].lazy;
			segTree[nodeNum * 2 + 1].lazy += segTree[nodeNum].lazy;
		}
		segTree[nodeNum].lazy = 0;
		
		if (end < left || right < start) {
			return;
		}


		if (start <= left && right <= end) {
			segTree[nodeNum].value += value * (right - left + 1);
			if (left < right) {
				segTree[nodeNum * 2].lazy += value;
				segTree[nodeNum * 2 + 1].lazy += value;
			}
			return;
		}
		int mid = (left + right + 1) >> 1;
		updateTree(segTree, nodeNum * 2, left, mid - 1, start, end, value);
		updateTree(segTree, nodeNum * 2 + 1, mid, right, start, end, value);
		segTree[nodeNum].value = segTree[nodeNum * 2].value + segTree[nodeNum * 2 + 1].value;
	}

	public static long getRangeSum(Node[] segTree, int nodeNum, int left, int right, int start, int end) {
		
		segTree[nodeNum].value += segTree[nodeNum].lazy * (right - left + 1);
		if (left < right) {
			segTree[nodeNum * 2].lazy += segTree[nodeNum].lazy;
			segTree[nodeNum * 2 + 1].lazy += segTree[nodeNum].lazy;
		}
		segTree[nodeNum].lazy = 0;
		
		if (end < left || right < start) {
			return 0;
		}
		
		if (start <= left && right <= end) {
			return segTree[nodeNum].value;
		}
		int mid = (left + right + 1) >> 1;
		return getRangeSum(segTree, nodeNum * 2, left, mid - 1, start, end)
				+ getRangeSum(segTree, nodeNum * 2 + 1, mid, right, start, end);
	}
}