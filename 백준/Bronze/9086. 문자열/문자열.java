import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			String str = br.readLine();
			sb.append(str.charAt(0));
			sb.append(str.charAt(str.length() - 1));
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}