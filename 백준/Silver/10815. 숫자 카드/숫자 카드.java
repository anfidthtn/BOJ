import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(nums);
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			int answer = 0;
			int left = 0;
			int right = N - 1;
			while(left <= right) {
				int mid = (left + right) >> 1;
				if (nums[mid] == target) {
					answer = 1;
					break;
				}
				else if (nums[mid] > target) {
					right = mid - 1;
				}
				else {
					left = mid + 1;
				}
			}
			sb.append(answer).append(" ");
		}
		System.out.print(sb.toString());
	}
}