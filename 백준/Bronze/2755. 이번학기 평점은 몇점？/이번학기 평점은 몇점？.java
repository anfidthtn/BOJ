import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		float sum = 0;
		int total = 0;
		for (int i = 0; i < N; i++) {
			String[] strs = br.readLine().split(" ");
			total += Integer.parseInt(strs[1]);
			float temp = 0;
			if (strs[2].equals("F")) {
				continue;
			}
			temp += 4 - strs[2].charAt(0) + 'A';
			if (strs[2].charAt(1) == '-') {
				temp -= 0.3;
			} else if (strs[2].charAt(1) == '+') {
				temp += 0.3;
			}
			sum += temp * Integer.parseInt(strs[1]);
		}
		System.out.printf("%.2f", sum / total);
	}
}