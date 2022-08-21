import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int a = 0;
		int b = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tempa = Integer.parseInt(st.nextToken());
			int tempb = Integer.parseInt(st.nextToken());
			if (tempa < tempb) {
				b++;
			}
			if (tempb < tempa) {
				a++;
			}
		}
		System.out.print(a + " " + b);
	}
}