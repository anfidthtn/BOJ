import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long max = 0;
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			max = Math.max(num, max);
		}
		sum -= max;
		if (max >= sum) {
			System.out.println(max - sum);
		}
		else {
			if ((max + sum) % 2 == 0) {
				System.out.println(0);
			}
			else {
				System.out.println(1);
			}
		}
	}
}