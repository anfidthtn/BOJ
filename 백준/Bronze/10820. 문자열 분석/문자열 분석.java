import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = br.readLine()) != null) {
			int[] counts = new int[4];
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if ('a' <= c && c <= 'z') {
					counts[0]++;
				}
				else if('A' <= c && c <= 'Z') {
					counts[1]++;
				}
				else if('0' <= c && c <= '9') {
					counts[2]++;
				}
				else {
					counts[3]++;
				}
			}
			for(int i = 0; i < 4; i++) {
				System.out.print(counts[i] + " ");
			}
			System.out.println();
		}
	}
}