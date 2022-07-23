import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int len = 0;
		int left = 0;
		int right = -1;
		int count = 0;
		while(right < N) {
			if (count <= K) {
				len = len > right - left + 1 - count ? len : right - left + 1 - count;
			}
			if (left > right) {
				count += get(++right);
			}
			else if (count <= K) {
				count += get(++right);
			}
			else {
				count -= get(left++);
			}
		}
		System.out.println(len);
	}
	public static int get(int idx) {
		if ((nums[idx] & 1) == 0) {
			return 0;
		}
		else {
			return 1;
		}
	}
}