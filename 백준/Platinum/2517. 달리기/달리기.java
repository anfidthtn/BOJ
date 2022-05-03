import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] orders = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			orders[i] = Integer.parseInt(br.readLine());
		}
		int[] places = Arrays.copyOf(orders, N + 1);
		Arrays.sort(places);
		StringBuilder sb = new StringBuilder();
		int[] segTree = new int[4 * N];
		for(int i = 1; i <= N; i++) {
			int idx = getIdx(places, 1, N, orders[i]);
			sb.append(getSum(segTree, 1, 1, N, idx + 1) + 1).append("\n");
			addSum(segTree, 1, 1, N, idx);
		}
		System.out.println(sb.toString());
	}
	public static void addSum(int[] segTree, int nodeNum, int left, int right, int targetIdx) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		segTree[nodeNum]++;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		addSum(segTree, nodeNum * 2, left, mid, targetIdx);
		addSum(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx);
	}
	public static int getSum(int[] segTree, int nodeNum, int left, int right, int start) {
		if (right < start) {
			return 0;
		}
		if (start <= left) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid, start) + getSum(segTree, nodeNum * 2 + 1, mid + 1, right, start);
	}
	public static int getIdx(int[] places, int left, int right, int target) {
		if (left == right) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (target <= places[mid]) {
			return getIdx(places, left, mid, target);
		}
		else {
			return getIdx(places, mid + 1, right, target);
		}
	}
}