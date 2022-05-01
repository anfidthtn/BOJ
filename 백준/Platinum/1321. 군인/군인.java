import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] segTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		segTree = new int[4 * N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			update(1, 1, N, i, Integer.parseInt(st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if (query == 1) {
				update(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(getIdx(1, 1, N, Integer.parseInt(st.nextToken()))).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	public static int getIdx(int nodeNum, int leftIdx, int rightIdx, int target) {
		if (leftIdx == rightIdx) {
			return leftIdx;
		}
		int mid = (leftIdx + rightIdx) >> 1;
		if (target <= segTree[nodeNum * 2]) {
			return getIdx(nodeNum * 2, leftIdx, mid, target);
		}
		else {
			return getIdx(nodeNum * 2 + 1, mid + 1, rightIdx, target - segTree[nodeNum * 2]);
		}
	}

	public static void update(int nodeNum, int leftIdx, int rightIdx, int targetIdx, int value) {
		if (targetIdx < leftIdx || rightIdx < targetIdx) {
			return;
		}
		segTree[nodeNum] += value;
		if (leftIdx == rightIdx) {
			return;
		}
		int mid = (leftIdx + rightIdx) >> 1;
		if (targetIdx <= mid) {
			update(nodeNum * 2, leftIdx, mid, targetIdx, value);
		} else {
			update(nodeNum * 2 + 1, mid + 1, rightIdx, targetIdx, value);
		}
	}
}