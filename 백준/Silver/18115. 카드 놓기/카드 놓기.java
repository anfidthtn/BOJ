import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] skills = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Deque<Integer> deque = new LinkedList<>();
		for (int cardNum = skills.length - 1; cardNum >= 0; cardNum--) {
			switch(skills[cardNum]) {
			case 1:
				deque.addFirst(N - cardNum);
				break;
			case 2:
				int temp = deque.pollFirst();
				deque.addFirst(N - cardNum);
				deque.addFirst(temp);
				break;
			case 3:
				deque.addLast(N - cardNum);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!deque.isEmpty()) {
			sb.append(deque.pollFirst()).append(" ");
		}
		System.out.print(sb.toString());
	}
}