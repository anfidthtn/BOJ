import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] fib = new int[41];
		fib[1] = 1;
		fib[2] = 1;
		for(int i = 3; i <= 40; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		for (int tNum = 1; tNum <= t; tNum++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				sb.append("1 0\n");
				continue;
			}
			sb.append(fib[num - 1]).append(' ').append(fib[num]).append('\n');
		}
		System.out.print(sb.toString());
	}
}