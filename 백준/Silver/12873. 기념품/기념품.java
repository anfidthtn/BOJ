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
		for(int t = 1; t < N; t++) {
			int t3 = power3(t, deque.size());
			for(int i = 1; i < t3; i++) {
				deque.addLast(deque.pollFirst());
			}
			deque.pollFirst();
		}
		System.out.println(deque.peek());
	}
	public static int power3(int num, int N) {
		int result = 1;
		for(int i = 0; i < 3; i++) {
			result *= num;
			result += N - 1;
			result %= N;
			result += 1;
		}
		return result;
	}
}