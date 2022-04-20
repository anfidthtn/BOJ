import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Integer> segTree = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		initSegTree(1, 1, N);
		// 세그트리에 잘 들어갔나 확인하는 용도
//		for (Integer n : segTree.keySet()) {
//			System.out.println(n + " " + segTree.get(n));
//		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int nextIdx = 0;
		for (int i = 0; i < N; i++) {
			nextIdx = getIdx(1, 1, N, 1 + ((getSum(1, 1, N, 1, nextIdx - 1) + K) - 1) % segTree.get(1));
			updateSegTree(1, 1, N, nextIdx, -1);
			if (segTree.get(1) > 0) sb.append(nextIdx).append(", ");
		}
		sb.append(nextIdx).append(">");
		
		System.out.print(sb.toString());
	}
	// 문제 조건에 따른 초기화 간단하게 구현
	public static void initSegTree(int nodeNum, int start, int end) {
		if (start == end) {
			// 리프노드에는 1넣고 끝
			segTree.put(nodeNum, 1);
			return;
		}
		// 범위 내면 초기설정
		segTree.put(nodeNum, end - start + 1);
		// 범위를 왼쪽과 오른쪽으로 나누어서, 해당 인덱스가 들어갈 범위라면 더해주는 연산을 재귀적으로 수행한다.
		initSegTree(nodeNum * 2, start, (start + end) >> 1);
		initSegTree(nodeNum * 2 + 1, ((start + end) >> 1) + 1, end);
	}
	// 원본 배열의 start ~ end 범위 중, left ~ right 범위까지의 합계를 구하는 메서드
	public static int getSum(int nodeNum, int start, int end, int left, int right) {
		if (right < start || end < left) {
			// 범위 에서 벗어난다면 0 반환
			return 0;
		}
		
		if (left <= start && end <= right) {
			// 원본 배열의 start ~ end 범위가 구하려는 구간에 완벽히 포함되면 segTree의 nodeNum에 저장되어있기 때문에 저장된 합을 반환
			return segTree.get(nodeNum);
		}
		
		// 범위가 서로 엇나간다면 좌우로 엇나간 범위를 따로 더해준다.
		return getSum(nodeNum * 2, start, (start + end) >> 1, left, right) + getSum(nodeNum * 2 + 1, ((start + end) >> 1) + 1, end, left, right);
	}
	// remain이 0이 될 때까지 재귀적으로 위치를 탐색한다.
	public static int getIdx(int nodeNum, int start, int end, int remain) {
		if (start == end) {
			return start;
		}
		// 왼쪽 자식의 합계보다 많이 남았다면
		if (segTree.get(nodeNum * 2) < remain) {
			// 남은 개수를 왼쪽 자식 개수만큼 줄여주고
			// 오른쪽 자식을 탐색한다
			return getIdx(nodeNum * 2 + 1, ((start + end) >> 1) + 1, end, remain - segTree.get(nodeNum * 2));
		}
		// 왼쪽 자식의 합계보다 작거나 같다면
		else {
			// 왼쪽 자식을 탐색한다.
			return getIdx(nodeNum * 2, start, (start + end) >> 1, remain);
		}
	}
	public static void updateSegTree(int nodeNum, int start, int end, int changeIdx, int value) {
		if (changeIdx < start || end < changeIdx) {
			// 바꿀 인덱스가 범위 내에 없으면 넘김
			return;
		}
		// 범위 내면 더해줌
		if (!segTree.containsKey(nodeNum)) {
			segTree.put(nodeNum, value);
		}
		else {
			segTree.put(nodeNum, segTree.get(nodeNum) + value);
		}
		// 리프 노드가 아니면
		if (start != end){
			// 범위를 왼쪽과 오른쪽으로 나누어서, 해당 인덱스가 들어갈 범위라면 더해주는 연산을 재귀적으로 수행한다.
			updateSegTree(nodeNum * 2, start, (start + end) >> 1, changeIdx, value);
			updateSegTree(nodeNum * 2 + 1, ((start + end) >> 1) + 1, end, changeIdx, value);
		}
	}
}