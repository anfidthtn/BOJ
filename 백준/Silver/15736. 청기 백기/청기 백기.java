import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sqrtN = (int) Math.sqrt(N);
		if ((sqrtN + 1) * (sqrtN + 1) < N) {
			sqrtN++;
		}
		System.out.println(sqrtN);
	}
}