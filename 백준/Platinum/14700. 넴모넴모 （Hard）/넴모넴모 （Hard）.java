import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static long[][] ways;
	
	static final int MODNUM = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		N = Math.max(a, b);
		M = Math.min(a, b);
		ways = new long[N * M][1 << (M + 1)];
		
		System.out.println(getWays(0, 0));
	}
	public static long getWays(int linearIdx, int status) {
		if(linearIdx == N * M) {
			return 1;
		}
		long result = ways[linearIdx][status];
		if (result != 0) {
			if (result == -1) {
				
				return 0;
			}
			return result;
		}
		
		if (linearIdx % M == 0 || (status & 1) == 0 || (status & (1 << (M - 1))) == 0 || (status & (1 << M)) == 0) {
			result += getWays(linearIdx + 1, ((status << 1) + 1) & ((1 << (M + 1)) - 1)) % MODNUM;
		}
		result += getWays(linearIdx + 1, (status << 1) & ((1 << (M + 1)) - 1)) % MODNUM;
		
		if (result == 0) {
			ways[linearIdx][status] = -1;
			return 0;
		}
		return ways[linearIdx][status] = result % MODNUM;
		
	}
}