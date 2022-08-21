import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		int min = -1;
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		for(int i = n; i >= m; i--) {
			if (i == 1) {
				continue;
			}
			boolean ok = true;
			for(int j = 2; j * j <= i; j++) {
				if (i % j == 0) {
					ok = false;
					break;
				}
			}
			if (ok) {
				sum += i;
				min = i;
			}
		}
		if (min == -1) {
			System.out.print(-1);
		}
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}