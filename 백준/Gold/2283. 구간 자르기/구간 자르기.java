import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Integer.parseInt(st.nextToken());
		int[] counts = new int[1_001_000];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			counts[Integer.parseInt(st.nextToken())]++;
			counts[Integer.parseInt(st.nextToken())]--;
		}
		for(int i = 1; i < 1_000_010; i++) {
			counts[i] += counts[i - 1];
		}
		int left = 0;
		int right = 0;
		long sum = 0;
		while(left < 1_000_010 && right < 1_000_010) {
			if (sum == K) {
				System.out.println(left + " " + right);
				return;
			}
			else if (sum < K){
				sum += counts[right++];
			}
			else {
				sum -= counts[left++];
			}
		}
		System.out.println("0 0");
	}
}