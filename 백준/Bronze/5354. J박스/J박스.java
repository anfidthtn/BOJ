import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			for(int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == 1 || j == 1 || i == N || j == N) {
						sb.append("#");
					}
					else {
						sb.append("J");
					}
				}
				sb.append("\n");
			}
			if (tNum != t) {
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}