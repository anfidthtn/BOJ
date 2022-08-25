import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int count;
	static int N;
	static int K;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[20];
		check(0, 0);
		if (count < K) {
			System.out.println(-1);
		}
	}
	public static void check(int idx, int sum) {
		if (count == K) {
			return;
		}
		if (sum > N) {
			return;
		}
		if (sum == N) {
			if (++count == K) {
				for(int i = 0; i < idx; i++) {
					System.out.print(nums[i]);
					if (i != idx - 1) {
						System.out.print('+');
					}
				}
			}
			return;
		}
		for(int i = 1; i <= 3; i++) {
			nums[idx] = i;
			check(idx + 1, sum + i);
		}
	}
	
}