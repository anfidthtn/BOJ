import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder answer;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = 0;
		answer = new StringBuilder();
		move(Integer.parseInt(br.readLine()), 1, 3);
		System.out.println(count);
		System.out.print(answer.toString());
	}
	public static void move(int depth, int start, int end) {
		if (depth == 1) {
			write(start, end);
			return;
		}
		int mid = 6 - start - end;
		move(depth - 1, start, mid);
		write(start, end);
		move(depth - 1, mid, end);
	}
	public static void write(int start, int end) {
		count++;
		answer.append(start).append(" ").append(end).append("\n");		
	}
}