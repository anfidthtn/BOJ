import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int d = 0;
		int p = 0;
		for (int i = 0; i < N; i++) {
			switch (br.readLine().charAt(0)) {
			case 'D':
				d++;
				break;
			default:
				p++;
			}
			if (Math.abs(d - p) >= 2) {
				break;
			}
		}
		System.out.print(d + ":" + p);
	}
}