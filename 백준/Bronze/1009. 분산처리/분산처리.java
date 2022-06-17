import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb.append(getIdx(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int getIdx(int num, int power) {
		int result = 1;
		while(power > 0) {
			if (power % 2 == 1) {
				result *= num;
				result %= 10;
			}
			power >>= 1;
			num *= num;
			num %= 10;
		}
		return result == 0 ? 10 : result;
	}
}