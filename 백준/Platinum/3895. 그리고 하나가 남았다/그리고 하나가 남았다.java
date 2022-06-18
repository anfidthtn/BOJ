import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			if (N == 0) {
				break;
			}
			int[] segTree = new int[4 * N];
			initTree(segTree, 1, 1, N);
			for (int i = 0; i < N - 1; i++) {
				int poped = pop(segTree, 1, 1, N, target);
				target = ((getSum(segTree, 1, 1, N, 1, poped) + K) + segTree[1] - 1) % segTree[1] + 1;
			}
			sb.append(pop(segTree, 1, 1, N, target)).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int pop(int[] segTree, int nodeNum, int left, int right, int target) {
		segTree[nodeNum]--;
		if (left == right) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (target <= segTree[nodeNum * 2]) {
			return pop(segTree, nodeNum * 2, left, mid, target);
		}
		else {
			return pop(segTree, nodeNum * 2 + 1, mid + 1, right, target - segTree[nodeNum * 2]);
		}
	}

	public static int getSum(int[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid, start, end)
				+ getSum(segTree, nodeNum * 2 + 1, mid + 1, right, start, end);
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
}