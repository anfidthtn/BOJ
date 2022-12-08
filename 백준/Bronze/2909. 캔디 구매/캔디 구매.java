import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long cover = 1;
		for(int i = 0; i < K; i++) {
			cover *= 10;
		}
		long mod = C % cover;
		C /= cover;
		C *= cover;
		if (mod * 10 >= 5 * cover) {
			C += cover;
		}
		System.out.print(C);
	}
}