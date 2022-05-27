import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] segTree;
	static int[] lazy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		segTree = new int[4 * N];
		lazy = new int[4 * N];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
			case '0':
				update(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case '1':
				sb.append(getCount(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
				break;
			}
		}
		System.out.print(sb.toString());
		
	}
	public static int getCount(int nodeNum, int left, int right, int start, int end) {
		lazyPropagation(nodeNum, left, right);
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getCount(nodeNum * 2, left, mid, start, end)
				+ getCount(nodeNum * 2 + 1, mid + 1, right, start, end);
	}
	public static void update(int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			lazyPropagation(nodeNum, left, right);
			return;
		}
		if (start <= left && right <= end) {
			lazy[nodeNum] ^= 1;
			lazyPropagation(nodeNum, left, right);
			return;
		}
		lazyPropagation(nodeNum, left, right);
		int mid = (left + right) >> 1;
		update(nodeNum * 2, left, mid, start, end);
		update(nodeNum * 2 + 1, mid + 1, right, start, end);
		segTree[nodeNum] = segTree[nodeNum * 2] + segTree[nodeNum * 2 + 1];
	}
	public static void lazyPropagation(int nodeNum, int left, int right) {
		if (lazy[nodeNum] == 1) {
			int size = right - left + 1;
			if (size >= 2) {
				lazy[nodeNum * 2] ^= lazy[nodeNum];
				lazy[nodeNum * 2 + 1] ^= lazy[nodeNum];
			}
			segTree[nodeNum] = size - segTree[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
}