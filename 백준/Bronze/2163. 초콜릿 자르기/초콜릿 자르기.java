import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		answer = 0;
		div(N, M);
		System.out.println(answer);
	}
	public static void div(int a, int b) {
		if (a == 1 && b == 1) {
			return;
		}
		answer++;
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		div(a / 2, b);
		div((a + 1) / 2, b);
	}
}