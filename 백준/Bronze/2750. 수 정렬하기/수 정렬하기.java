import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] counts = new int[2001];
		for(int i = 0; i < N; i++) {
			counts[Integer.parseInt(br.readLine()) + 1000]++;
		}
		for(int i = 0; i <= 2000; i++) {
			for(int c = 0; c < counts[i]; c++) {
				System.out.println(i - 1000);
			}
		}
	}
}