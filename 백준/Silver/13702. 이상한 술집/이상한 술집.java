import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		long answer = 0;
		long left = 1;
		long right = Integer.MAX_VALUE;
		while(left <= right) {
			long mid = (left + right) >> 1;
			int count = 0;
			for(int i = 0; i < N; i++) {
				count += nums[i] / mid;
			}
			if (count >= K) {
				answer = mid;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}
}