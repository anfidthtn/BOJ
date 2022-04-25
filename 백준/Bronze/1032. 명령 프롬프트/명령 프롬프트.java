import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.print(br.readLine());
			return;
		}
		String[] files = new String[N];
		for(int i = 0; i < N; i++) {
			files[i] = br.readLine();
		}
		for(int i = 0; i < files[0].length(); i++) {
			char first = files[0].charAt(i);
			for(int j = 1; j < N; j++) {
				if (first != files[j].charAt(i)) {
					System.out.print('?');
					break;
				}
				if (j == N - 1) {
					System.out.print(first);
				}
			}
		}
	}
}