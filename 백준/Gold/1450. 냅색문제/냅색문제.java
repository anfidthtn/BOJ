import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long C;
	static long[] weights;
	static long[] preSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		weights = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(weights);
		preSum = new long[N];
		preSum[0] = weights[0];
		for(int i = 1; i < N; i++) {
			preSum[i] = weights[i] + preSum[i - 1];
		}
		System.out.println(getCount(N - 1, C));
	}
	public static int getCount (int nowIdx, long value) {
		if (nowIdx == -1) {
			return 1;
		}
		if (preSum[nowIdx] <= value) {
			return power(2, nowIdx + 1);
		}
		int result = 0;
		if (weights[nowIdx] <= value) {
			result += getCount(nowIdx - 1, value - weights[nowIdx]);
		}
		result += getCount(nowIdx - 1, value);
		
		return result;
	}
	public static int power(int num, int expo) {
		int result = 1;
		while(expo > 0) {
			if (expo % 2 == 1) {
				result *= num;
			}
			expo /= 2;
			num *= num;
		}
		return result;
	}
}