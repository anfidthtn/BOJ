import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken()) - 1;
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		int answer = 0;
		int left = 1;
		int right = 1_000_000_000;
		while(left <= right) {
			int mid = (left + right) >> 1;
			int count = 0;
			int last = nums[0];
			for(int i = 1; i < N; i++) {
				if (nums[i] - last >= mid) {
					count++;
					last = nums[i];
				}
			}
			if (count < C) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
				answer = mid;
			}
		}
		System.out.println(answer);
	}
}