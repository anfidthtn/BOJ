import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] nums;
	static boolean found;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		found = false;
		check(0);
	}
	public static void check(int idx) {
		if (found) {
			return;
		}
		if (idx == N) {
			for(int i = 0 ; i < N; i++) {
				System.out.print(nums[i]);
				found = true;
			}
			return;
		}
		for(int i = 1; i <= 3; i++) {
			nums[idx] = i;
			boolean able = true;
			for(int checkPoint = idx - 1; checkPoint >= 0; checkPoint--) {
				if (idx > 2 * checkPoint + 1) {
					break;
				}
				boolean same = true;
				int a = checkPoint;
				int b = idx;
				while(b > checkPoint) {
					if (nums[a] != nums[b]) {
						same = false;
						break;
					}
					a--;
					b--;
				}
				if (same) {
					able = false;
					break;
				}
			}
			if (able) {
				check(idx + 1);
			}
		}
	}
}