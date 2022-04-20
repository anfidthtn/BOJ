import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String nums = br.readLine();
		boolean[] erase = new boolean[N];
		Stack<Integer> stack = new Stack<>();
		int eCount = 0;
		int searchIdx = 0;
		while (eCount < K && searchIdx < N) {
			if (stack.isEmpty()) {
				stack.add(searchIdx++);
			} else {
				if (nums.charAt(stack.peek()) < nums.charAt(searchIdx)) {
					erase[stack.pop()] = true;
					eCount++;
				} else {
					stack.add(searchIdx++);
				}
			}
		}
		while (eCount < K) {
			erase[stack.pop()] = true;
			eCount++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if (!erase[i]) {
				sb.append(nums.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}
}