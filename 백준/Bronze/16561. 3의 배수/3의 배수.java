import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) / 3;
		int count = 0;
		for(int a = 1; a <= N - 2; a++) {
			for(int b = 1; b <= N - 2; b++) {
				if (a + b + 1 > N) {
					break;
				}
				for(int c = 1; c <= N - 2; c++) {
					if (a + b + c >= N) {
						if (a + b + c == N) {
							count++;
						}
						break;
					}
				}
			}
		}
		System.out.println(count);
	}
}