import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int idx = 1;
		int[] nums = new int[1001];
		for(int i = 1; i <= 1000 && idx <= 1000; i++) {
			for(int j = 1; j <= i && idx <= 1000; j++) {
				nums[idx++] = i;
			}
		}
		int sum = 0;
		for(int i = A; i <= B; i++) {
			sum += nums[i];
		}
		System.out.println(sum);
	}
}