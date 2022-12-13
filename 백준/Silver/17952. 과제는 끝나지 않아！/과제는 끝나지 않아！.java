import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	class Node {
		int time;
		int score;

		public Node(int score, int time) {
			super();
			this.time = time;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Node> stack = new Stack<>();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (!stack.isEmpty()) {
				if (--stack.peek().time == 0) {
					answer += stack.pop().score;
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				stack.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
		}
		if (!stack.isEmpty()) {
			if (--stack.peek().time == 0) {
				answer += stack.pop().score;
			}
		}
		System.out.print(answer);
	}
}