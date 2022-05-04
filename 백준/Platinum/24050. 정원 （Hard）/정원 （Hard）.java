import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			result += (Integer.parseInt(st.nextToken()) & getStatus(M - 1, N - i));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			result += (Integer.parseInt(st.nextToken()) & getStatus(N - 1, M - i));
		}
		System.out.println(result & 1);
	}
	public static int getStatus(int a, int b) {
		if (a == 0 && b == 0) {
			return 1;
		}
		int ta = a;
		int tb = b;
		int count = 0;
		while(ta > 0 && tb > 0) {
			ta >>= 1;
			tb >>= 1;
			count++;
		}
		if (ta == tb) {
			return 0;
		}
		else if (ta > 0) {
			return getStatus(a - (ta << count), b);
		}
		else {
			return getStatus(a, b - (tb << count));
		}
	}
}