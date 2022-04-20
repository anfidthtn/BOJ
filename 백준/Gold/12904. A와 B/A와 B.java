import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder(br.readLine().trim());
		StringBuilder T = new StringBuilder(br.readLine().trim());
		while(!S.toString().equals(T.toString())) {
			if (S.length() >= T.length()) {
				System.out.print(0);
				return;
			}
			switch(T.charAt(T.length() - 1)) {
			case 'A':
				T = new StringBuilder(T.substring(0, T.length() - 1));
				break;
			case 'B':
				T = new StringBuilder(T.substring(0, T.length() - 1));
				T = T.reverse();
				break;
			}
		}
		System.out.print(1);
	}
}