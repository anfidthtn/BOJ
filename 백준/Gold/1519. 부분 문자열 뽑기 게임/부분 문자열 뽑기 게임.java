import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] results;
	static int[] answers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		results = new int[N + 1];
		answers = new int[N + 1];
		Arrays.fill(answers, 1 << 30);
		for(int i = 10; i <= N; i++) {
			game(i);
		}
		System.out.print(game(N) == -1 ? -1 : answers[N]);
	}

	public static int game(int num) {
		if (num < 10) {
			return -1;
		}
		if (results[num] != 0) {
			return results[num];
		}
		String numS = String.valueOf(num);
		int res = -1;
		for (int i = 0; i < numS.length(); i++) {
			for (int j = i; j < numS.length(); j++) {
				int sub = 0;
				for(int k = i; k <= j; k++) {
					sub *= 10;
					sub += numS.charAt(k) - '0';
				}
				if (sub != num && sub != 0 && game(num - sub) == -1) {
					answers[num] = Math.min(answers[num], sub);
					res = 1;
				}
			}
		}
		return results[num] = res;
	}
}