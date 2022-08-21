import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int max = 0;
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			count -= Integer.parseInt(st.nextToken());
			count += Integer.parseInt(st.nextToken());
			max = Math.max(count, max);
		}
		System.out.print(Math.min(max, 10000));
	}
}