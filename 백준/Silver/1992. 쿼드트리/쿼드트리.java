import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[] info;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// N 맥시멈이 64이기때문에 64비트 long형에 저장가능
		info = new long[N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			long n = 0;
			for(int j = N - 1; j >= 0; j--) {
				n <<= 1;
				if (s.charAt(j) == '1') n++;
			}
			info[i] = n;
		}
		StringBuilder sb = new StringBuilder();
		makeString(sb, 0, 0, N);
		System.out.print(sb.toString());
	}
	public static void makeString(StringBuilder sb, int row, int col, int N) {
		if (N == 1) {
			if ((info[row] & ((long)1 << col)) != 0) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			return;
		}
		sb.append('(');
		makeString(sb, row, col, N / 2);
		makeString(sb, row, col + N / 2, N / 2);
		makeString(sb, row + N / 2, col, N / 2);
		makeString(sb, row + N / 2, col + N / 2, N / 2);
		sb.append(')');
		if (sb.substring(sb.length() - 6).equals("(0000)")) {
			sb.delete(sb.length() - 6, sb.length());
			sb.append(0);
		}
		else if (sb.substring(sb.length() - 6).equals("(1111)")) {
			sb.delete(sb.length() - 6, sb.length());
			sb.append(1);
		}
	}
}