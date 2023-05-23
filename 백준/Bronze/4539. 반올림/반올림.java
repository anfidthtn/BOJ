import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int	n	=	Integer.parseInt(br.readLine());
			int cmp = 10;
			while (n >= cmp) {
				if (n % cmp >= cmp / 2) {
					n -= n % cmp;
					n += cmp;
				}
				else {
					n -= n % cmp;
				}
				cmp *= 10;
			}
			sb.append(n).append("\n");
		}
		System.out.print(sb.toString());
	}
}