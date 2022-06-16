import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int a = 100;
		int b = 100;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int aa = Integer.parseInt(st.nextToken());
			int bb = Integer.parseInt(st.nextToken());
			if (aa < bb) {
				a -= bb;
			}
			else if (bb < aa) {
				b -= aa;
			}
		}
		System.out.println(a);
		System.out.println(b);
	}
}