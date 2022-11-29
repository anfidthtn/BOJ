import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] map = { //
			{ '@', ' ', ' ', ' ', '@' }, //
			{ '@', '@', '@', '@', '@' }, //
			{ '@', ' ', ' ', ' ', '@' }, //
			{ '@', '@', '@', '@', '@' }, //
			{ '@', ' ', ' ', ' ', '@' } //
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5 * N; i++) {
			for (int j = 0; j < 5 * N; j++) {
				sb.append(map[i / N][j / N]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}