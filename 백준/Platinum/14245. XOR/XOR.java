import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int lazy;
		int value;
	}

	static Node[] nodes;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new Node[4 * N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			insert(1, 1, N, i, Integer.parseInt(st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().charAt(0) == '1') {
				update(1, 1, N, Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()));
			}
			else {
				int target = Integer.parseInt(st.nextToken()) + 1;
				sb.append(getXOR(1, 1, N, target, target)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	public static int getXOR(int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (nodeNum * 2 < 4 * N && nodes[nodeNum * 2] != null) {
			nodes[nodeNum * 2].lazy ^= nodes[nodeNum].lazy;
			if (nodeNum * 2 + 1 < 4 * N && nodes[nodeNum * 2 + 1] != null) {
				nodes[nodeNum * 2 + 1].lazy ^= nodes[nodeNum].lazy;
			}
		}
		if (((right - left + 1) & 1) != 0) {
			// 개수가 홀수 개일 때만 xor연산의 의미가 있다.
			nodes[nodeNum].value ^= nodes[nodeNum].lazy;
		}
		nodes[nodeNum].lazy = 0;
		if (start <= left && right <= end) {
			return nodes[nodeNum].value;
		}
		int mid = (left + right) >> 1;
		return getXOR(nodeNum * 2, left, mid, start, end) ^ getXOR(nodeNum * 2 + 1, mid + 1, right, start, end);
	}
	public static void update(int nodeNum, int left, int right, int start, int end, int value) {
		if (end < left || right < start) {
			return;
		}
		if (nodeNum * 2 < 4 * N && nodes[nodeNum * 2] != null) {
			nodes[nodeNum * 2].lazy ^= nodes[nodeNum].lazy;
			if (nodeNum * 2 + 1 < 4 * N && nodes[nodeNum * 2 + 1] != null) {
				nodes[nodeNum * 2 + 1].lazy ^= nodes[nodeNum].lazy;
			}
		}
		if (((right - left + 1) & 1) != 0) {
			// 개수가 홀수 개일 때만 xor연산의 의미가 있다.
			nodes[nodeNum].value ^= nodes[nodeNum].lazy;
		}
		nodes[nodeNum].lazy = 0;
		if (start <= left && right <= end) {
			nodes[nodeNum].lazy = value;
			return;
		}
		int mid = (left + right) >> 1;
		update(nodeNum * 2, left, mid, start, end, value);
		update(nodeNum * 2 + 1, mid + 1, right, start, end, value);
	}

	public static void insert(int nodeNum, int left, int right, int targetIdx, int value) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		if (nodes[nodeNum] == null) {
			nodes[nodeNum] = new Node();
		}
		nodes[nodeNum].value ^= value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		insert(nodeNum * 2, left, mid, targetIdx, value);
		insert(nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}
}