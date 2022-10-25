import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int straight;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int rowcount = 0;
		int colcount = 0;
		for(int i = 0; i < N; i++, straight = 0) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					straight++;
				}
				else {
					if (straight >= 2) {
						rowcount++;
					}
					straight = 0;
				}
			}
			if (straight >= 2) {
				rowcount++;
			}
		}
		for(int j = 0; j < N; j++, straight = 0) {
			for(int i = 0; i < N; i++) {
				if (map[i][j] == '.') {
					straight++;
				}
				else {
					if (straight >= 2) {
						colcount++;
					}
					straight = 0;
				}
			}
			if (straight >= 2) {
				colcount++;
			}
		}
		System.out.print(rowcount + " " + colcount);
	}
}