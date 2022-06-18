import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static double[] rates;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		rates = new double[N + 1];
		for(int i = 1; i <= N; i++) {
			getRate(i);
		}
		System.out.println(getRate(N));
	}
	public static double getRate(int num) {
		if (num <= 0) {
			return 0;
		}
		if (rates[num] > 0) {
			return rates[num];
		}
		for(int i = 6; i >= 1; i--) {
			rates[num] += getRate(num - i) + 1;
		}
		return rates[num] /= 6;
	}
}