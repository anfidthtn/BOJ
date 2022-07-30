import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		long answer = 0;
		long left = 1;
		long right = 10_000_000_000l;
		while(left <= right) {
			long mid = (left + right) >> 1;
			long count = 0;
			for(int i = 1; i <= N && i <= mid; i++) {
				count += Math.min(N, mid / i);
			}
			if (K <= count) {
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