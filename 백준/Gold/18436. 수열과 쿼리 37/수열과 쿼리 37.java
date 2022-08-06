import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] nums;
	static int[][] tree;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		tree = new int[2][4 * N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			init(1, 1, N, i, nums[i]);
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if (q == 1) {
				if ((1 & (nums[l] ^ r)) == 1) {
					change(1, 1, N, l, (nums[l] & 1) * 2 - 1);
				}
				nums[l] = r;
			} else {
				sb.append(getSum(tree[q % 2], 1, 1, N, l, r)).append("\n");
			}
		}
		System.out.print(sb.toString());
	}

	public static int getSum(int[] tree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return tree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(tree, nodeNum * 2, left, mid, start, end)
				+ getSum(tree, nodeNum * 2 + 1, mid + 1, right, start, end);
	}

	public static void change(int nodeNum, int left, int right, int idx, int value) {
		if (idx < left || right < idx) {
			return;
		}
		tree[0][nodeNum] += value;
		tree[1][nodeNum] -= value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		change(nodeNum * 2, left, mid, idx, value);
		change(nodeNum * 2 + 1, mid + 1, right, idx, value);
	}

	public static void init(int nodeNum, int left, int right, int idx, int value) {
		if (idx < left || right < idx) {
			return;
		}
		if ((value & 1) == 0) {
			tree[0][nodeNum]++;
		} else {
			tree[1][nodeNum]++;
		}

		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		init(nodeNum * 2, left, mid, idx, value);
		init(nodeNum * 2 + 1, mid + 1, right, idx, value);
	}
}