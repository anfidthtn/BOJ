import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int idx;
		int value;
		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Node> stack = new Stack<>();
		long answer = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty() && stack.peek().value <= num) {
				answer += i - stack.pop().idx + 1;
			}
			if (!stack.isEmpty()) {
				answer += i - stack.peek().idx + 1;
			}
			stack.add(new Node(i, num));
		}
		System.out.print(answer);
	}
}