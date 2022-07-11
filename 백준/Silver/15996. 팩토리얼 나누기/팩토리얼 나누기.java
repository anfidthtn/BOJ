import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		System.out.println(getPow(N, A));
	}
	public static int getPow(int n, int p) {
		int sub = 0;
		while(n > 0) {
			sub += n /= p;
		}
		return sub;
	}
}