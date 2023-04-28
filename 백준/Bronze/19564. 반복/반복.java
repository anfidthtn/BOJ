import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][][] 그림들;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char before = 'z';
		char[] target = br.readLine().toCharArray();
		int ans = 0;
		for(char t : target) {
			if (before >= t) {
				ans++;
			}
			before = t;
		}
		System.out.println(ans);
	}
}