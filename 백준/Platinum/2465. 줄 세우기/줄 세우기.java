import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			// 키 정렬할 배열
			heights[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(heights);
		int[] orders = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N ; i++) {
			// 몇 등
			orders[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] segTree = new int[4 * N];
		initTree(segTree, 1, 1, N);
		for(int i = N; i >= 1; i--) {
			// 자리를 결정하지 않은 사람 중, 가장 뒤에 배치될 사람은 남은 사람 중 자신의 순위를 정확히 알 수 있다.
			// 세그먼트 트리에서 순위별 인덱스 빼와서 넣는다.
			orders[i] = heights[getIdx(segTree, 1, 1, N, orders[i])];
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(orders[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static int getIdx(int[] segTree, int nodeNum, int left, int right, int target) {
		segTree[nodeNum]--;
		if(left == right) {
			return left;
		}
		int mid = (left + right) >> 1;
		if (segTree[nodeNum * 2] <= target) {
			return getIdx(segTree, nodeNum * 2 + 1, mid + 1, right, target - segTree[nodeNum * 2]);
		}
		else {
			return getIdx(segTree, nodeNum * 2, left, mid, target);
		}
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