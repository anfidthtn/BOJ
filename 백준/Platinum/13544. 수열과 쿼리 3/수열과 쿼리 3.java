import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		boolean sorted;
		int[] values;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] segTree = new Node[4 * N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			insert(segTree, 1, 1, N, i, Integer.parseInt(st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		int lastAnswer = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			lastAnswer = getCountGreaterThanValue(segTree, 1, 1, N, Integer.parseInt(st.nextToken()) ^ lastAnswer,
					Integer.parseInt(st.nextToken()) ^ lastAnswer, Integer.parseInt(st.nextToken()) ^ lastAnswer); 
			sb.append(lastAnswer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void insert(Node[] segTree, int nodeNum, int left, int right, int targetIdx, int value) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		if (segTree[nodeNum] == null) {
			segTree[nodeNum] = new Node();
			segTree[nodeNum].values = new int[right - left + 1];
		}
		if (left == right) {
			segTree[nodeNum].sorted = true;
			segTree[nodeNum].values[0] = value;
			return;
		}
		int mid = (left + right) >> 1;
		insert(segTree, nodeNum * 2, left, mid, targetIdx, value);
		insert(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}

	public static int getCountGreaterThanValue(Node[] segTree, int nodeNum, int left, int right, int start, int end,
			int value) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			if (!segTree[nodeNum].sorted) {
				merge(segTree, nodeNum);
			}
			return segTree[nodeNum].values.length
					- binarySearch(segTree[nodeNum].values, -1, segTree[nodeNum].values.length, value);
		}
		int mid = (left + right) >> 1;
		return getCountGreaterThanValue(segTree, nodeNum * 2, left, mid, start, end, value)
				+ getCountGreaterThanValue(segTree, nodeNum * 2 + 1, mid + 1, right, start, end, value);
	}

	public static void merge(Node[] segTree, int nodeNum) {
		int leftNum = nodeNum * 2;
		int rightNum = nodeNum * 2 + 1;
		if (!segTree[leftNum].sorted) {
			merge(segTree, leftNum);
		}
		if (!segTree[rightNum].sorted) {
			merge(segTree, rightNum);
		}
		segTree[nodeNum].sorted = true;
		int leftIdx = 0;
		int rightIdx = 0;
		int idx = 0;
		while (leftIdx < segTree[leftNum].values.length || rightIdx < segTree[rightNum].values.length) {
			if (leftIdx < segTree[leftNum].values.length && rightIdx < segTree[rightNum].values.length) {
				if (segTree[leftNum].values[leftIdx] < segTree[rightNum].values[rightIdx]) {
					segTree[nodeNum].values[idx++] = segTree[leftNum].values[leftIdx++];
				} else {
					segTree[nodeNum].values[idx++] = segTree[rightNum].values[rightIdx++];
				}
			} else {
				while (leftIdx < segTree[leftNum].values.length) {
					segTree[nodeNum].values[idx++] = segTree[leftNum].values[leftIdx++];
				}
				while (rightIdx < segTree[rightNum].values.length) {
					segTree[nodeNum].values[idx++] = segTree[rightNum].values[rightIdx++];
				}
			}
		}
	}

	public static int binarySearch(int[] values, int left, int right, int value) {
		if (left == right) {
			if (values[left] > value) {
				return left + 1;
			}
			return left;
		}
		if (left + 1 == right) {
			return right;
		}
		int mid = (left + right) >> 1;
		if (value < values[mid]) {
			return binarySearch(values, left, mid, value);
		} else {
			return binarySearch(values, mid, right, value);
		}
	}
}