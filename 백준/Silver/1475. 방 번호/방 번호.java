import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int[] set = new int[] {
			1,
			1,
			1,
			1,
			1,
			1,
			2,
			1,
			1
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int[] myCard = new int[9];
		int N = Integer.parseInt(br.readLine());
		while(N > 0) {
			int num = N % 10;
			if (num == 9) {
				num = 6;
			}
			if (myCard[num] == 0) {
				count++;
				for(int i = 0; i < 9; i++) {
					myCard[i] += set[i];
				}
			}
			myCard[num] --;
			N /= 10;
		}
		System.out.print(count);
	}
}