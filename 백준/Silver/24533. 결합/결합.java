import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long result = 0;
		long a = 0;
		long b = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ai = Integer.parseInt(st.nextToken());
			int bi = Integer.parseInt(st.nextToken());
			result += b * ai;
			result += a * bi;
			a += ai;
			b += bi;
		}
		System.out.println(result);
	}
}