import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class AnswerSlot implements Comparable<AnswerSlot>{
		int[] answer;
		public AnswerSlot(int[] answer) {
			this.answer = answer;
		}

		@Override
		public int compareTo(AnswerSlot o) {
			for(int i = 0; i < 7; i++) {
				if (this.answer[i] != o.answer[i]) {
					return this.answer[i] - o.answer[i];
				}
			}
			return 0;
		}
		
	}
	static final int NAN = -1_000_000_000;
	static int N;
	static int[] nums;
	static boolean[] visited;
	static int[] slot;
	static Set<AnswerSlot> resultSet;
	static int[][] checks = {
			{0, 1, 3},
			{0, 2, 5},
			{0, 4, 6},
			{1, 2, 4},
			{1, 5, 6},
			{2, 3, 6}
	};
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			resultSet = new TreeSet<>();
			slot = new int[7];
			for (int i = 0; i < 7; i++) {
				slot[i] = NAN;
			}
			check(0, 0);
			sb.append(resultSet.size()).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void check(int slotIdx, int insertCount) {
		if (slotIdx == 7) {
			if (insertCount == N) {
				slotCheck();
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			slot[slotIdx] = nums[i];
			check(slotIdx + 1, insertCount + 1);
			visited[i] = false;
			slot[slotIdx] = NAN;
		}
		check(slotIdx + 1, insertCount);
	}
	public static void slotCheck() {
		int[] slots = new int[7];
		for(int i = 0; i < 7; i++) {
			slots[i] = slot[i];
		}
		int numCount = N;
		do {
			for(int[] check : checks) {
				if (slots[check[0]] != NAN && slots[check[1]] != NAN) {
					if (slots[check[2]] != NAN) {
						if (slots[check[2]] != slots[check[0]] + slots[check[1]]) {
							return;
						}
					}
					else {
						slots[check[2]] = slots[check[0]] + slots[check[1]];
						numCount++;
					}
				}
				if (slots[check[1]] != NAN && slots[check[2]] != NAN) {
					if (slots[check[0]] != NAN) {
						if (slots[check[0]] != slots[check[2]] - slots[check[1]]) {
							return;
						}
					}
					else {
						slots[check[0]] = slots[check[2]] - slots[check[1]];
						numCount++;
					}
				}
				if (slots[check[0]] != NAN && slots[check[2]] != NAN) {
					if (slots[check[1]] != NAN) {
						if (slots[check[1]] != slots[check[2]] - slots[check[0]]) {
							return;
						}
					}
					else {
						slots[check[1]] = slots[check[2]] - slots[check[0]];
						numCount++;
					}
				}
			}
			
		} while(numCount < 7);
		for(int[] check : checks) {
			if (slots[check[0]] != NAN && slots[check[1]] != NAN) {
				if (slots[check[2]] != NAN) {
					if (slots[check[2]] != slots[check[0]] + slots[check[1]]) {
						return;
					}
				}
				else {
					slots[check[2]] = slots[check[0]] + slots[check[1]];
				}
			}
			if (slots[check[1]] != NAN && slots[check[2]] != NAN) {
				if (slots[check[0]] != NAN) {
					if (slots[check[0]] != slots[check[2]] - slots[check[1]]) {
						return;
					}
				}
				else {
					slots[check[0]] = slots[check[2]] - slots[check[1]];
				}
			}
			if (slots[check[0]] != NAN && slots[check[2]] != NAN) {
				if (slots[check[1]] != NAN) {
					if (slots[check[1]] != slots[check[2]] - slots[check[0]]) {
						return;
					}
				}
				else {
					slots[check[1]] = slots[check[2]] - slots[check[0]];
				}
			}
		}
		if (slots[0] < 1 || slots[1] < 1 || slots[2] < 1) {
			return;
		}
		if (slots[0] > slots[1] || slots[1] > slots[2]) {
			return;
		}
		resultSet.add(new AnswerSlot(slots));
	}
}