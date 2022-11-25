import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] minTree;
	static int validCount;
	static int[][] querys;
	static int Q;
	static int MOD;
	static StringBuilder sb;
	static Stack<Integer> stack;
	static final int NAN = -100;
	static Stack<Integer>[] maxIdxStacks;
	static int nowSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		MOD = Integer.parseInt(st.nextToken());
		querys = new int[Q][2];
		stack = new Stack<>();
		sb = new StringBuilder();
		nowSize = 0;
		int maxCount = 0;
		int tempCount = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			querys[i][0] = Integer.parseInt(st.nextToken());
			if (querys[i][0] == 1) {
				querys[i][1] = Integer.parseInt(st.nextToken()) % MOD;
				tempCount++;
				maxCount = Math.max(maxCount, tempCount);
			} else if (querys[i][0] == 2) {
				tempCount = Math.max(tempCount - 1, 0);
			}
		}
		if (maxCount < MOD) {
			for (int i = 0; i < Q; i++) {
				if (querys[i][0] == 3) {
					sb.append("-1\n");
				}
			}
			System.out.print(sb.toString());
			return;
		}

		minTree = new int[MOD * 4];
		maxIdxStacks = new Stack[MOD];
		for(int i = 0; i < MOD; i++) {
			maxIdxStacks[i] = new Stack<>();
		}
		Arrays.fill(minTree, NAN);
		for(int i = 0; i < Q; i++) {
			switch(querys[i][0]) {
			case 1:
				process1(querys[i][1]);
				break;
			case 2:
				process2();
				break;
			case 3:
				if (minTree[1] < 0) {
					sb.append("-1\n");
				}
				else {
					sb.append(nowSize - minTree[1]).append("\n");
				}
			}
		}
		System.out.print(sb.toString());
	}
	public static void process1(int num) {
		maxIdxStacks[num].add(nowSize);
		stack.add(num);
		changeTree(1, 1, MOD, num + 1, nowSize);
		nowSize++;
	}
	public static void process2() {
		if (stack.isEmpty()) {
			return;
		}
		int num = stack.pop();
		maxIdxStacks[num].pop();
		if (maxIdxStacks[num].isEmpty()) {
			changeTree(1, 1, MOD, num + 1, NAN);
		}
		else {
			changeTree(1, 1, MOD, num + 1, maxIdxStacks[num].peek());
		}
		nowSize--;
	}
	public static void changeTree(int treeIdx, int leftIdx, int rightIdx, int targetIdx, int value) {
		if (targetIdx < leftIdx || rightIdx < targetIdx) {
			return;
		}
		if (leftIdx == rightIdx) {
			minTree[treeIdx] = value;
			return;
		}
		int mid = (leftIdx + rightIdx) >> 1;
		if (targetIdx <= mid) {
			changeTree(treeIdx * 2, leftIdx, mid, targetIdx, value);
		}
		else {
			changeTree(treeIdx * 2 + 1, mid + 1, rightIdx, targetIdx, value);
		}
		minTree[treeIdx] = Math.min(minTree[treeIdx * 2], minTree[treeIdx * 2 + 1]);
	}

}