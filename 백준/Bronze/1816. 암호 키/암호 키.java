import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			long s = Long.parseLong(br.readLine());
			for(int i = 2; i <= 1000000 && s > 1; i++) {
				if(s % i == 0) {
					sb.append("NO\n");
					s = 0;
					break;
				}
			}
			if(s > 0) {
				sb.append("YES\n");
			}
		}
		System.out.print(sb.toString());
	}
}