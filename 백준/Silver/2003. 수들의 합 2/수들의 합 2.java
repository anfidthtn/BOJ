import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
		}
		int left = 1;
		int right = 1;
		int count = 0;
		while(left <= N && right <= N) {
			if (left > right) {
				right++;
			}
			else if (nums[right] - nums[left - 1] == M) {
				left++;
				count++;
			}
			else if (nums[right] - nums[left - 1] < M) {
				right++;
			}
			else {
				left++;
			}
		}
		System.out.println(count);
	}
}