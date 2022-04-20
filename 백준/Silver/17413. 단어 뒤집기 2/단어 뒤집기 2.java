import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String S = br.readLine().trim();
		Deque<Character> deque = new LinkedList<>();
		boolean isTag = false;
		for(int i = 0; i < S.length(); i++) {
			switch(S.charAt(i)) {
			case '<':
				while(!deque.isEmpty()) {
					sb.append(deque.pollLast());
				}
				sb.append('<');
				isTag = true;
				break;
			case '>':
				isTag = false;
				sb.append('>');
				break;
			case ' ':
				if (isTag) sb.append(' ');
				else {
					while(!deque.isEmpty()) {
						sb.append(deque.pollLast());
					}
					sb.append(' ');
				}
				break;
			default:
				if (isTag) sb.append(S.charAt(i));
				else {
					deque.addLast(S.charAt(i));
				}
			}
		}
		while(!deque.isEmpty()) {
			sb.append(deque.pollLast());
		}
		System.out.print(sb.toString());
	}
}