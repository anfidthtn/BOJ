import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		Map<Character, Character> pair = new HashMap<>();
		pair.put('(', ')');
		pair.put('{', '}');
		pair.put('[', ']');
		tCase: while (!(line = br.readLine()).equals(".")) {
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < line.length() - 1; i++) {
				switch (line.charAt(i)) {
				case '(':
				case '{':
				case '[':
					stack.add(line.charAt(i));
					break;
				case ')':
				case '}':
				case ']':
					if (stack.isEmpty()) {
						sb.append("no\n");
						continue tCase;
					} else if (line.charAt(i) != pair.get(stack.pop())) {
						sb.append("no\n");
						continue tCase;
					}
					break;
				}
			}
			if (stack.isEmpty()) {
				sb.append("yes\n");
			}
			else {
				sb.append("no\n");
			}
		}
		System.out.print(sb.toString());
	}
}