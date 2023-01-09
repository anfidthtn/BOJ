import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int before = -1;
		int num;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(st.nextToken());
			if (before > num) {
				System.out.print("no");
				return;
			}
			before = num;
		}
		System.out.print("yes");
	}
}