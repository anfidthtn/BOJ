import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb1.append("(G");
		sb2.append(")(");
		while (sb1.length() < N) {
			sb1.append("a");
			sb2.append("a");
		}
		System.out.println(sb1);
		System.out.println(sb2);
	}
}