import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] sums = new int[8];
		for(int i = 7; i >= 0; i--) {
			sums[i] = a % 10 + b % 10;
			a /= 10;
			b /= 10;
		}
		boolean find = false;
		for(int i = 0; i < 8; i++) {
			if(!find && sums[i] > 0) {
				find = true;
			}
			if (find || i == 7) {
				System.out.print(sums[i]);
			}
		}
	}
}