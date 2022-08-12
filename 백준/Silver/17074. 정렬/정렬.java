import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int FRONT_TRASH = Integer.MAX_VALUE;
	static final int BACK_TRASH = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] front = new int[N + 2];
		int[] back = new int[N + 2];
		front[0] = BACK_TRASH + 1;
		front[1] = nums[1];
		front[N + 1] = FRONT_TRASH;
		back[0] = BACK_TRASH;
		back[N] = nums[N];
		back[N + 1] = FRONT_TRASH - 1;
		for(int i = 2; i <= N; i++) {
			if (front[i - 1] > nums[i]) {
				front[i] = FRONT_TRASH;
			}
			else {
				front[i] = nums[i];
			}
		}
		for(int i = N - 1; i >= 1; i--) {
			if (nums[i] > back[i + 1]) {
				back[i] = BACK_TRASH;
			}
			else {
				back[i] = nums[i];
			}
		}
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			if (front[i - 1] <= back[i + 1]) {
				answer++;
			}
		}
		System.out.print(answer);
	}
}