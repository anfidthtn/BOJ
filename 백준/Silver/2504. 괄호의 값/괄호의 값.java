import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		Stack<Integer[]> numStack = new Stack<>();
		numStack.push(new Integer[]{0, 1});
		for (int i = 0; i < s.length(); i++) {
			switch(s.charAt(i)) {
			case '(':
			case '[':
				stack.push(s.charAt(i));
				numStack.push(new Integer[] {0, 1});
				break;
			default:
				if (stack.isEmpty()) {
					System.out.print(0);
					return;
				}
				char top = stack.pop();
				if (top == '(' && s.charAt(i) != ')') {
					System.out.print(0);
					return;
				}
				if (top == '[' && s.charAt(i) != ']') {
					System.out.print(0);
					return;
				}
				Integer[] temp = numStack.pop();
				int mul = s.charAt(i) == ']' ? 3 : 2;
				numStack.peek()[0] += mul * (temp[0] + temp[1]);
				numStack.peek()[1] = 0;
			}
		}
		if (!stack.isEmpty()) {
			System.out.print(0);
			return;
		}
		System.out.print(numStack.peek()[0]);
	}
}
