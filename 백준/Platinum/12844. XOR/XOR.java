import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int value;
		int lazy;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] segTree = new Node[4 * N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			insert(segTree, 1, 0, N - 1, i, Integer.parseInt(st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == '1') {
				update(segTree, 1, 0, N - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(getRangeXOR(segTree, 1, 0, N - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	public static void insert(Node[] segTree, int nodeNum, int left, int right, int tIdx, int value) {
		if (tIdx < left || right < tIdx) {
			return;
		}
		if (segTree[nodeNum] == null) {
			segTree[nodeNum] = new Node();
		}
		segTree[nodeNum].value ^= value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		insert(segTree, nodeNum * 2, left, mid, tIdx, value);
		insert(segTree, nodeNum * 2 + 1, mid + 1, right, tIdx, value);
	}

	public static void update(Node[] segTree, int nodeNum, int left, int right, int start, int end, int value) {
		if (right - left + 1 >= 2) {
			// 2개 이상을 포함하는 구간은 자식 노드 둘 다 있음이 보장되니까 갖고있던 lazy를 전파
			segTree[nodeNum * 2].lazy ^= segTree[nodeNum].lazy;
			segTree[nodeNum * 2 + 1].lazy ^= segTree[nodeNum].lazy;
		}
		if (((right - left + 1) & 1) != 0) {
			// 홀수 개면 XOR의미가 있으니 lazy를 XOR
			segTree[nodeNum].value ^= segTree[nodeNum].lazy;
		}
		// lazy 처리가 끝났으니 lazy 0으로
		segTree[nodeNum].lazy = 0;
		// 범위 밖이라면 넘어감
		if (end < left || right < start) {
			return;
		}
		// 범위를 완벽하게 덮으면 value를 자식의 lazy로 전파하고, 자신 개수만큼 적당히 value 계산을 함.
		if (start <= left && right <= end) {
			if (right - left + 1 >= 2) {
				// 2개 이상을 포함하는 구간은 자식 노드 둘 다 있음이 보장되니까 갖고있던 lazy를 전파
				segTree[nodeNum * 2].lazy ^= value;
				segTree[nodeNum * 2 + 1].lazy ^= value;
			}
			if (((right - left + 1) & 1) != 0) {
				// 홀수 개면 XOR의미가 있으니 lazy를 XOR
				segTree[nodeNum].value ^= value;
			}
			return;
		}
		// 범위를 완벽하게 못 덮으면 범위를 나눠서 계산
		int mid = (left + right) >> 1;
		update(segTree, nodeNum * 2, left, mid, start, end, value);
		update(segTree, nodeNum * 2 + 1, mid + 1, right, start, end, value);
		// 자식들이 계산되고 난 이후에는 자식들의 값으로 현재 값 계산
		segTree[nodeNum].value = segTree[nodeNum * 2].value ^ segTree[nodeNum * 2 + 1].value;
	}

	public static int getRangeXOR(Node[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (right - left + 1 >= 2) {
			// 2개 이상을 포함하는 구간은 자식 노드 둘 다 있음이 보장되니까 갖고있던 lazy를 전파
			segTree[nodeNum * 2].lazy ^= segTree[nodeNum].lazy;
			segTree[nodeNum * 2 + 1].lazy ^= segTree[nodeNum].lazy;
		}
		if (((right - left + 1) & 1) != 0) {
			// 홀수 개면 XOR의미가 있으니 lazy를 XOR
			segTree[nodeNum].value ^= segTree[nodeNum].lazy;
		}
		// lazy 처리가 끝났으니 lazy 0으로
		segTree[nodeNum].lazy = 0;
		// 범위 밖이라면 넘어감
		if (end < left || right < start) {
			return 0;
		}
		// 범위를 완벽하게 덮으면 저장된 XOR값 반환
		if (start <= left && right <= end) {
			return segTree[nodeNum].value;
		}
		// 완벽하게 덮지 못하면 자식들로부터 값 받아서 계산
		int mid = (left + right) >> 1;
		return getRangeXOR(segTree, nodeNum * 2, left, mid, start, end) ^ getRangeXOR(segTree, nodeNum * 2 + 1, mid + 1, right, start, end);
	}
}