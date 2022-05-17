import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] values = new int[N + 1];
		int[] segTree = new int[4 * N + 4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			update(segTree, values, 1, 1, N, i);
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
			case '1':
				int targetIdx = Integer.parseInt(st.nextToken());
				values[targetIdx] = Integer.parseInt(st.nextToken());
				update(segTree, values, 1, 1, N, targetIdx);
				break;
			case '2':
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int firstMaxIdx = getIdx(segTree, values, 1, 1, N, start, end);
				int leftMaxIdx = getIdx(segTree, values, 1, 1, N, start, firstMaxIdx - 1);
				int rightMaxIdx = getIdx(segTree, values, 1, 1, N, firstMaxIdx + 1, end);
				sb.append(values[firstMaxIdx] + Math.max(values[leftMaxIdx], values[rightMaxIdx])).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static int getIdx(int[] segTree, int[] values, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		int leftMaxIdx = getIdx(segTree, values, nodeNum * 2, left, mid, start, end);
		int rightMaxIdx = getIdx(segTree, values, nodeNum * 2 + 1, mid + 1, right, start, end);
		if (values[leftMaxIdx] > values[rightMaxIdx]) {
			return leftMaxIdx;
		}
		else {
			return rightMaxIdx;
		}
	}
	public static void update(int[] segTree, int[] values, int nodeNum, int left, int right, int targetIdx) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		if (left == right) {
			segTree[nodeNum] = left;
			return;
		}
		int mid = (left + right) >> 1;
		update(segTree, values, nodeNum * 2, left, mid, targetIdx);
		update(segTree, values, nodeNum * 2 + 1, mid + 1, right, targetIdx);
		if (values[segTree[nodeNum * 2]] > values[segTree[nodeNum * 2 + 1]]) {
			segTree[nodeNum] = segTree[nodeNum * 2];
		}
		else {
			segTree[nodeNum] = segTree[nodeNum * 2 + 1];			
		}
	}
}