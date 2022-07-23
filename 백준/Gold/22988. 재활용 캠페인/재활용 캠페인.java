import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int bottle = N;
		long X = Long.parseLong(st.nextToken()) * 2;
		long[] C = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			C[i] = Long.parseLong(st.nextToken()) * 2;
		}
		Arrays.sort(C);
		int left = 0;
		int right = N - 1;
		while(right >= 0 && C[right] == X) {
			count++;
			bottle--;
			right--;
		}
		while(left < right) {
			if (C[left] + C[right] >= X / 2) {
				count++;
				left++;
				right--;
				bottle -= 2;
			}
			else {
				left++;
			}
		}
		count += bottle / 3;
		System.out.println(count);
	}
}