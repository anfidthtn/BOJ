import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N < 4) {
			System.out.println(4);
			return;
		}
		int w = 1;
		int h = 1;
		while (w * h < N) {
			w++;
			if (w * h < N)
				h++;
		}
		System.out.println(2 * (w + h - 2));
	}
}