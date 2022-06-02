import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static final long[] ones = {
			11l, // 2
			111l, // 3
			11111l, // 5
			1111111l, // 7
			11111111111l, // 11
			1111111111111l, // 13
			11111111111111111l // 17
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		long count = 0l;
		for(int i = 1; i < 1 << ones.length; i++) {
			long bitlcm = bitLCM(i);
			if (bitlcm > 0) {
				int bitCount = 0;
				for(int bit = 0; bit < ones.length; bit++) {
					if ((i & (1 << bit)) != 0) {
						bitCount++;
					}
				}
				if (bitCount % 2 == 0) {
					count -= N / bitlcm;
				}
				else {
					count += N / bitlcm;
				}
			}
		}
		System.out.println(count);
	}
	public static long bitLCM(int bit) {
		List<Long> target = new ArrayList<>();
		for(int i = 0; i < ones.length; i++) {
			if ((bit & (1 << i)) != 0) {
				target.add(ones[i]);
			}
		}
		long bitlcm = target.get(0);
		for(int i = 1; i < target.size(); i++) {
			bitlcm = lcm(bitlcm, target.get(i));
		}
		return bitlcm;
	}
	public static long lcm(long a, long b) {
		if (a < 0 || b < 0) {
			return -1;
		}
		long gcd = gcd(a, b);
		if (Math.log10(a) + Math.log10(b) - Math.log10(gcd) > 18) {
			return -1;
		}
		else {
			return a / gcd * b;
		}
	}
	public static long gcd(long a, long b) {
		if (a < b) {
			long temp = a;
			a = b;
			b = temp;
		}
		while(b > 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}