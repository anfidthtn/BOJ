import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] counts = new int[111111];
		counts[0] = 1000;
		int[] nums = new int[222222];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = -1;
		int answer = 0;
		while (left < N && right < N) {
			if (counts[nums[right + 1]] < K) {
				counts[nums[++right]]++;
				answer = Math.max(answer, right - left + 1);
			} else {
				counts[nums[left++]]--;
			}
		}
		System.out.print(answer);
	}
}