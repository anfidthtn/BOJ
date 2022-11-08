import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] counts = new int[P + N];
		int num = N;
		while (counts[num] < 20) {
			counts[num]++;
			num = (num * N) % P;
		}
		int count = 0;
		for(int i = 0; i < P; i++) {
			if (counts[i] > 10) {
				count++;
			}
		}
		System.out.println(count);
	}
}