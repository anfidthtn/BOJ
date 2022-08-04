import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a + b == 0) {
				break;
			}
			int sum = 0;
			int count = 0;
			while(a > 0 || b > 0) {
				sum += a % 10 + b % 10;
				a /= 10;
				b /= 10;
				if (sum >= 10) {
					count++;
				}
				sum /= 10;
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}