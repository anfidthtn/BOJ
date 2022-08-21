import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int result = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken())
					+ Integer.parseInt(st.nextToken());
			if (result == 0) {
				System.out.println("does not matter");
			} else if (result < 0) {
				System.out.println("advertise");
			} else {
				System.out.println("do not advertise");
			}
		}
	}
}