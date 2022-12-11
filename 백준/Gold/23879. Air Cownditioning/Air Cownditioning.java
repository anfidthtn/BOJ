import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Stack<Integer> stack;
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		stack = new Stack<>();
		answer = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st1.nextToken()) - Integer.parseInt(st2.nextToken());
			while(!stack.isEmpty() && stack.peek() * num <= 0) {
				stack.pop();
			}
			if(!stack.isEmpty() && Math.abs(stack.peek()) - Math.abs(num) >= 0) {
				while (!stack.isEmpty() && Math.abs(stack.peek()) - Math.abs(num) >= 0) {
					stack.pop();
				}
				stack.add(num);
			}
			else {
				if (!stack.isEmpty()) {
					answer += Math.abs(num - stack.peek());
				}
				else {
					answer += Math.abs(num);
				}
				stack.add(num);
			}
		}
		System.out.print(answer);
	}
}