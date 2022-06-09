import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder[] sbs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sbs = new StringBuilder[13];
		sbs[0] = new StringBuilder();
		sbs[0].append("-");
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(getN(Integer.parseInt(line))).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	public static String getN(int N) {
		if (sbs[N] != null) {
			return sbs[N].toString();
		}
		sbs[N] = new StringBuilder();
		sbs[N].append(getN(N - 1));
		int p3 = power3(N - 1);
		for(int i = 0; i < p3; i++) {
			sbs[N].append(" ");
		}
		sbs[N].append(getN(N - 1));
		return sbs[N].toString();
	}
	public static int power3(int N) {
		int result = 1;
		for(int i = 0; i < N; i++) {
			result *= 3;
		}
		return result;
	}
}