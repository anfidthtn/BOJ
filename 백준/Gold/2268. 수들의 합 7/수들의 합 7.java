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
		
		long[] segTree = new long[N * 4];
		int[] An = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 0) {
				if (b > c) {
					sb.append(getSum(segTree, 1, 1, N, c, b)).append("\n");
				}
				else {					
					sb.append(getSum(segTree, 1, 1, N, b, c)).append("\n");
				}
			}
			else {
				modify(segTree, An, 1, 1, N, b, c);
			}
		}
		System.out.println(sb.toString());
	}
	public static void modify(long[] segTree, int[] An, int nodeNum, int left, int right, int tIdx, int value) {
		if (tIdx < left || right < tIdx) {
			return;
		}
		segTree[nodeNum] -= An[tIdx];
		segTree[nodeNum] += value;
		if (right <= left) {
			An[tIdx] = value;
			return;
		}
		int mid = (left + right + 1) >> 1;
		modify(segTree, An, nodeNum * 2, left, mid - 1, tIdx, value);
		modify(segTree, An, nodeNum * 2 + 1, mid, right, tIdx, value);
	}
	public static long getSum(long[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right + 1) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid - 1, start, end)
				+ getSum(segTree, nodeNum * 2 + 1, mid, right, start, end);
	}
}