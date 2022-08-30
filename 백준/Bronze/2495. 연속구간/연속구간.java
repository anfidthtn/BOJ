import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 3; i++) {
			System.out.println(len(Integer.parseInt(br.readLine())));
		}
	}
	public static int len(int num) {
		int max = 1;
		int now = 1;
		int before = num % 10;
		num /= 10;
		while(num > 0) {
			if (num % 10 == before) {
				max = Math.max(max, ++now);
			}
			else {
				before = num % 10;
				now = 1;
			}
			num /= 10;
		}
		return max;
	}
}