import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int count = 0;
		for (int tNum = 1; tNum <= t; tNum++) {
			Stack<Character> stack = new Stack<>();
			String str = br.readLine();
			for(int i = 0; i < str.length(); i++) {
				if (stack.isEmpty()) {
					stack.add(str.charAt(i));
				}
				else {
					if (stack.peek() == str.charAt(i)) {
						stack.pop();
					}
					else {
						stack.add(str.charAt(i));
					}
				}
			}
			if (stack.isEmpty()) {
				count++;
			}
		}
		System.out.println(count);
	}
}