import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			if (p == 0) {
				break;
			}
			System.out.println(new BigInteger(st.nextToken(), p).remainder(new BigInteger(st.nextToken(), p)).toString(p));
		}
	}
}