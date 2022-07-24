import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int C = Integer.parseInt(br.readLine());
			sb.append(C / 25).append(" ");
			C %= 25;
			sb.append(C / 10).append(" ");
			C %= 10;			
			sb.append(C / 5).append(" ");
			C %= 5;
			sb.append(C).append("\n");
			
		}
		System.out.print(sb.toString());
	}
}