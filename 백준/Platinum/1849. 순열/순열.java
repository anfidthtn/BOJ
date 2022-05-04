import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] orders = new int[N + 1];
		int[] segTree = new int[4 * N];
		initTree(segTree, 1, 1, N);
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			int idx = getIdx(segTree, 1, 1, N, num);
			orders[idx] = i;
//			update(segTree, 1, 1, N, idx);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(orders[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	public static void initTree(int[] segTree, int nodeNum, int left, int right) {
		segTree[nodeNum] = right - left + 1;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		initTree(segTree, nodeNum * 2, left, mid);
		initTree(segTree, nodeNum * 2 + 1, mid + 1, right);
	}
	public static int getIdx(int[] segTree, int nodeNum, int left, int right, int target) {
		segTree[nodeNum]--;
		if (left == right) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (segTree[nodeNum * 2] <= target) {
			return getIdx(segTree, nodeNum * 2 + 1, mid + 1, right, target - segTree[nodeNum * 2]);
		}
		else {
			return getIdx(segTree, nodeNum * 2, left, mid, target);
		}
	}
//	public static void update(int[] segTree, int nodeNum, int left, int right, int targetIdx) {
//		if (targetIdx < left || right < targetIdx) {
//			return;
//		}
//		segTree[nodeNum]--;
//		if (left == right) {
//			return;
//		}
//		int mid = (left + right) >> 1;
//		update(segTree, nodeNum * 2, left, mid, targetIdx);
//		update(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx);
//	}
}