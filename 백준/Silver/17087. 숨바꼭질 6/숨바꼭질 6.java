import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int res = Math.abs(S - Integer.parseInt(st.nextToken()));
		for(int i = 1; i < N; i++) {
			res = getGCD(res, Math.abs(S - Integer.parseInt(st.nextToken())));
		}
		System.out.println(res);
	}
	public static int getGCD(int x, int y) {
		if (y == 0) {
			return x;
		}
		return getGCD(y, x % y);
	}
}