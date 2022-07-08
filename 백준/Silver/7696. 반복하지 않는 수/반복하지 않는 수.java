import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] nums;
	static int idx;
	static final int MAXNUM = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nums = new int[MAXNUM + 1];
		idx = 1;

		for (int digit = 1; digit <= 8; digit++) {
			boolean[] used = new boolean[10];
			int[] numArr = new int[digit];
			for (int first = 1; first <= 9 && idx <= MAXNUM; first++) {
				used[first] = true;
				numArr[0] = first;
				dfs(numArr, used, digit, 1);				
				used[first] = false;
			}
		}
		while(true) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				break;
			}
			System.out.println(nums[num]);
		}
	}

	public static void dfs(int[] numArr, boolean[] used, int digit, int nextIdx) {
		if (idx > MAXNUM) {
			return;
		}
		if (nextIdx == digit) {
			int num = 0;
			for(int i = 0; i < digit; i++) {
				num *= 10;
				num += numArr[i];
			}
			nums[idx++] = num;
			return;
		}
		for (int num = 0; num <= 9; num++) {
			if (used[num]) {
				continue;
			}
			used[num] = true;
			numArr[nextIdx] = num;
			dfs(numArr, used, digit, nextIdx + 1);
			used[num] = false;
		}
	}
}