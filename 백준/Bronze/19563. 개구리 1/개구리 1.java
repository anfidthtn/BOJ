import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Math.abs(Long.parseLong(st.nextToken()));
		long b = Math.abs(Long.parseLong(st.nextToken()));
		long c = Long.parseLong(st.nextToken());
		if ((c & 1) == ((a + b) & 1) && a + b <= c) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}
}