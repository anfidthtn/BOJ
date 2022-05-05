import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] weights = new int[N];
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(weights);
		int sum = 0;
		int idx = 0;
		int target = 1;
		while(true) {
			if (sum < target) {
				if (idx == N || weights[idx] > target) {
					System.out.println(target);
					return;
				}
				else {
					sum += weights[idx++];
				}
			}
			target++;
		}
	}
}