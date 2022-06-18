import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final double size = 250;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double targetArea = size * size / 4;
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		double tX = 0;
		double tY = 0;
		if (x == 0) {
			if (y >= size / 2) {
				tX = targetArea * 2 / y;
			}
			else {
				tX = targetArea * 2 / (size - y);
				tY = size - tX;
			}
		}
		else if (y == 0) {
			if (x >= size / 2) {
				tY = targetArea * 2 / x;
			}
			else {
				tY = targetArea * 2 / (size - x);
				tX = size - tY;
			}
		}
		else {
			if (x <= size / 2) {
				tX = size - targetArea * 2 / y;
			}
			else {
				tY = size - targetArea * 2 / x;
			}
		}
		System.out.println(String.format("%.2f %.2f", tX, tY));
	}
}