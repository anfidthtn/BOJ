import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			deque.addLast(i);
		}
		for(int i = 0; i < N; i++) {
			System.out.print(deque.pollFirst());
			System.out.print(" ");
			if (!deque.isEmpty()) {
				deque.addLast(deque.pollFirst());
			}
		}
	}
}