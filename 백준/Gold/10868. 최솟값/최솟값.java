import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] segTree = new int[4 * N];
		
		initSegTree(segTree, 1, 1, N);
		
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			updateSegTree(segTree, 1, 1, N, i, num);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(getMin(segTree, 1, 1, N, start, end)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int getMin(int[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return Integer.MAX_VALUE;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right + 1) >> 1;
		int minValue = Integer.MAX_VALUE;
		int leftMin = getMin(segTree, nodeNum * 2, left, mid - 1, start, end);
		int rightMin = getMin(segTree, nodeNum * 2 + 1, mid, right, start, end);
		if (minValue > leftMin) {
			minValue = leftMin;
		}
		if (minValue > rightMin) {
			minValue = rightMin;
		}
		return minValue;
	}
	
	public static void updateSegTree(int[] segTree, int nodeNum, int left, int right, int idx, int value) {
		if (idx < left || right < idx) {
			return;
		}
		
		if (segTree[nodeNum] > value) {
			segTree[nodeNum] = value;			
		}
		if (right <= left) {
			return;
		}
		int mid = (left + right + 1) >> 1;
		updateSegTree(segTree, nodeNum * 2, left, mid - 1, idx, value);
		updateSegTree(segTree, nodeNum * 2 + 1, mid, right, idx, value);
	}
	
	public static void initSegTree(int[] segTree, int nodeNum, int left, int right) {
		segTree[nodeNum] = Integer.MAX_VALUE;
		if (right <= left) {
			return;
		}
		int mid = (left + right + 1) >> 1;
		initSegTree(segTree, nodeNum * 2, left, mid - 1);
		initSegTree(segTree, nodeNum * 2 + 1, mid, right);
	}	
}