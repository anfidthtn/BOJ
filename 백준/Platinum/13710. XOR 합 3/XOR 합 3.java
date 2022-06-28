import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long total = Integer.parseInt(st.nextToken());
		long[] bitCount = new long[30];
		bitAdd(bitCount, (int) total);
		for(int i = 1; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			total += getXOR(bitCount, num, i);
			bitAdd(bitCount, num);
			total += num;
		}
		System.out.println(total);
	}
	public static long getXOR(long[] bitCount, int value, int count) {
		long result = 0;
		for(int i = 0; i < 30; i++) {
			if ((value & (1 << i)) != 0) {
				bitCount[i] = count - bitCount[i];
			}
			result += (1 << i) * bitCount[i];
		}
		return result;
	}
	public static void bitAdd(long[] bitCount, int value) {
		for(int i = 0; i < 30; i++) {
			if ((value & (1 << i)) != 0) {
				bitCount[i]++;
			}
		}
	}
}