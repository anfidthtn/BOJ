import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final Long[] rev = new Long[] { //
			0L, // 0
			1L, // 1
			2L, // 2
			-1L, // 3
			-1L, // 4
			5L, // 5
			9L, // 6
			-1L, // 7
			8L, // 8
			6L, // 9
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long N = Long.parseLong(br.readLine());
		if (N == 1) {
			System.out.println("no");
			return;
		}
		Long temp = N;
		Long Nrev = 0L;
		while(temp > 0) {
			Nrev *= 10;
			if (rev[(int) (temp % 10)] == -1) {
				System.out.println("no");
				return;
			}
			Nrev += rev[(int) (temp % 10)];
			temp /= 10;
		}
		for(Long p = 2L; p * p <= N; p++) {
			if (N % p == 0) {
				System.out.println("no");
				return;
			}
		}
		for(Long p = 2L; p * p <= Nrev; p++) {
			if (Nrev % p == 0) {
				System.out.println("no");
				return;
			}
		}
		System.out.println("yes");
	}
}