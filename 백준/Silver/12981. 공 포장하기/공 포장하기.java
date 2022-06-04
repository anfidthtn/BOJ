import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int count = 0;
		count += r / 3;
		count += g / 3;
		count += b / 3;
		r %= 3;
		g %= 3;
		b %= 3;
		
		int count2 = 0;
		if (r > 0) {
			count2++;
		}
		if (g > 0) {
			count2++;
		}
		if (b > 0) {
			count2++;
		}
		count += Math.min(count2, Math.max(Math.max(r, g), b));
		System.out.print(count);
	}
}