import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str= br.readLine();
		long[][] anss = new long[N + 1][5];
		anss[0][0] = 1;
		for(int i = 1; i <= N; i++) {
			switch(str.charAt(i - 1)) {
			case 'R':
				for(int j = 0; j < 5; j++) {
					anss[i][1] += anss[i - 1][j];
				}
				break;
			case 'O':
				anss[i][0] += anss[i - 1][0];
				anss[i][0] += anss[i - 1][2];
				anss[i][0] += anss[i - 1][3];
				anss[i][0] += anss[i - 1][4];
				anss[i][2] = anss[i - 1][1];
				break;
			case 'C':
				anss[i][0] += anss[i - 1][0];
				anss[i][0] += anss[i - 1][1];
				anss[i][0] += anss[i - 1][3];
				anss[i][0] += anss[i - 1][4];
				anss[i][3] = anss[i - 1][2];
				break;
			case 'K':
				anss[i][0] += anss[i - 1][0];
				anss[i][0] += anss[i - 1][1];
				anss[i][0] += anss[i - 1][2];
				anss[i][4] = anss[i - 1][3];
				anss[i][0] += anss[i - 1][4];
				break;
			default:
				for(int j = 0; j < 5; j++) {
					anss[i][0] += anss[i - 1][j];
				}
				break;
			}
			for(int j = 0; j < 5; j++) {
				anss[i][j] += anss[i - 1][j];
				anss[i][j] %= 1000000007;
			}
		}
		System.out.println(anss[N][4]);
	}
}