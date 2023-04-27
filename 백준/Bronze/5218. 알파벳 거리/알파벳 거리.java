import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static String A;
	static String B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = st.nextToken();
			B = st.nextToken();
			sb.append("Distances: ");
			for(int i = 0; i < A.length(); i++) {
				sb.append((26 + B.charAt(i) - A.charAt(i)) % 26).append(" ");
			}	
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}