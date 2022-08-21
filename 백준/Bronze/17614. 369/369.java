import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			int num = i;
			while(num > 0) {
				int digit = num % 10;
				if (digit == 3 || digit == 6 || digit == 9) {
					count++;
				}
				num /= 10;
			}
		}
		System.out.println(count);
	}
}