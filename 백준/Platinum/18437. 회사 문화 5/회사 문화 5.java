import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 3, 5, 7번 직원이 1번을 상사로 하고, 2, 4, 6번 직원이 3번을 상사로, 8번 직원이 5번을 상사로 하면
	 * 
	 * empNum
	 * 1 2 3 4 5 6 7 8
	 * treeIdx
	 * 1 3 2 4 6 5 8 7
	 * childsSize
	 * 7 0 3 0 1 0 0 0
	 * 이런식으로 된다.
	 * 1번 직원의 경우 자신의 인덱스가 1이고 자신 뒤로 7인덱스만큼이 자신의 자식
	 * 3번 직원의 경우 자신의 인덱스가 2이고 자신 뒤로 3인덱스만큼이 자신의 자식
	 * 2번 직원의 경우 자신의 인덱스가 3이고 자신 뒤로 0인덱스만큼이 자신의 자식
	 * 이런 의미이다.
	 */
	static int[] treeIdx;
	static int[] childsSize;
	static List<Integer>[] childs;
	static int[] segTree;
	static int[] lazy;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		childs = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			childs[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			childs[Integer.parseInt(st.nextToken())].add(i);
		}
		treeIdx = new int[N + 1];
		childsSize = new int[N + 1];
		
		dfsMatching(0, 0);
		
		segTree = new int[4 * N];
		lazy = new int[4 * N];
		initTree(1, 1, N);
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			switch(q) {
			case 1:
				update(1, 1, N, treeIdx[num] + 1, treeIdx[num] + childsSize[num], 1);
				break;
			case 2:
				update(1, 1, N, treeIdx[num] + 1, treeIdx[num] + childsSize[num], 2);
				break;
			case 3:
				sb.append(getSum(1, 1, N, treeIdx[num] + 1, treeIdx[num] + childsSize[num])).append("\n");
			}
		}
		System.out.print(sb.toString());
		
	}
	public static int dfsMatching(int nowIdx, int empNum) {
		treeIdx[empNum] = nowIdx;
		int childCount = 0;
		for(int child : childs[empNum]) {
			childCount += dfsMatching(nowIdx + childCount + 1, child);
		}
		childsSize[empNum] = childCount;
		return childCount + 1;
	}
	public static int getSum(int nodeNum, int left, int right, int start, int end) {
		lazyPropagation(nodeNum, left, right);
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(nodeNum * 2, left, mid, start, end) + getSum(nodeNum * 2 + 1, mid + 1, right, start, end);
	}
	public static void update(int nodeNum, int left, int right, int start, int end, int value) {
		lazyPropagation(nodeNum, left, right);
		if (end < left || right < start) {
			return;
		}
		if (start <= left && right <= end) {
			lazy[nodeNum] = value;
			lazyPropagation(nodeNum, left, right);
			return;
		}
		int mid = (left + right) >> 1;
		update(nodeNum * 2, left, mid, start, end, value);
		update(nodeNum * 2 + 1, mid + 1, right, start, end, value);
		segTree[nodeNum] = segTree[nodeNum * 2] + segTree[nodeNum * 2 + 1]; 
	}
	public static void lazyPropagation(int nodeNum, int left, int right) {
		if (left < right) {
			if (lazy[nodeNum] > 0) {
				lazy[nodeNum * 2] = lazy[nodeNum];
				lazy[nodeNum * 2 + 1] = lazy[nodeNum];
			}
		}
		if (lazy[nodeNum] == 1) {
			segTree[nodeNum] = right - left + 1;
		}
		else if (lazy[nodeNum] == 2) {
			segTree[nodeNum] = 0;
		}
		lazy[nodeNum] = 0;
	}
	public static void initTree(int nodeNum, int left, int right) {
		segTree[nodeNum] = right - left + 1;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		initTree(nodeNum * 2, left, mid);
		initTree(nodeNum * 2 + 1, mid + 1, right);
	}
}