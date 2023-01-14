import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] wins = new boolean[N + 10];
		wins[2] = true;
		for(int i = 4; i <= N; i++) {
			wins[i] = !(wins[i - 1] & wins[i - 3]);
		}
		System.out.print(wins[N]?"SK":"CY");
	}
}