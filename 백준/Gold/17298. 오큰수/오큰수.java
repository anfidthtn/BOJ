import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> stack = new Stack<>();
		for(int i = N - 1; i >= 0; i--) {
			while(!stack.isEmpty() && stack.peek() <= nums[i]) {
				stack.pop();
			}
			int temp = nums[i];
			if (!stack.isEmpty()) {
				nums[i] = stack.peek();
			}
			else {
				nums[i] = -1;
			}
			stack.add(temp);
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			sb.append(nums[i]).append(' ');
		}
		System.out.println(sb.toString());
	}
}