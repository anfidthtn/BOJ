import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int num = 1;
		while (N > 0) {
			if (check(num, L)) {
				N--;
			}
			num++;
		}
		System.out.println(num - 1);
	}

	public static boolean check(int num, int L) {
		while (num > 0) {
			if (num % 10 == L) {
				return false;
			}
			num /= 10;
		}
		return true;
	}
}