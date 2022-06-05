import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int maxBit = 60;
	static int[] bitsX;
	static long LO;
	static long HI;
	static int X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		bitsX = new int[maxBit + 1];
		for (int i = 1; i < maxBit + 1; i++) {
			int num = i;
			bitsX[i] = 1;
			while (num > 1) {
				bitsX[i]++;
				int bitCount = 0;
				while (num > 0) {
					if (num % 2 == 1) {
						bitCount++;
					}
					num >>= 1;
				}
				num = bitCount;
			}
		}
		// for(int i = 1; i < maxBit + 1; i++) {
		// System.out.println(Integer.toBinaryString(i) + " " + bitsX[i]);
		// }
		for (int tNum = 1;; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			LO = Long.parseLong(st.nextToken());
			HI = Long.parseLong(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			if (LO == 0 && HI == 0 && X == 0) {
				break;
			}
			if (X == 0) {
				if (LO <= 1) {
					sb.append("1\n");
					continue;
				}
				else {
					sb.append("0\n");
					continue;
				}
			}
			long result = getCount(0, ((long) 1) << 60, 60, 0);
			if (X == 1 && LO == 1) {
				result--;
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static long getCount(long start, long end, int unusedBit, int bitCount) {
		if (HI < start || end < LO) {
			return 0;
		}
		if (LO <= start && end <= HI) {
			long result = 0;
			for (int bit = 1; bit <= maxBit; bit++) {
				if (bitsX[bit] == X) {
					if (bit < bitCount) {
						continue;
					}
					if (unusedBit < bit - bitCount) {
						break;
					}
					result += getComb(unusedBit, bit - bitCount);
				}
			}
//			if (start <= 1 && 1 <= end) {
//				result--;
//			}
			return result;
		}
		long mid = (start + end + 1) >> 1;
		return getCount(start, mid - 1, unusedBit - 1, bitCount)
				+ getCount(mid, end, unusedBit - 1, bitCount + 1);
	}

	public static long getComb(int n, int r) {
		if (n == 0 || r == 0) {
			return 1l;
		}
		r = Math.min(n - r, r);
		int[] a = new int[r + 1];
		int[] b = new int[r + 1];
		for (int i = 1; i <= r; i++) {
			a[i] = n - r + i;
		}
		for (int i = 1; i <= r; i++) {
			b[i] = i;
		}
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= r && b[i] > 1; j++) {
				if (a[j] == 1) {
					continue;
				}
				int g = gcd(a[j], b[i]);
				a[j] /= g;
				b[i] /= g;
			}
		}
		long result = 1;
		for(int i = 1; i <= r; i++) {
			result *= a[i];
		}
		return result;
	}

	public static int gcd(int a, int b) {
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}