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
			int count = 0;
			while(N > 0) {
				if (N % 2 == 1) {
					sb.append(count).append(" ");
				}
				N >>= 1;
				count++;
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}