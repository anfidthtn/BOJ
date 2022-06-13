import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Double a = Double.parseDouble(st.nextToken());
		Double b = Double.parseDouble(st.nextToken());
		if (a * (100 - b) / 100 < 100) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
}