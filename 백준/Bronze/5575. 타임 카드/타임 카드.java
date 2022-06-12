import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int total = 0;
			total -= 3600 * Integer.parseInt(st.nextToken());
			total -= 60 * Integer.parseInt(st.nextToken());
			total -= Integer.parseInt(st.nextToken());
			total += 3600 * Integer.parseInt(st.nextToken());
			total += 60 * Integer.parseInt(st.nextToken());
			total += Integer.parseInt(st.nextToken());
			System.out.print(String.format("%d %d %d\n", total / 3600, (total % 3600) / 60, total % 60));
		}
	}
}