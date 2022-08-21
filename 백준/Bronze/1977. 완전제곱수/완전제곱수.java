import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int min = 0;
		int sum = 0;
		for(int i = 100; i >= 1; i--) {
			if (a <= i * i && i * i <= b) {
				min = i * i;
				sum += i * i;
			}
		}
		if (min == 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}