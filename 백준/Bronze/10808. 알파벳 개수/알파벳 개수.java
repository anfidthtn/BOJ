import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] counts = new int[26];
		for (int i = 0; i < str.length(); i++) {
			counts[str.charAt(i) - 'a']++;
		}
		for(int count : counts) {
			System.out.print(count);
			System.out.print(" ");
		}
	}
}