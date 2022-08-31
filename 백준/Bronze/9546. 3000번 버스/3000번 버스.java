import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int K = Integer.parseInt(br.readLine());
			long person = 0;
			for (int k = 0; k < K; k++) {
				person += 1;
				person *= 2;
			}
			sb.append(person / 2).append("\n");
		}
		System.out.print(sb.toString());
	}
}