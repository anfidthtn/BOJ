import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int sum = 0;
		while(num > 0) {
			sum += (num % 10) * (num % 10) * (num % 10) * (num % 10) * (num % 10);
			num /= 10;
		}
		System.out.println(sum);
	}
}