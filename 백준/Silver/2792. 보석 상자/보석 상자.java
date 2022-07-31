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
		int[] nums = new int[M];
		for(int i = 0; i < M; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		int answer = 0;
		int left = 1;
		int right = 1_000_000_000;
		while(left <= right) {
			int mid = (left + right) >> 1;
			long count = 0;
			for(int i = 0; i < M; i++) {
				count += (nums[i] + mid - 1) / mid;
			}
			if (count <= N) {
				answer = mid;
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}
}